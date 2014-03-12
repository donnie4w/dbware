package com.dbware.mysql.handler;

import java.sql.SQLException;
import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.dbware.db.BasePacket;
import com.dbware.db.BindAddress;
import com.dbware.db.GlobalVar;
import com.dbware.listener.PubVars;
import com.dbware.listener.ThreadPool;
import com.dbware.log.LogFactory;
import com.dbware.log.Logger;
import com.dbware.mysql.code.ErrEnum;
import com.dbware.mysql.flow.HandShakeFlow;
import com.dbware.mysql.packet.AuthPacket;
import com.dbware.mysql.packet.CommandPacket;
import com.dbware.mysql.packet.ErrPacket;
import com.dbware.mysql.packet.InitialPacket;
import com.dbware.mysql.packet.OkPacket;
import com.dbware.mysql.packet.RowAllPacket;
import com.dbware.util.StringUtil;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-07
 * @verion 1.0
 */
public class MysqlChannelUpstreamHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = LogFactory.getLogger(MysqlChannelUpstreamHandler.class);
	private HandShakeFlow FLOW;
	private PubVars vars;
	AuthTimeOut ato = null;

	public MysqlChannelUpstreamHandler(PubVars vars) {
		this.vars = vars;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		if (StringUtil.isNotNull(BindAddress.wrap(ctx.getChannel().getRemoteAddress().toString()))) {
			logger.info("clinet [" + ctx.getChannel().getRemoteAddress() + "]");
		} else {
			forceCloseConnect(e.getChannel());
		}
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		try {
			vars.releaseConnection();
		} catch (SQLException e1) {
		}
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		sendResultSetPacket(e.getChannel(), new InitialPacket(this));
		FLOW = HandShakeFlow.AUTH;
		ato = new AuthTimeOut(e.getChannel());
		ThreadPool.getDefualExecutor().execute(ato);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		byte[] byteMsg = (byte[]) e.getMessage();
		switch (FLOW) {
		case AUTH:
			AuthPacket auth = new AuthPacket(byteMsg);
			if (StringUtil.isEmpty(auth.getDbName())) {
				FLOW = HandShakeFlow.CLOSE;
				sendResultSetPacket(
						e.getChannel(),
						new ErrPacket((short) ErrEnum.E1046.getErrorCode(), StringUtil.getBytes(ErrEnum.E1046.getSqlState()), ErrEnum.E1046
								.getErrorMessage()));
			} else if (auth.checkAuth()) {
				FLOW = HandShakeFlow.COMMAND;
				vars.setGroupName(auth.getGroupName());
				sendResultSetPacket(e.getChannel(), new OkPacket());
				break;
			} else {
				FLOW = HandShakeFlow.CLOSE;
				sendResultSetPacket(
						e.getChannel(),
						new ErrPacket((short) ErrEnum.E1045.getErrorCode(), StringUtil.getBytes(ErrEnum.E1045.getSqlState()), ErrEnum.E1045
								.getErrorMessage()));
			}
		case COMMAND:
			ato.setAuth(false);
			vars.setSequenceId(0);
			try {
				sendResultSetPacket(e.getChannel(), CommandExecuteGuide.newInstance(new CommandPacket(byteMsg), vars).execute());
			} catch (SQLException e1) {
				logger.error("", e1);
				short code = (short) e1.getErrorCode();
				String state = e1.getSQLState();
				String msg = e1.getMessage();
				sendResultSetPacket(
						e.getChannel(),
						new ErrPacket(code == 0 ? (short) ErrEnum.E1105.getErrorCode() : code, StringUtil.getBytes(state == null ? ErrEnum.E1105
								.getSqlState() : state), msg == null ? ErrEnum.E1105.getErrorMessage() : msg));
			} catch (Exception e2) {
				logger.error("", e2);
				sendResultSetPacket(
						e.getChannel(),
						new ErrPacket((short) ErrEnum.E1105.getErrorCode(), StringUtil.getBytes(ErrEnum.E1105.getSqlState()), ErrEnum.E1105
								.getErrorMessage()));
			}
			break;
		case CLOSE:
			closeConnect(e.getChannel());
			break;
		}
	}

	public void sendResultSetPacket(Channel channel, BasePacket packet) {
		channel.write(packet.toBytes());
	}

	public void sendResultSetPacket(Channel channel, List<BasePacket> packetList) {
		for (BasePacket packet : packetList) {
			if (packet instanceof RowAllPacket) {
				channel.write(packet);
			} else {
				byte[] bytes = packet.toBytes();
				if (bytes != null)
					channel.write(bytes);
			}
		}
	}

	private void closeConnect(Channel channel) {
		try {
			channel.close().addListener(ChannelFutureListener.CLOSE);
		} catch (Exception e) {
		}
	}

	private void forceCloseConnect(Channel channel) {
		try {
			channel.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, ExceptionEvent arg1) throws Exception {
	}

	class AuthTimeOut implements Runnable {
		boolean isAuth = false;
		Channel channel;
		int timeOutSecond = GlobalVar.getAuthTimeOut();
		int interval = 100;

		public AuthTimeOut(Channel channel) {
			this.channel = channel;
		}

		@Override
		public void run() {
			while (!isAuth && timeOutSecond > 0) {
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
				}
				timeOutSecond -= interval;
			}
			if (timeOutSecond <= 0) {
				forceCloseConnect(channel);
			}
		}

		public void setAuth(boolean isAuth) {
			this.isAuth = isAuth;
		}
	}
}

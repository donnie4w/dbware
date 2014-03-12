package com.dbware.db.handler;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.util.Set;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import com.dbware.cluster.cache.ResultSetCache;
import com.dbware.db.GlobalVar;
import com.dbware.db.ManagerEnum;
import com.sun.management.OperatingSystemMXBean;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @verion 1.0.4
 */
public class ManagerChannelUpstreamHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		e.getChannel().write(GlobalVar.getVersion());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String msg = ((String) e.getMessage()).trim();
		String command = msg;
		String arg = null;
		if (msg.contains(" ")) {
			String[] ss = msg.split("\\s{1,}");
			command = ss[0].trim();
			arg = ss[1].trim();
		}
		switch (ManagerEnum.getManagerType(command)) {
		case HELP:
			sendResult(e.getChannel(), help());
			break;
		case KEYS:
			sendResult(e.getChannel(), keys());
			break;
		case RM:
			if (arg == null || "".equals(arg)) {
				sendResult(e.getChannel(), help());
			} else {
				ResultSetCache.SyncRemove(arg);
				sendResult(e.getChannel(), "ok");
			}
			break;
		case INFO:
			sendResult(e.getChannel(), info());
			break;
		case EXIST:
			if (arg == null || "".equals(arg)) {
				sendResult(e.getChannel(), help());
			} else {
				sendResult(e.getChannel(), String.valueOf(exist(arg)));
			}
			break;
		case MU:
			if (arg == null || "".equals(arg)) {
				sendResult(e.getChannel(), help());
			} else {
				sendResult(e.getChannel(), mu(arg) + " byte");
			}
			break;
		case CLEAR:
			if (arg != null) {
				sendResult(e.getChannel(), help());
			} else {
				ResultSetCache.SyncClear();
				sendResult(e.getChannel(), "ok");
			}
			break;
		case QUIT:
			forceCloseConnect(e.getChannel());
			break;
		case BYE:
			forceCloseConnect(e.getChannel());
			break;
		case ERROR:
			sendResult(e.getChannel(), help());
			break;
		default:
			closeConnect(e.getChannel());
		}
	}

	public void sendResult(Channel channel, String msg) {
		channel.write(msg);
	}

	private static String help() {
		StringBuilder sb = new StringBuilder();
		sb.append("help").append(GlobalVar.CRLF);
		sb.append("info").append(GlobalVar.CRLF);
		sb.append("exist key").append(GlobalVar.CRLF);
		sb.append("mu key  memory usage").append(GlobalVar.CRLF);
		sb.append("keys").append(GlobalVar.CRLF);
		sb.append("rm key  remove key").append(GlobalVar.CRLF);
		sb.append("clear").append(GlobalVar.CRLF);
		sb.append("quit").append(GlobalVar.CRLF);
		sb.append("bye");
		return sb.toString();
	}

	private static String keys() {
		StringBuilder sb = new StringBuilder();
		Set<String> set = ResultSetCache.getKeys();
		for (String s : set) {
			sb.append(s).append(GlobalVar.CRLF);
		}
		sb.append("count:").append(set.size());
		return sb.toString();
	}

	private static boolean exist(String key) {
		return ResultSetCache.containsKey(key);
	}

	private static int mu(String key) {
		return ResultSetCache.mu(key);
	}

	private static String info() {
		StringBuilder ret = new StringBuilder();
		try {
			OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			String osVersion = osmxb.getVersion();
			double loadAverage = osmxb.getSystemLoadAverage();
			int cpuCount = osmxb.getAvailableProcessors();
			String osName = osmxb.getName();
			String arch = osmxb.getArch();
			String ip = InetAddress.getLocalHost().getHostAddress();
			int kb = 1024;
			long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
			long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
			RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
			String upTime = formatDuring(runtimeMXBean.getUptime());
			ret.append("os             :").append(osName).append(" ").append(osVersion).append(" ").append(arch).append(GlobalVar.CRLF);
			ret.append("ip             :").append(ip).append(GlobalVar.CRLF);
			ret.append("cpu count      :").append(cpuCount).append(GlobalVar.CRLF);
			ret.append("physical memory:").append(totalMemorySize).append(" KB").append(GlobalVar.CRLF);
			ret.append("used memory    :").append(usedMemory).append(" KB").append(GlobalVar.CRLF);
			ret.append("load Average   :").append(loadAverage).append(GlobalVar.CRLF);
			ret.append("keys count     :").append(ResultSetCache.getKeys().size()).append(GlobalVar.CRLF);
			ret.append("run time       :").append(upTime);
		} catch (Exception e) {
		}
		return ret.toString();
	}

	private static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
	}

	private void closeConnect(Channel channel) {
		try {
			channel.close().addListener(ChannelFutureListener.CLOSE);
		} catch (Exception e) {
		}
	}

	private void forceCloseConnect(Channel channel) {
		try {
			channel.disconnect();
		} catch (Exception e) {
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, ExceptionEvent arg1) throws Exception {
	}
}

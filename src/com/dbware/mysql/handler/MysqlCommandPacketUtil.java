package com.dbware.mysql.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbware.db.BasePacket;
import com.dbware.db.CommandHandler;
import com.dbware.mysql.packet.EofPacket;
import com.dbware.mysql.packet.FeildPacket;
import com.dbware.mysql.packet.HeaderPacket;
import com.dbware.mysql.packet.MysqlFieldType;
import com.dbware.mysql.packet.OkPacket;
import com.dbware.mysql.packet.ResultSetHeaderPacket;
import com.dbware.mysql.packet.RowAllPacket;
import com.dbware.mysql.packet.RowPacket;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-12
 * @verion 1.0
 */
public class MysqlCommandPacketUtil {
	public static List<BasePacket> queryPacket(CommandHandler handler, String s) throws SQLException {
		List<BasePacket> packetList = new ArrayList<BasePacket>();
		ResultSet rs = null;
		try {
			rs = handler.executeQuery(s);
			ResultSetMetaData meta = rs.getMetaData();
			ResultSetHeaderPacket rshp = new ResultSetHeaderPacket(meta.getColumnCount());
			packetList.add(rshp);
			int columnCount = meta.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				FeildPacket feild = new FeildPacket();
				feild.setDb(meta.getCatalogName(i));
				feild.setTable(meta.getTableName(i));
				feild.setOrgTable(meta.getTableName(i));
				feild.setName(meta.getColumnName(i));
				feild.setOrgName(meta.getColumnName(i));
				feild.setType((byte) MysqlFieldType.javaToMysql(meta.getColumnType(i)));
				feild.setLength(meta.getColumnDisplaySize(i));
				packetList.add(feild);
			}
			packetList.add(new EofPacket());
			RowAllPacket rap = new RowAllPacket();
			while (rs.next()) {

				RowPacket rowPacket = new RowPacket();
				for (int i = 1; i <= columnCount; i++) {
					rowPacket.addValue(rs.getString(i));
				}
				// packetList.add(rowPacket);
				HeaderPacket header = new HeaderPacket();
				byte[] bs = rowPacket.toBytes();
				header.setLength(bs.length);
				rap.putBytes(header.toBytes());
				rap.putBytes(bs);
			}
			packetList.add(rap);
			packetList.add(new EofPacket());
		} finally {
			if (rs != null)
				rs.close();
		}

		return packetList;
	}

	public static List<BasePacket> updatePacket(CommandHandler handler, String sql) throws SQLException {
		List<BasePacket> packetList = new ArrayList<BasePacket>();
		int updateCount = handler.executeUpdate(sql);
		OkPacket okPacket = new OkPacket();
		okPacket.setAffectedRows(updateCount);
		packetList.add(okPacket);
		return packetList;
	}

}

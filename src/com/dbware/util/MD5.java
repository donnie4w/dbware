package com.dbware.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.dbware.db.cfg.DBwareConfigXml;

public class MD5 {
	public static String getMD5(String msg) {
		String ret = null;
		try {
			byte[] result = msg.getBytes(DBwareConfigXml.getCharacterSet());
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(result);
			ret = ByteUtil.byte2hex(digest);
		} catch (UnsupportedEncodingException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		return ret;
	}
}

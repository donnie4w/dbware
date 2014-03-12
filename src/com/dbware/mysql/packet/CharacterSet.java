package com.dbware.mysql.packet;

import com.dbware.db.cfg.DBwareConfigXml;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-18
 * @verion 1.0
 */
public class CharacterSet {
	public final static short big5_chinese_ci = 0x01;
	public final static short latin2_czech_cs = 0x02;
	public final static short dec8_swedish_ci = 0x03;
	public final static short cp850_general_ci = 0x04;
	public final static short latin1_german1_ci = 0x05;
	public final static short hp8_english_ci = 0x06;
	public final static short koi8r_general_ci = 0x07;
	public final static short latin1_swedish_ci = 0x08;
	public final static short latin2_general_ci = 0x09;
	public final static short swe7_swedish_ci = 0x0A;
	public final static short ascii_general_ci = 0x0B;
	public final static short ujis_japanese_ci = 0x0C;
	public final static short sjis_japanese_ci = 0x0D;
	public final static short cp1251_bulgarian_ci = 0x0E;
	public final static short latin1_danish_ci = 0x0F;
	public final static short hebrew_general_ci = 0x10;
	public final static short tis620_thai_ci = 0x12;
	public final static short euckr_korean_ci = 0x13;
	public final static short latin7_estonian_cs = 0x14;
	public final static short latin2_hungarian_ci = 0x15;
	public final static short koi8u_general_ci = 0x16;
	public final static short cp1251_ukrainian_ci = 0x17;
	public final static short gb2312_chinese_ci = 0x18;
	public final static short greek_general_ci = 0x19;
	public final static short cp1250_general_ci = 0x1A;
	public final static short latin2_croatian_ci = 0x1B;
	public final static short gbk_chinese_ci = 0x1C;
	public final static short cp1257_lithuanian_ci = 0x1D;
	public final static short latin5_turkish_ci = 0x1E;
	public final static short latin1_german2_ci = 0x1F;
	public final static short armscii8_general_ci = 0x20;
	public final static short utf8_general_ci = 0x21;
	public final static short cp1250_czech_cs = 0x22;
	public final static short ucs2_general_ci = 0x23;
	public final static short cp866_general_ci = 0x24;
	public final static short keybcs2_general_ci = 0x25;
	public final static short macce_general_ci = 0x26;
	public final static short macroman_general_ci = 0x27;
	public final static short cp852_general_ci = 0x28;
	public final static short latin7_general_ci = 0x29;
	public final static short latin7_general_cs = 0x2A;
	public final static short macce_bin = 0x2B;
	public final static short cp1250_croatian_ci = 0x2C;
	public final static short latin1_bin = 0x2F;
	public final static short latin1_general_ci = 0x30;
	public final static short latin1_general_cs = 0x31;
	public final static short cp1251_bin = 0x32;
	public final static short cp1251_general_ci = 0x33;
	public final static short cp1251_general_cs = 0x34;
	public final static short macroman_bin = 0x35;
	public final static short cp1256_general_ci = 0x39;
	public final static short cp1257_bin = 0x3A;
	public final static short cp1257_general_ci = 0x3B;
	public final static short binary = 0x3F;
	public final static short armscii8_bin = 0x40;
	public final static short ascii_bin = 0x41;
	public final static short cp1250_bin = 0x42;
	public final static short cp1256_bin = 0x43;
	public final static short cp866_bin = 0x44;
	public final static short dec8_bin = 0x45;
	public final static short greek_bin = 0x46;
	public final static short hebrew_bin = 0x47;
	public final static short hp8_bin = 0x48;
	public final static short keybcs2_bin = 0x49;
	public final static short koi8r_bin = 0x4A;
	public final static short koi8u_bin = 0x4B;
	public final static short latin2_bin = 0x4D;
	public final static short latin5_bin = 0x4E;
	public final static short latin7_bin = 0x4F;
	public final static short cp850_bin = 0x50;
	public final static short cp852_bin = 0x51;
	public final static short swe7_bin = 0x52;
	public final static short utf8_bin = 0x53;
	public final static short big5_bin = 0x54;
	public final static short euckr_bin = 0x55;
	public final static short gb2312_bin = 0x56;
	public final static short gbk_bin = 0x57;
	public final static short sjis_bin = 0x58;
	public final static short tis620_bin = 0x59;
	public final static short ucs2_bin = 0x5A;
	public final static short ujis_bin = 0x5B;
	public final static short geostd8_general_ci = 0x5C;
	public final static short geostd8_bin = 0x5D;
	public final static short latin1_spanish_ci = 0x5E;
	public final static short cp932_japanese_ci = 0x5F;
	public final static short cp932_bin = 0x60;
	public final static short eucjpms_japanese_ci = 0x61;
	public final static short eucjpms_bin = 0x62;
	public final static short ucs2_unicode_ci = 0x80;
	public final static short ucs2_icelandic_ci = 0x81;
	public final static short ucs2_latvian_ci = 0x82;
	public final static short ucs2_romanian_ci = 0x83;
	public final static short ucs2_slovenian_ci = 0x84;
	public final static short ucs2_polish_ci = 0x85;
	public final static short ucs2_estonian_ci = 0x86;
	public final static short ucs2_spanish_ci = 0x87;
	public final static short ucs2_swedish_ci = 0x88;
	public final static short ucs2_turkish_ci = 0x89;
	public final static short ucs2_czech_ci = 0x8A;
	public final static short ucs2_danish_ci = 0x8B;
	public final static short ucs2_lithuanian_ci = 0x8C;
	public final static short ucs2_slovak_ci = 0x8D;
	public final static short ucs2_spanish2_ci = 0x8E;
	public final static short ucs2_roman_ci = 0x8F;
	public final static short ucs2_persian_ci = 0x90;
	public final static short ucs2_esperanto_ci = 0x91;
	public final static short ucs2_hungarian_ci = 0x92;
	public final static short utf8_unicode_ci = 0xC0;
	public final static short utf8_icelandic_ci = 0xC1;
	public final static short utf8_latvian_ci = 0xC2;
	public final static short utf8_romanian_ci = 0xC3;
	public final static short utf8_slovenian_ci = 0xC4;
	public final static short utf8_polish_ci = 0xC5;
	public final static short utf8_estonian_ci = 0xC6;
	public final static short utf8_spanish_ci = 0xC7;
	public final static short utf8_swedish_ci = 0xC8;
	public final static short utf8_turkish_ci = 0xC9;
	public final static short utf8_czech_ci = 0xCA;
	public final static short utf8_danish_ci = 0xCB;
	public final static short utf8_lithuanian_ci = 0xCC;
	public final static short utf8_slovak_ci = 0xCD;
	public final static short utf8_spanish2_ci = 0xCE;
	public final static short utf8_roman_ci = 0xCF;
	public final static short utf8_persian_ci = 0xD0;
	public final static short utf8_esperanto_ci = 0xD1;
	public final static short utf8_hungarian_ci = 0xD2;

	public static byte getByte() {
		if ("utf-8".equals(DBwareConfigXml.getCharacterSet().toLowerCase())) {
			return utf8_general_ci;
		} else if ("gbk".equals(DBwareConfigXml.getCharacterSet().toLowerCase())) {
			return gbk_chinese_ci;
		} else if ("latin1".equals(DBwareConfigXml.getCharacterSet().toLowerCase())) {
			return latin1_swedish_ci;
		} else if ("gb2312".equals(DBwareConfigXml.getCharacterSet().toLowerCase())) {
			return gb2312_chinese_ci;
		}
		return utf8_general_ci;
	}
}

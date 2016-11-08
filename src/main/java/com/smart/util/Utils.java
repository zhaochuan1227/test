package com.smart.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *利用工具类封装通用算法
 *
 */
public class Utils {
	private static final String SALT = "再来一盘哇!";
	public static String md5salt(String pwd){
		return DigestUtils.md5Hex(pwd+SALT);
	}
}

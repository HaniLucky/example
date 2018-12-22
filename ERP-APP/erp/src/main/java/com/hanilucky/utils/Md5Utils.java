package com.hanilucky.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Utils {
	
	public static String Md5Encode(String source, String salt){
		return new Md5Hash(source, salt, 2).toString();
	}

}

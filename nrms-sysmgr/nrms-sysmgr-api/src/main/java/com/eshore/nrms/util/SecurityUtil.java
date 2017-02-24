package com.eshore.nrms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class SecurityUtil {

	public static String md5(String value){
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte[] bts = md.digest();
			return Hex.encodeHexString(bts);
		} catch (NoSuchAlgorithmException e) {
			return result; 
		} 
	}
	
}

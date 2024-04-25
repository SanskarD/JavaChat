package org.sanskar.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	public static String passwordEncrypt(String inputPassword) throws NoSuchAlgorithmException{
		String encryptedPass = null;
		MessageDigest messageDigest =   MessageDigest.getInstance("MD5");
		messageDigest.update(inputPassword.getBytes());
		byte[] encrypt= messageDigest.digest();
		StringBuffer sBuffer = new StringBuffer();
		for(byte b:encrypt) {
			sBuffer.append(b);
		}
		encryptedPass = sBuffer.toString();
		return encryptedPass;
	}
}

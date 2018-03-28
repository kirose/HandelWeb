package mx.handel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
public final class UtilCrypto {
	private UtilCrypto(){}
	public static String sha256(String vale){

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(vale.getBytes());
	
	        byte byteData[] = md.digest();
	
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
	        //convert the byte to hex format method 2
	        /*StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
			return hexString.toString();*/
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}

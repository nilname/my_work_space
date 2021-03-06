/**   
* @Title: DESEncryption.java 
* @Description: TODO 
* @author qyou  
* @date 2013-8-8 下午6:30:39 
* @version V1.0   
*/
package com.match.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.spec.KeySpec;

public class DESEncryption {
 
    private static final String UNICODE_FORMAT = "UTF-8";
    public static final String DES_ENCRYPTION_SCHEME = "DES";
    private KeySpec myKeySpec;
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher cipher;
    byte[] keyAsBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
 
    public DESEncryption() throws Exception {
        myEncryptionKey = "ThisIsSecretEncryptionKey";
        myEncryptionScheme = DES_ENCRYPTION_SCHEME;
        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        myKeySpec = new DESKeySpec(keyAsBytes);
        mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = mySecretKeyFactory.generateSecret(myKeySpec);
    }
 
    /**
     * Method To Encrypt The String
     */
    @SuppressWarnings("restriction")
	public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            encryptedString = base64encoder.encode(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
    /**
     * Method To Decrypt An Ecrypted String
     */
    @SuppressWarnings("restriction")
	public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= bytes2String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
    /**
     * Returns String From An Array Of Bytes
     */
    private static String bytes2String(byte[] bytes) {
    	return new String(bytes);
    }
 
	protected static String inputStreamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder result = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			result.append(line);
			result.append("\n");
		}
		return result.toString();
	}	
	
    /**
     * Testing the DES Encryption And Decryption Technique
     */
    public static void main(String args []) throws Exception
    {
        DESEncryption myEncryptor= new DESEncryption();
 
        File file = new File("/Users/qyou/tmp/log.txt");
		InputStream in = new FileInputStream(file);
		
        String stringToEncrypt=inputStreamToString(in);
        //String encrypted=myEncryptor.encrypt(stringToEncrypt);
        String decrypted=myEncryptor.decrypt(stringToEncrypt);
 
        //System.out.println("String To Encrypt: "+stringToEncrypt);
        //System.out.println("Encrypted Value :" + encrypted);
//        System.out.println("Decrypted Value :"+decrypted);
 
    }  
 
}

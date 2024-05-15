package rsa.secret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

 
public abstract class Coder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Coder.class);
	
	/**
     * RSA最大加密明文大小
     */
    protected static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    protected static final int MAX_DECRYPT_BLOCK = 128;
    
	
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
     
    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */ 
    public static final String KEY_MAC = "HmacMD5";
     
    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64( String key ){
    	
    	byte[] bytes = Base64Utils.decodeFromString(key);
        return bytes;
    }
     
    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64( byte[] key){
    	
    	String content = Base64Utils.encodeToString(key);
        return content;
    }
     
    /**
     * MD5 加密
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5( byte[] data){
        MessageDigest md5;
        byte[] bytes = null;
		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data);
			md5.digest();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException:{} -- {} ",KEY_MD5,e);
		}
        return bytes;
    }
     
    /**
     * SHA 加密
     * @param data
     * @return
     * @throws Exception
     */
	public static byte[] encryptSHA(byte[] data){
		MessageDigest sha;
		byte[] bytes = null;
		try {
			sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);
			bytes = sha.digest();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException:{} -- {} ", KEY_SHA, e);
		}
		return bytes;
	}
     
    /**
     * 初始化HMAC密钥
     * 
     * @return
     * @throws Exception
     */ 
    public static String initMacKey(){
        KeyGenerator keyGenerator;
        String macKey=null;
		try {
			keyGenerator = KeyGenerator.getInstance(KEY_MAC);
			SecretKey secretKey = keyGenerator.generateKey();
			macKey = encryptBASE64(secretKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException:{} -- {} ", KEY_MAC, e);
		}
        return macKey;
    }
     
    /**
     * HMAC  加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
	public static byte[] encryptHMAC(byte[] data, String key) {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = null;
		byte[] bytes = null;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException:{} -- {} ", KEY_MAC, e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {} ", e);
		}

		return bytes;
	}
    
    public static String readInputStream2String(InputStream inputStream) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder buffer = new StringBuilder();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			//ignore
			LOGGER.error("read file Exception : {}", e);
		}finally {
			if(null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					//ignore
					LOGGER.error(" close input stream Exception : {}", e);
				}
			}
		}
		return null;
	}
    
    
    public static String filterAlphabet(String alph)  
    {  
        alph = alph.replaceAll("[^(0-9A-Za-z)]", "Z0Z");  
        return alph;  
    }
}

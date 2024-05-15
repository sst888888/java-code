package rsa.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public abstract class RSACoder extends Coder{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RSACoder.class);
	
	
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
     
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivatekey";
     
    /**
     * 用私钥对信息生成数字签名
     * @param data 加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
	public static String sign(byte[] data, String privateKey) {
		// 解密由base64编码的私钥
		String sign = null;
		try {
			byte[] keyBytes = decryptBASE64(privateKey);

			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			// 取私钥对象
			PrivateKey pKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

			// 用私钥生成数字签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(pKey);
			signature.update(data);
			sign = encryptBASE64(signature.sign());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}", KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		} catch (SignatureException e) {
			LOGGER.error("SignatureException: -- {}", e);
		}
		return sign;
	}
	
    /**
     * 校验数字签名
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign){
         
        //解密有base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
         
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
         
        KeyFactory keyFactory = null;
        boolean success = false;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			//取公钥对象
	        PublicKey pKey = keyFactory.generatePublic(keySpec);
	         
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	        signature.initVerify(pKey);
	        signature.update(data);
	        //验证签名是否正常
	        success = signature.verify(decryptBASE64(sign));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}", KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		} catch (SignatureException e) {
			LOGGER.error("SignatureException: -- {}", e);
		} 
         
		return success;
    }
     
    /**
     * 解密 用私钥解密
     * @param data 加密数据
     * @param key
     * @return
     */
	public static byte[] decryptPrivateKey(byte[] encryptedData, String key) {
		byte[] keyBytes = decryptBASE64(key);

		// 取得私钥
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = null;
		byte[] decryptedData = null;
		ByteArrayOutputStream out = null;
		try {
			factory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key pKey = factory.generatePrivate(encodedKeySpec);

			// 对数据解密
			Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, pKey);
			
			
			out = new ByteArrayOutputStream();
	        // 对数据分段解密
	        cutting(encryptedData, cipher, encryptedData.length, out, MAX_DECRYPT_BLOCK);
	        decryptedData = out.toByteArray();
	        
			
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}", KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("NoSuchPaddingException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		}  finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("IOException: -- {}", e);
			}
		}

		return decryptedData;
	}
     
    /**
     * 用公钥解密
     * @param data
     * @param key
     * @return
     */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String key) {

		// 解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory;
		byte[] decryptedData = null;
		ByteArrayOutputStream out = null;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			Key pKey = keyFactory.generatePublic(keySpec);

			// 对数据解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, pKey);
			

			out = new ByteArrayOutputStream();
	        // 对数据分段解密
	        cutting(encryptedData, cipher, encryptedData.length, out, MAX_DECRYPT_BLOCK);
	        decryptedData = out.toByteArray();

	        
			//dataBytes = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}", KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("NoSuchPaddingException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("IOException: -- {}", e);
			}
		} 
		return decryptedData;
	}
     
    /**
     * 用公钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
	public static byte[] encryptByPublicKey(byte[] data, String key) {

		byte[] keyBytes = null;
		byte[] dataBytes = null;
		keyBytes = decryptBASE64(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = null;
		ByteArrayOutputStream out = null;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			Key pKey = keyFactory.generatePublic(keySpec);

			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, pKey);
			
			out = new ByteArrayOutputStream();
	        // 对数据分段加密
	        cutting(data, cipher, data.length, out, MAX_ENCRYPT_BLOCK);
	        
	        dataBytes = out.toByteArray();
	        
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}", KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("NoSuchPaddingException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("IOException: -- {}", e);
			}
		}

		return dataBytes;
	}
     
    /**
     * 用私钥加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key){
         
        byte[] keyBytes = decryptBASE64(key);
         
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;
        byte[] dataBytes = null;
        ByteArrayOutputStream out = null;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(keySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			
			out = new ByteArrayOutputStream();
	        // 对数据分段加密
	        cutting(data, cipher, data.length, out, MAX_ENCRYPT_BLOCK);
	        
	        dataBytes = out.toByteArray();
			
			//dataBytes = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: {} -- {}",KEY_ALGORITHM, e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error("InvalidKeySpecException: -- {}", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("NoSuchPaddingException: -- {}", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("InvalidKeyException: -- {}", e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("IOException: -- {}", e);
			}
		}
         
        return dataBytes;
    }
     
    /**
     * 取得私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey( Map<String, Object> keyMap){
 
        Key key = (Key) keyMap.get(PRIVATE_KEY);
         
        return encryptBASE64(key.getEncoded());
    }
     
    /**
     * 取得公钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey( Map<String, Object> keyMap){
 
        Key key = (Key) keyMap.get(PUBLIC_KEY);
         
        return encryptBASE64(key.getEncoded());
    }
    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey(){
         
        KeyPairGenerator keyPairGenerator = null;
        Map<String, Object> keyMap = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGenerator.initialize(1024);
	         
	        KeyPair keyPair = keyPairGenerator.generateKeyPair();
	        //公钥
	        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
	         
	        //私钥
	        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
	         
	        keyMap = new HashMap<String, Object>(2);
	        keyMap.put(PRIVATE_KEY, privateKey);
	        keyMap.put(PUBLIC_KEY, publicKey);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("NoSuchAlgorithmException: -- {}", e);
		}
        
        return keyMap;
    }
    
    
    
    /**
     * 分段加密
     *
     * @param data
     * @param cipher
     * @param inputLen
     * @param out
     */
	private static void cutting(byte[] data, Cipher cipher, int inputLen, ByteArrayOutputStream out, int num) {
		try {
			int offSet = 0;
			int i = 0;
			byte[] cache;
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > num) {
					cache = cipher.doFinal(data, offSet, num);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * num;
			}
		} catch (BadPaddingException e) {
			LOGGER.error("BadPaddingException : {} ", e);
		} catch (IllegalBlockSizeException f) {
			LOGGER.error("IllegalBlockSizeException : {} ", f);
		}
	}
    
}

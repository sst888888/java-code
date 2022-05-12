package rsa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacob
 * @version 1.0
 * @date 2021/11/4 15:57
 */
@Slf4j
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    //map对象中存放公私钥
    public static Map<String, Key> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        //公私钥对象存入map中
        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 从公钥字符串中获取公钥
     * @param key Base64的公钥字符串
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从私钥字符串中获取私钥
     * @param key Base64的私钥字符串
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * RSA私钥签名
     * @param content 待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("sign error, content: {}, priKey: {}", content, privateKey);
            log.error("sign error, message is {}", e.getMessage());
        }
        return null;
    }

    /**
     * 通过公钥验签
     * @param content 验签内容
     * @param sign  签名
     * @param publicKey 公钥
     * @return 验签结果
     */
    public static boolean verifySignByPublicKey(String content, String sign, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.warn("sign error, content: {}, sign: {}, pubKey: {}", content, sign, publicKey);
            log.error("sign error", e);
        }
        return false;
    }


    /**
     * 通过公钥加密
     * @param plainText 明文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encryptByPublicKey(String plainText, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] enBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(enBytes);
        } catch (Exception e) {
            log.error("encrypt error", e);
        }
        return null;
    }


    /**
     * 通过私钥解密
     * @param enStr 加密字符串
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decryptByPrivateKey(String enStr, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));
            return new String(deBytes);
        } catch (Exception e) {
            log.error("decrypt error", e);
        }
        return null;
    }


}



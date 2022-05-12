package rsa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class Test {

    @org.junit.Test
    public void testJM() {
        String content = "待加密数据";
        // 第一步，生成一对公私钥
        Map<String, Key> keys = new HashMap<>(2);
        try {
            keys = RSAUtil.initKey();
        }catch (Exception e){
            log.error("init RSA key error，message is {}", e.getMessage());
            System.exit(-1);
        }

        // 获得公钥
        Key publicKey = keys.get(RSAUtil.PUBLIC_KEY);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        log.info(">>>1.base64 publicKey String is:{}", base64PublicKeyStr);

        String encodeString = RSA.encode(content, base64PublicKeyStr); // RSAUtil.enc
        log.info(">>>2.encryptionString by publicKey ,encryptionString is: {}", encodeString);

        // 解密
        // 获得私钥
        Key privateKey = keys.get(RSAUtil.PRIVATE_KEY);
        // 私钥Base64编码字符串
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        log.info(">>>3.base64 privateKey String is:{}", base64PrivateKeyStr);

        String decryptionString = RSAUtil.decryptByPrivateKey(encodeString, base64PrivateKeyStr);
        log.info(">>>4.decryptionString by privateKey ,decryptionString is: {}", decryptionString);

    }

    @org.junit.Test
    public void testSign() {
        String signContent = "待签名数据";
        // 第一步，生成一对公私钥
        Map<String, Key> keys = new HashMap<>(2);
        try {
            keys = RSAUtil.initKey();
        }catch (Exception e){
            log.error("init RSA key error，message is {}", e.getMessage());
            System.exit(-1);
        }

        // 获得私钥
        Key privateKey = keys.get(RSAUtil.PRIVATE_KEY);
        log.info(">>>privateKey is:{}", privateKey);

        // 私钥Base64编码字符串
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        log.info(">>>base64 privateKey String is:{}", base64PrivateKeyStr);

        // 签名
        String signString = RSAUtil.signByPrivateKey(signContent, base64PrivateKeyStr);
        log.info(">>>sign by privateKey ,signString is: {}", signString);

        // 获得公钥
        Key publicKey = keys.get(RSAUtil.PUBLIC_KEY);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        log.info(">>>base64 publicKey String is:{}", base64PublicKeyStr);

        boolean verifySignResult = RSAUtil.verifySignByPublicKey(signContent ,signString, base64PublicKeyStr);
        log.info(">>>verify sign result is: {}", verifySignResult);
    }

    @org.junit.Test
    public void testJMSign() {
        String encryptionContent = "待加密数据";
        // 第一步，生成一对公私钥
        Map<String, Key> keys = new HashMap<>(2);
        try {
            keys = RSAUtil.initKey();
        }catch (Exception e){
            log.error("init RSA key error，message is {}", e.getMessage());
            System.exit(-1);
        }

        // 获得公钥
        Key publicKey = keys.get(RSAUtil.PUBLIC_KEY);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        log.info(">>>base64 publicKey String is:{}", base64PublicKeyStr);

        // 加密
        String encryptionString = RSAUtil.encryptByPublicKey(encryptionContent, base64PublicKeyStr);
        log.info(">>>encryptionString by publicKey ,encryptionString is: {}", encryptionString);

        // 加密之后用私钥进行签名
        String signString = RSAUtil.signByPrivateKey(encryptionString, Base64.encodeBase64String( keys.get(RSAUtil.PRIVATE_KEY).getEncoded()));
        log.info(">>>sign by privateKey ,signString is: {}", signString);


        // 用公钥进行验签
        boolean verifySignResult = RSAUtil.verifySignByPublicKey(encryptionString ,signString, base64PublicKeyStr);
        log.info(">>>>verify sign result is: {}", verifySignResult);


        // 解密
        // 获得私钥
        Key privateKey = keys.get(RSAUtil.PRIVATE_KEY);
        // 私钥Base64编码字符串
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        log.info(">>>base64 privateKey String is:{}", base64PrivateKeyStr);

        String decryptionString = RSAUtil.decryptByPrivateKey(encryptionString, base64PrivateKeyStr);
        log.info(">>>decryptionString by privateKey ,decryptionString is: {}", decryptionString);

    }

}

package rsa;
import org.springframework.util.Base64Utils;

public class RSA {
    /**
     * 加密
     * @param json 加密前数据
     * @param key 公钥
     * @return 加密后的数据
     */
    public static String encode(String json,String key){
        //加密请求参数data
        if(null == json) {
            return null;
        }
        byte[] bytes = RSACoder.encryptByPublicKey(json.getBytes(), key);
        String data = RSACoder.encryptBASE64(bytes);
        return data;
    }

    /**
     * 解密
     * @param resultData  http请求返回的加密data数据
     * @param privateKey 私钥
     * @return
     */
    public static String decode(String resultData,String privateKey){
        byte[] bytes = RSACoder.decryptByPrivateKey(RSACoder.decryptBASE64(resultData), privateKey);
        try {
            return new String(bytes,"UTF-8");
        } catch (Exception e) {
            return new String(bytes);
        }
    }


    /**
     * 签名校验
     * @param resultData  http请求返回的加密data数据
     * @param encodeSign  http请求返回的签名sign字段
     * @return
     */
    public static boolean verify(String resultData,String encodeSign,String key){
        if(null == encodeSign || encodeSign.trim().length() < 1) {
            return false;
        }
        encodeSign = encodeSign.trim();
        String sign = new String(Base64Utils.decodeFromString(encodeSign));
        return RSACoder.verify(RSACoder.decryptBASE64(resultData), key, sign);
    }

}


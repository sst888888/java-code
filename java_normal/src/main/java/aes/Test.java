package aes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import rsa.RSAUtil;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class Test {

    private static String content = "待加密数据13056897865";

    static {
        Map<String, String> map = Maps.newHashMap();
        BankCard bankCard = new BankCard();
        bankCard.setName("中国银行");
        bankCard.setAcctNo("622222228888812356789");
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("bankCard", bankCard);
        content = jsonObj.toJSONString();
        content = bankCard.toString();
        ObjectMapper mapper  = new ObjectMapper();
        try {
            content = mapper.writeValueAsString(bankCard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void testAes() {
        String content = Test.content;

        String aesKey = AesUtil.genAesKey();

        String encrypt = AesUtil.encryptToBase64(content, aesKey);

        System.out.println(">>>1.encrypt is " + encrypt);

        String decrypt = AesUtil.decryptFormBase64ToString(encrypt, aesKey);
        System.out.println(">>>2.decrypt is " + decrypt);
    }

    @org.junit.Test
    public void testAesRsa(){
        // 1.随机生成AES秘钥 aesKey
        String aesKey = AesUtil.genAesKey();
        log.info(">>>1.aesKey is:{}", aesKey);

        // 2.对明文AES加密
        String encrypt = AesUtil.encryptToBase64(content, aesKey);

        // 3.RSA 公钥
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
        log.info(">>>2.base64 publicKey String is:{}", base64PublicKeyStr);

        // 4.RSA 公钥加密AES密钥 aesKey
        String rsaAesKey = RSAUtil.encryptByPublicKey(aesKey, base64PublicKeyStr);

        // 5. RSA 私钥
        Key privateKey = keys.get(RSAUtil.PRIVATE_KEY);
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());

        // 6.RSA 解密 AES密钥
        String decryptAesKey = RSAUtil.decryptByPrivateKey(rsaAesKey, base64PrivateKeyStr);
        log.info(">>>3.decryptAesKey is:{}", decryptAesKey);

        // 7.AES 解密得到原始内内容
        String decryptBody = AesUtil.decryptFormBase64ToString(encrypt, decryptAesKey);
        log.info(">>>4.decryptBody is:{}", decryptBody);

        JSONObject bankCardJson = JSONObject.parseObject(decryptBody);
        BankCard bankCard = JSON.toJavaObject(bankCardJson, BankCard.class);
        log.info(">>>5.BankCard.name is {}", bankCard.getName());
        log.info(">>>6.BankCard.acctno is {}", bankCard.getAcctNo());

        Map<String, Object> bodyMap = JsonUtil.toMap(decryptBody);

        Map<String, String> parameters = bodyMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                e -> Objects.isNull(e.getValue()) ? null : String.valueOf(e.getValue())));
        log.info(">>>7.双层解析后的 Map: {}", parameters);
        String acctNo = parameters.get("acctNo");
        String name = parameters.get("name");
        log.info(">>>8.acctNo is {}", acctNo);
        log.info(">>>9.name is {}", name);
        String card = parameters.get("bankCard");
        log.info(">>>10.card is {}", card);

    }

}

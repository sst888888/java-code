package rsa.secret;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Base64Utils;

public class RSACoderTest {
    /*
     * 非对称加密算法   RSA过程 ： 以甲乙双方为例
     *      1、初始化密钥 构建密钥对,生成公钥、私钥保存到keymap中
     *              KeyPairGenerator --->    KeyPair     -->      RSAPublicKey、RSAPrivateKey
     *      2、甲方使用私钥加密, 加密后在用私钥对加密数据进行数据签名，然后发送给乙方
     *              RSACoder.encryptByPrivateKey(data, privateKey);
     *              RSACoder.sign(encodedData, privateKey);
     *      3、乙方则通过公钥验证签名的加密数据，如果验证正确则在通过公钥对加密数据进行解密
     *              RSACoder.verify(encodedData, publicKey, sign);
     *              RSACoder.decryptByPublicKey(encodedData, publicKey);               
     *
     *      4、乙方在通过公钥加密发送给甲方
     *              RSACoder.encryptByPublicKey(decodedData, publicKey);
     *      5、甲方通过私钥解密该数据      
     *              RSACoder.decryptPrivateKey(encodedData, privateKey);               
     */
    public static void main(String[] args) throws Exception{
        //1.生成密钥对
//        Map<String , Object> keyMap = RSACoder.initKey();
//        String publicKey = RSACoder.getPublicKey(keyMap);
//        String privateKey = RSACoder.getPrivateKey(keyMap);
//
//        System.out.println("公钥：\n" + publicKey);
//        System.out.println("私钥：\n" + privateKey);

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgZpPquRQ1nZ/+wG5FNCKtWqIeEE2cocpj8JHH5YuLu36TDX2jye/qYVwDNd48yCCxHovmheHNtOcL+rtUa5soRucJoDAcArK3eX3D68KHxZULo07okbmHTiE0y1B+9fQrDEu0f/3GD/nLX/z+yZ6RyYzbyVxuAzBgIqZV+RMKKwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBmk+q5FDWdn/7AbkU0Iq1aoh4QTZyhymPwkcfli4u7fpMNfaPJ7+phXAM13jzIILEei+aF4c205wv6u1RrmyhG5wmgMBwCsrd5fcPrwofFlQujTuiRuYdOITTLUH719CsMS7R//cYP+ctf/P7JnpHJjNvJXG4DMGAiplX5EworAgMBAAECgYANlgKrW/fRPsOhgU0VwX+6bTXUFA9u1+lbo7HFW7UBJeVnsTnWGLGgiZSQlQx4236hYfP5l5dzpAH56sfZM7AViyI2+uxnj44GE2hfYizILjHHyCIepqtB/2TJkM0EDKuCjWfqZ56Q9L7gcL4560tNZle7UnSRKsXHMHtp4rRaiQJBAPt+GOzLF8j18FmM4VFItHdLGgvWh2W2PkDl7oAU4YSlgrgHtZikxRP+RM/TkRhFcEnpcTiznBCblBlXXhJ6Vw8CQQCjRof1+JI5Yzvr2m931XHzwuluzIIn6Zz0wL3N25rIQnjguAlwpEmmz4VKE//Q7byQJHgpRLc6zeSDTvOPbDslAkBgr/4NK5edX1BkXVFS2sznACynWHb9l68fbEbhXyXExY0YC365jL9oDF2QORqTZj7ha6pnzkW17NpnU7uLBxXpAkAyLP6jTFxDXoePl3Uh8mouavupnRdyhIxDeufZV19do2/aPzRHdXVc2fEwLq0y25xS/6IdrIxSeet7nsuii7QxAkEAqMTDal/0Rqne9cUfdMOnZoyQmC0+Dtm/rmJDmOFBSQ1kC2GkyWD3D0xoiAywi9dkfetAdVt9F2LRPpwVK3qvNA==";

//        VideoRequest request = new VideoRequest();
//        request.setId(1790389729132437505L);
//        request.setTitle("ces");
//        request.setCategory(1787395967683739649L);
//        request.setTransStartTime(LocalDateTime.now());
//        request.setTransEndTime(LocalDateTime.now());
//        List<VideoImgEntity> videoImgEntities = new ArrayList<>();
//        VideoImgEntity videoImgEntity = new VideoImgEntity();
//        videoImgEntity.setVideoId(1790389729132437505L);
//        videoImgEntity.setImgUrl("/img.01.jpg");
//        videoImgEntities.add(videoImgEntity);
//        request.setVideoImgList(videoImgEntities);
//
//        List<VideoDetailEntity> videoDetailEntities = new ArrayList<>();
//        VideoDetailEntity videoDetailEntity = new VideoDetailEntity();
//        videoDetailEntity.setVideoId(1790389729132437505L);
//        videoDetailEntity.setVideoUrl("/img.01.jpg");
//
//        videoDetailEntities.add(videoDetailEntity);
//        request.setVideoDetailList(videoDetailEntities);

        System.out.println("-----------乙方生成请求数据------------");
        String requestDataStr = "KICAGnhnf6JIe7YG9SI2hm5VwDvyCMCMNWa38NhJXA5aZeeTXKhYitExxInfbIzUZJpAp3cM82kmZ2vbRHAYUGn4K5368kCbb16";
//        String requestDataStr = JsonUtil.toJson(request);
        //1. 乙方使用甲方颁发给乙方的公钥加密数据
        byte[] requestData = requestDataStr.getBytes();
        byte[] requestEncodedData = RSACoder.encryptByPublicKey(requestData, publicKey);
        
        System.out.println("******* 加密前的请求数据：" + requestDataStr);
        
        String base64Encode = Base64Utils.encodeToString(requestEncodedData);
        
        System.out.println("******* 加密后的请求数据：" + new String(base64Encode));

        //2. 甲方使用该公钥对应的私钥解密数据
        byte[] base64Decode = Base64Utils.decodeFromString(base64Encode);
        byte[] decodedData = RSACoder.decryptPrivateKey(base64Decode, privateKey);
        System.out.println("----------- 解密后的数据： " + new String(decodedData));
//        VideoRequest parse = JsonUtil.parse(new String(decodedData), VideoRequest.class);


//        //3.甲方对数据进行加密
//        String responseDataStr = "KICAGnhnf6JIe7YG9SI2hm5VwDvyCMCMNWa38NhJXA5aZeeTXKhYitExxInfbIzUZJpAp3cM82kmZ2vbRHAYUGn4K5368kCbb16";
//        byte[] responseData = responseDataStr.getBytes();//每次的得到的字节数组是不一样的。
//        System.out.println("----------- 加密前的甲方响应数据: " + responseDataStr + "------------" );
//        //4.1 甲方私钥加密
//        byte[] encodedData = RSACoder.encryptByPrivateKey(responseData, privateKey);
//        System.out.println("----------- 甲方用私钥加密 ------------" );
//        //4.2 甲方私钥进行数据签名
//        String sign = RSACoder.sign(encodedData, privateKey);
//        System.out.println("----------- 甲方用私钥签名sign: ");
//        System.out.println(sign);
//        System.out.println(" ------------");
//        String encodeSign = Base64Util.encode(sign);
//        System.out.println("----------- 甲方用私钥签名，经base64编码后，encodeSign: ");
//        System.out.println(encodeSign);
//        System.out.println(" ------------");
//
//        //5.甲方执行数据发送
//        System.out.println("-----------甲方将私钥加密数据发送至乙方------------");
//
//
//        //6. 乙方使用公钥验证数字签名并解密
//        decrypt( encodedData,  publicKey,  encodeSign );

    }

    public static void decrypt(byte[] encodedData, String publicKey, String encodeSign ) {
        System.out.println("******* 乙方收到的签名字符串，encodeSign:");
        System.out.println(encodeSign);
        System.out.println("*******");
        String sign = Base64Util.decode(encodeSign);
         System.out.println("******* 乙方收到的签名字符串，经base64解码后，sign: ");
         System.out.println( sign );
         System.out.println("*******");

        //1.先验签
        boolean flag = RSACoder.verify(encodedData, publicKey, sign);
        System.out.println("******* 乙方用公钥进行验签，flag:" + flag + " *******");
        if(flag) {
            //2.用公钥对数据解密
            byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
            System.out.println("******* 乙方解密后数据： " + new String(decodedData) + " *******");
        }
    }
    
    
    public static void generateKey() {

        List<Map> mapList = new ArrayList<>();
        for(int i=0; i<10;i++) {
            Map<String , Object> keyMap = RSACoder.initKey();
            String publicKey = RSACoder.getPublicKey(keyMap);
            String privateKey = RSACoder.getPrivateKey(keyMap);

            System.out.println("公钥：\n" + publicKey);
            System.out.println("私钥：\n" + privateKey);

            Map<String, Integer> lengthMap = new HashMap<>();
            lengthMap.put("publicKey", publicKey.length());
            lengthMap.put("privateKey", privateKey.length());
            mapList.add(lengthMap);
        }
        for(Map map :mapList) {
            System.out.println("公钥：" + map.get("publicKey"));
            System.out.println("私钥：" + map.get("privateKey"));
        }
    }
    
    
}
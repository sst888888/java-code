package rsa.encrypt;

import org.springframework.util.Base64Utils;

import java.util.Map;

/**
 * @author: cp
 * @date: 2024-05-15 13:00
 */
public class Test {

    public static void main(String[] args) {

        //1.生成密钥对
        Map<String , Object> keyMap = RSACoder.initKey();
        String publicKey = RSACoder.getPublicKey(keyMap);
        String privateKey = RSACoder.getPrivateKey(keyMap);

        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgZpPquRQ1nZ/+wG5FNCKtWqIeEE2cocpj8JHH5YuLu36TDX2jye/qYVwDNd48yCCxHovmheHNtOcL+rtUa5soRucJoDAcArK3eX3D68KHxZULo07okbmHTiE0y1B+9fQrDEu0f/3GD/nLX/z+yZ6RyYzbyVxuAzBgIqZV+RMKKwIDAQAB";
//        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBmk+q5FDWdn/7AbkU0Iq1aoh4QTZyhymPwkcfli4u7fpMNfaPJ7+phXAM13jzIILEei+aF4c205wv6u1RrmyhG5wmgMBwCsrd5fcPrwofFlQujTuiRuYdOITTLUH719CsMS7R//cYP+ctf/P7JnpHJjNvJXG4DMGAiplX5EworAgMBAAECgYANlgKrW/fRPsOhgU0VwX+6bTXUFA9u1+lbo7HFW7UBJeVnsTnWGLGgiZSQlQx4236hYfP5l5dzpAH56sfZM7AViyI2+uxnj44GE2hfYizILjHHyCIepqtB/2TJkM0EDKuCjWfqZ56Q9L7gcL4560tNZle7UnSRKsXHMHtp4rRaiQJBAPt+GOzLF8j18FmM4VFItHdLGgvWh2W2PkDl7oAU4YSlgrgHtZikxRP+RM/TkRhFcEnpcTiznBCblBlXXhJ6Vw8CQQCjRof1+JI5Yzvr2m931XHzwuluzIIn6Zz0wL3N25rIQnjguAlwpEmmz4VKE//Q7byQJHgpRLc6zeSDTvOPbDslAkBgr/4NK5edX1BkXVFS2sznACynWHb9l68fbEbhXyXExY0YC365jL9oDF2QORqTZj7ha6pnzkW17NpnU7uLBxXpAkAyLP6jTFxDXoePl3Uh8mouavupnRdyhIxDeufZV19do2/aPzRHdXVc2fEwLq0y25xS/6IdrIxSeet7nsuii7QxAkEAqMTDal/0Rqne9cUfdMOnZoyQmC0+Dtm/rmJDmOFBSQ1kC2GkyWD3D0xoiAywi9dkfetAdVt9F2LRPpwVK3qvNA==";

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
//        System.out.println("parse=" + parse);
    }
}

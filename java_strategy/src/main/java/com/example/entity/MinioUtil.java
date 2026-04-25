package com.example.entity;


import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.apache.http.entity.ContentType;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * Minio 文件存储工具类
 *
 * @author ruoyi
 */
public class MinioUtil {
    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param fileName
     *
     * @throws IOException
     */
    public static String uploadFile(String bucketName,
                                    String fileName,
                                    MultipartFile multipartFile) throws IOException {
        String url;
        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .method(Method.GET)
                    .build());
            url = url.substring(0, url.indexOf('?'));
            url =  new URL(url).getPath();
            return ServletUtils.urlDecode(url);
        }
        catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**

     * 上传微信头像

     *

     * @param url 网络图片地址

     * @return

     */

//    public static String uploadFile(String bucketName,String url) {
//        MinioClient minioClient = SpringUtils.getBean(MinioClient.class);
//        if (StringUtils.isBlank(url)) {
//            return null;
//        }
//        try {
//            InputStream inputStreamImg = ImageUtils.readFileInputStream(url);
//            // 判断上传文件是否为空
//            if (null == inputStreamImg) {
//                return null;
//            }
//            // uuid 生成文件名
//            String uuid = String.valueOf(UUID.randomUUID());
//            // 新的文件名
//            String fileName = "img/" + DateUtils.dateToStr(new Date(),"yyyMMdd") + "/" + uuid + ".jpg";
//
//            MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
//                    //MediaType.IMAGE_PNG_VALUE //PNG类型的图片，所以用这个枚举。点击MediaType有对应类型
//                    ContentType.APPLICATION_OCTET_STREAM.toString()
//                    , inputStreamImg);
//
//            // 开始上传
//            try (InputStream inputStream = multipartFile.getInputStream()) {
//                minioClient.putObject(PutObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .stream(inputStream, multipartFile.getSize(), -1)
//                        .contentType(multipartFile.getContentType())
//                        .build());
//                url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .method(Method.GET)
//                        .build());
//                url = url.substring(0, url.indexOf('?'));
//                return ServletUtils.urlDecode(url);
//            }
//            catch (Exception e) {
//                throw new IOException(e.getMessage(), e);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//

}


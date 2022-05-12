package com;

import pojo.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @ClassName testIo
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/18 21:32
 * @Version 1.0
 **/
public class testIo {

    public static void main(String[] args) {
        User user = new User();
        user.setPassword("test");
        user.setUsername("test");

        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byte[] username = user.getUsername().getBytes();
        byte[] password = user.getPassword().getBytes();
        byteBuffer.putInt(username.length);
        byteBuffer.put(username);
        byteBuffer.putInt(password.length);
        byteBuffer.put(password);

        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        System.out.println("ByteBuffer 字节码长度：" + bytes.length + "\n");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(user);

            byte[] testByte = os.toByteArray();
            System.out.println("ObjectOutputStream 字节码长度：" + testByte.length + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

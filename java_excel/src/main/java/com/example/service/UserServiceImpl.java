package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.entity.User;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUser {

    @Override
    public boolean saveData(List<User> users) {

        log.info("UserService {}条数据，开始存储数据库！", users.size());
        int count=0;
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (User user : users) {
            String phone1 = user.getPhone1();
            if (StringUtil.isNotBlank(phone1)) {
                temp.add(phone1);
            }
        }

        for (User user : users) {
            String phone2 = user.getPhone2();
            if (temp.contains(phone2)) {
                count++;
            }
            if (StringUtil.isNotBlank(phone2) && !temp.contains(phone2)) {
                result.add(phone2);
            }
        }

        log.info("相同手机号码个数为{}", count);

        log.info("手机号码2个数为{}", result.size());
        log.info("UserService 存储数据库成功！");

        try {
            // 获取当前日期，格式 yyyy-MM-dd
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 构建文件路径
            String filePath = "/Users/andongni/Documents/my/" + date + ".txt";


            Files.write(
                    Paths.get(filePath),
                    result,
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        log.info("UserService {}条数据，开始存储数据库！", users.size());
//        log.info(JSONUtil.toJsonStr(users));
//        log.info("UserService 存储数据库成功！");
        return true;
    }
}



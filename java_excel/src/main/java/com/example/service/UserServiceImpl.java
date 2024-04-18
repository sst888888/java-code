package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUser {
    @Override
    public boolean saveData(List<User> users) {
        log.info("UserService {}条数据，开始存储数据库！", users.size());
        log.info(JSONUtil.toJsonStr(users));
        log.info("UserService 存储数据库成功！");
        return true;
    }
}

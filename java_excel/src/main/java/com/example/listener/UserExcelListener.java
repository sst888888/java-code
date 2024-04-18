package com.example.listener;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.entity.User;
import com.example.service.IUser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserExcelListener extends AnalysisEventListener<User> {

    private IUser iUser;

    public UserExcelListener(IUser iUser){
        this.iUser = iUser;
    }

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 2;
    List<User> list = new ArrayList<User>(BATCH_COUNT);
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(user));
        list.add(user);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
        iUser.saveData(list); //调用 userService 中的 saveData 方法
    }


}

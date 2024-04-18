package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.listener.UserExcelListener;
import com.example.entity.User;
import com.example.service.IUser;
import com.example.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@RestController
@Slf4j
public class excelController {

    @Autowired
    private IUser iUser;

    @RequestMapping(value = "/excel/read", method = RequestMethod.POST)
    public String read(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        EasyExcelFactory.read(uploadFile.getInputStream(), User.class, new UserExcelListener(iUser)).sheet().doRead();

        return "ok";
    }


    @RequestMapping(value = "/excel/read2", method = RequestMethod.POST)
    public String read2(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        AnalysisEventListener<User> userAnalysisEventListener = ExcelUtil.getListener(this.batchInsert(), 2);
        EasyExcelFactory.read(uploadFile.getInputStream(), User.class, userAnalysisEventListener).sheet().doRead();

        return "ok";
    }


    private Consumer<List<User>> batchInsert(){
        return users -> iUser.saveData(users);
    }

}

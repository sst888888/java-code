package com.bootkafka.springbootkafkademo.controller.origin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.entity.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController

@Slf4j
@RequestMapping("/origin/sign")
public class ExcelController {


    @PostMapping(value = "/exe")
    public Boolean exe(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename name is {}", originalFilename);

        assert originalFilename != null;
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        ExeService exeService = ExeFactory.getByFileName(fileName);
        if (null == exeService) {
            log.error("exeService is null. please check upload file name.");
        }
        assert exeService != null;
        return exeService.exe(file);
    }


}

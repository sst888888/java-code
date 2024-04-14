package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@Slf4j
public class uploadController {


    @RequestMapping(value = "/test/file", method = RequestMethod.POST)
    public String file(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        String name = uploadFile.getOriginalFilename();
        long size = uploadFile.getSize();
        return "ok";
    }

}

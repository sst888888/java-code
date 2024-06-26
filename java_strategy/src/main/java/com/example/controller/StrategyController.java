package com.example.controller;

import com.example.cancel.CancelOrderService;
import com.example.cancel.CancelOrderServiceStrategyFactory;
import com.example.entity.UploadFile;
import com.example.release.ReleaseDTO;
import com.example.release.ReleaseHandler;
import com.example.release.ReleaseHandlerFactory;
import com.example.risk.ReqVo;
import com.example.risk.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@Slf4j
public class StrategyController {


    @RequestMapping(value = "/test/cancel/{category}", method = RequestMethod.GET)
    public String cancel(@PathVariable("category") int category) {
        CancelOrderService cancelOrderService = CancelOrderServiceStrategyFactory.getByCategory(category);
        cancelOrderService.exeCancelOrder();
        return "ok";
    }

    @RequestMapping(value = "/test/release/{category}", method = RequestMethod.GET)
    public String release(@PathVariable("category") int category) {
        ReleaseHandler releaseHandler = ReleaseHandlerFactory.produce(category);
        ReleaseDTO releaseDTO = new ReleaseDTO();
        releaseDTO.setCategory(100); // 该值不影响已产生的releaseHandler
        releaseHandler.handle(releaseDTO);
        return "ok";
    }

    @RequestMapping(value = "/test/risk/{category}", method = RequestMethod.GET)
    public String risk(@PathVariable("category") String category) {
        ReqVo reqVo = new ReqVo();
        reqVo.setCategory(category);
        StrategyHandler.exe(reqVo);
        return "ok";
    }


    @RequestMapping(value = "/test/file", method = RequestMethod.POST)
    public String file(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        String name = uploadFile.getOriginalFilename();
//        uploadFile.getSize()
        return "ok";
    }

}

package com.example.controller;

import com.example.cancel.CancelOrderService;
import com.example.cancel.CancelOrderServiceStrategyFactory;
import com.example.release.ReleaseDTO;
import com.example.release.ReleaseHandler;
import com.example.release.ReleaseHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

}

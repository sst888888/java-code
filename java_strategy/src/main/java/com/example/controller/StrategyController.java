package com.example.controller;

import com.example.cancel.CancelOrderService;
import com.example.cancel.CancelOrderServiceStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cp
 * @date: 2023-09-24 22:33
 */
@RestController
@Slf4j
public class StrategyController {

    @RequestMapping(value = "/test/cancel/{category}", method = RequestMethod.GET)
    public String cancel(@PathVariable("category") int category) {
        CancelOrderService cancelOrderService = CancelOrderServiceStrategyFactory.getByCategory(category);
        cancelOrderService.exeCancelOrder();
        return "ok";
    }
}

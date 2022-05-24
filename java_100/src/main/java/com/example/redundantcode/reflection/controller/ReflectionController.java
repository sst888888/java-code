package com.example.redundantcode.reflection.controller;


import com.example.redundantcode.reflection.right.BetterBankService;
import com.example.redundantcode.reflection.wrong.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("reflection")
public class ReflectionController {

    @PostMapping("/bank/createUser")
    public String createUser(@RequestBody String data) {
        log.info("createUser called with argument {}", data);
        return "createUser ok";
    }

    @PostMapping("/bank/pay")
    public String pay(@RequestBody String data) {
        log.info("pay called with argument {}", data);
        return "pay ok";
    }

    @GetMapping("wrong")
    public void wrong() throws IOException {
        BankService.createUser("ssting", "xxxxxxxxxxxxxxxxxx", "13056898888", 36);
        BankService.pay(1234L, new BigDecimal("100.5"));

    }

    @GetMapping("right")
    public void right() throws IOException {
       BetterBankService.createUser("ssting", "xxxxxxxxxxxxxxxxxx", "13056898888", 36);
       BetterBankService.pay(1234L, new BigDecimal("100.5"));
    }
}

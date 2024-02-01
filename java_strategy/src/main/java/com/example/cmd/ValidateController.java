package com.example.cmd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ValidateController {

    @RequestMapping(value = "/test/validate", method = RequestMethod.GET)
    public String validate() {
            new ValidatePluginExecute().execute();
        return "ok";
    }

}

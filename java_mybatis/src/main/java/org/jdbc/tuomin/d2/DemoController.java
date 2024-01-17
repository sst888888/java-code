package org.jdbc.tuomin.d2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensitive")
public class DemoController {

    @GetMapping
    public DemoInfo getUserInfo(){
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.setName("cs001");
        demoInfo.setIdNum("123456789123456789");
        demoInfo.setPhone("13818888888");
        demoInfo.setAddress("深圳市南山区深圳湾三号6606");
        demoInfo.setPassword("abc123qwe@");
        return demoInfo;
    }

}

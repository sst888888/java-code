package org.jdbc.tuomin.d1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desensitized")
public class DesensitizedController {

    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1).setUserName("zhangsan").setNickName("张三")
                .setIdCard("450218199103458901")
                .setPhone("13456789012");
        return userInfo;
    }
}

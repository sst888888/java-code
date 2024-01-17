package org.jdbc.tuomin.d1;

import com.example.tuomin.Sensitive;
import com.example.tuomin.SensitiveTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class UserInfo implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    @JsonSensitive(strategy = DesensitizedStrategy.USER_NAME)
    private String nickName;

    /**
     * 手机号
     */
    @JsonSensitive(strategy = DesensitizedStrategy.PHONE)
    @Sensitive(type = SensitiveTypeEnum.PHONE_NUM)
    private String phone;

    /**
     * 身份证号
     */
    @JsonSensitive(strategy = DesensitizedStrategy.ID_CARD)
    private String idCard;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GTM+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}

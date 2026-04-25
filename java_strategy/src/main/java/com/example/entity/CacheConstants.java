package com.example.entity;

/**
 * 缓存的key 常量
 */
public class CacheConstants
{

    /**
     * 代理登录用户 redis key
     */
    public static final String LOGIN_AGENT_TOKEN_KEY = "gold_login_agent_tokens:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "gold_login_tokens:";

    /**
     * App 登录用户 redis key
     */
    public static final String LOGIN_APP_TOKEN_KEY = "gold_login_app_tokens:";
    /**
     * App 登录用户 redis key
     */
    public static final String LOGIN_APP_USER_ID = "gold_login_app_user_id:";

    /**
     * 登录用户id redis key 用于实现灵活控制多设备登录
     */
    public static final String LOGIN_USER_ID_KEY = "gold_login_user_ids:";


    /**
     * 注册验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "gold_register_captcha_codes:";


    /**
     * 密码修改验证码 redis key
     */
    //public static final String PASSWORD_UPDATE_CAPTCHA_CODE_KEY = "password_update_captcha_codes:";

    /**
     *  绑定交易密码 修改交易密码  redis key
     */
    //public static final String BIND_UPDATE_WITHDRAW_PASSWORD_CAPTCHA_CODE_KEY = "bind_update_withdraw_password_captcha_codes:";

    /**
     * 注册验证码 redis key
     */
    public static final String EMAIL_CODE_KEY = "gold_email_captcha_codes:";



    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "gold_sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "gold_sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "gold_repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "gold_rate_limit:";



    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_IMG_CODE_KEY = "sms_img_captcha_codes:";


    public static final String INVITE_CODE_SET = "goleinvite_code_set";
    public static final String CardNoGenera_SET = "CardNoGeneraSET";
}

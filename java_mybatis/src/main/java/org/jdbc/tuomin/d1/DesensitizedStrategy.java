package org.jdbc.tuomin.d1;


public enum DesensitizedStrategy {

    /**
     * 用户信息脱敏
     */
    USER_NAME(s -> s.replaceAll("(\\S)\\S(\\S*)", "$1*$2")),
    /**
     * 身份证信息脱敏
     */
    ID_CARD(s -> s.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2")),
    /**
     * 手机号脱敏
     */

    PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),

    ;

    private final Desensitized desensitized;

    DesensitizedStrategy(Desensitized desensitized){
        this.desensitized = desensitized;
    }

    public Desensitized getDesensitize() {
        return desensitized;
    }

}

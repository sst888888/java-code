package org.jdbc.tuomin.d2;


public class DesensitizedUtils {

    private DesensitizedUtils() {};

    public static String desValue(String origin, int prefixLen, int suffixLen, String maskStr) {
        if (origin == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = origin.length(); i < n; i++) {
            if (prefixLen <= i && i <= (n - suffixLen - 1)) {
                sb.append(maskStr);
            } else {
                sb.append(origin.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * 【中文姓名】只显示最后一个汉字，其他隐藏为星号，比如：**梦
     *
     * @param fullName 姓名
     * @return 结果
     */
    public static String chineseName(String fullName) {
        if (fullName == null) {
            return null;
        }
        return desValue(fullName, 1, 0, "*");
    }

    /**
     * 【身份证号】显示前4位, 后2位，其他隐藏。
     *
     * @param id 身份证号码
     * @return 结果
     */
    public static String idCardNum(String id) {
        return desValue(id, 4, 2, "*");
    }

    /**
     * 【手机号码】前三位，后四位，其他隐藏。
     *
     * @param num 手机号码
     * @return 结果
     */
    public static String mobilePhone(String num) {
        return desValue(num, 3, 4, "*");
    }

}

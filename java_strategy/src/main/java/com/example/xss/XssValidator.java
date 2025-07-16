package com.example.xss;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义xss校验注解实现
 *
 * @author ruoyi
 */
public class XssValidator implements ConstraintValidator<Xss, Object> {
    private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

    @Override
    public boolean isValid(Object strvalue, ConstraintValidatorContext constraintValidatorContext) {
        if (strvalue == null) {
            return true;
        }
        String value = strvalue.toString();
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            return true;
        }

        ParamSecurityChecker.checksql(value);
        return !containsHtml(value);
    }

    public static boolean containsHtml(String value) {
        Pattern pattern = Pattern.compile(HTML_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
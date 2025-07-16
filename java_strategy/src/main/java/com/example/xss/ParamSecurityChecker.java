package com.example.xss;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ParamSecurityChecker {

    // SQL注入检测正则（示例：检测常见SQL关键字）
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile("(?i)(\\b(SELECT|DELETE|UPDATE||UNION|INSERT|EXEC|ALTER|DROP)\\b)|([';]--|/\\*|\\*/|@@|0x|\\bOR\\b|=)\n");

    // 非法字符检测正则（根据需求自定义）
    private static final Pattern ILLEGAL_CHAR_PATTERN = Pattern.compile("[<>\"'&]|script" // 示例：检测HTML标签和脚本
    );

    public static void check(String value)  {
        if (value == null) return;
        try {
            CCJSqlParserUtil.parse(value);
            throw new InvalidParameterException("参数包含非法SQL关键字: " + value);
        } catch (JSQLParserException e) {
        }

        if (ILLEGAL_CHAR_PATTERN.matcher(value).find()) {
            throw new InvalidParameterException("参数包含非法字符: " + value);
        }
    }

    public static void checksql(String value)  {
        if (value == null) return;
        try {
            CCJSqlParserUtil.parse(value);
            throw new InvalidParameterException("参数包含非法SQL关键字: " + value);
        } catch (JSQLParserException e) {
        }

    }
}

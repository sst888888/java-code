package org.cp.springframework.test;

import org.cp.springframework.context.support.ClassPathXmlApplicationContext;
import org.cp.springframework.core.convert.converter.Converter;
import org.cp.springframework.core.convert.support.StringToNumberConverterFactory;
import org.cp.springframework.test.bean.Husband;
import org.cp.springframework.test.converter.StringToIntegerConverter;
import org.junit.Test;

/**
 * @author: cp
 * @date: 2024-11-07 18:00
 */
public class Xml18Test {
    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring18.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }

    @Test
    public void test_StringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println("测试结果：" + num);
    }

    @Test
    public void test_StringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        System.out.println("测试结果：" + stringToIntegerConverter.convert("1234"));

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        System.out.println("测试结果：" + stringToLongConverter.convert("1234"));
    }
}

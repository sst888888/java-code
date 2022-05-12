package com.util;

import com.google.common.collect.Lists;
import com.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName JsonUtil
 * @Description
 * @Author Chaiphat
 * @Date 2020/10/18 17:14
 * @Version 1.0
 **/
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        // 取消默认的转换TIMESTAMPS形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空Bean装Json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 忽略在Json字符串中存在，但是在java对象中不存在对象属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 所有的日期格式都统一
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }


    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            return null;
        }

    }

    /**
     * 处理复杂对象   将strList转换为目标list
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }

        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
        }

        return null;
    }

    /**
     * 处理复杂对象   将strList转换为目标list
     * @param str
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
        }

        return null;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setName("sst");
        String s1 = JsonUtil.obj2String(user);
        String s2 = JsonUtil.obj2StringPretty(user);
        log.info("s1:{}", s1);
        log.info("s2:{}", s2);

        User string2Obj = JsonUtil.string2Obj(s1, User.class);
        log.info("string2Obj:{}", string2Obj);


        List<User> userList = Lists.newArrayList();
        User user1 = new User();
        user1.setId(2L);
        user1.setName("hhh");

        User user2 = new User();
        user2.setId(3L);
        user2.setName("ggg");
        userList.add(user1);
        userList.add(user2);

        String userListStr = JsonUtil.obj2StringPretty(userList);
        List<User> users = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
        });

        List<User> userListObj2 = JsonUtil.string2Obj(userListStr, List.class, User.class);

        return;
    }
}

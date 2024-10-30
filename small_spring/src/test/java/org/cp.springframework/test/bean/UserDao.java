//package org.cp.springframework.test.bean;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author: cp
// * @date: 2024-10-27 15:14
// */
//public class UserDao {
//
//    private static Map<String, String> hashMap = new HashMap<>();
//
//    static {
//        hashMap.put("10001", "小哥");
//        hashMap.put("10002", "张三");
//        hashMap.put("10003", "阿毛");
//    }
//
//    public String queryUserName(String uId) {
//        return hashMap.get(uId);
//    }
//
//
//    public void initDataMethod(){
//        System.out.println("执行：init-method");
//        hashMap.put("10001", "小哥");
//        hashMap.put("10002", "张三");
//        hashMap.put("10003", "阿毛");
//    }
//
//    public void destroyDataMethod(){
//        System.out.println("执行：destroy-method");
//        hashMap.clear();
//    }
//
//
//}

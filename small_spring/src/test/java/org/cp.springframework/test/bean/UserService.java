package org.cp.springframework.test.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.cp.springframework.beans.BeansException;
import org.cp.springframework.beans.factory.*;
import org.cp.springframework.context.ApplicationContext;
import org.cp.springframework.context.ApplicationContextAware;

/**
 * @author: cp
 * @date: 2024-10-25 20:36
 */
//@Data
//@Setter
//@Getter
//public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
//    private ApplicationContext applicationContext;
//    private BeanFactory beanFactory;
//
//
//    private String uId;
//    private String name;
//
//    private String company;
//    private String location;
//
//    public UserService() {
//    }
//
//    public UserService(String name) {
//        this.name = name;
//    }
//
//    public void queryUserInfo() {
//        System.out.println("查询用户信息：" + name);
//    }
//
//    public String queryUserInfo07() {
//        return userDao.queryUserName(uId) + "," + company + "," + location;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("");
//        sb.append("").append(name);
//        return sb.toString();
//    }
//
//    public String queryUserInfo2() {
//        return userDao.queryUserName(uId);
//    }
//
//    private IUserDao userDao;
//
//
//    public void queryUserInfoByDao() {
//        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
//    }
//
//    public String getuId() {
//        return uId;
//    }
//
//    public void setuId(String uId) {
//        this.uId = uId;
//    }
//
//    public IUserDao getUserDao() {
//        return userDao;
//    }
//
//    public void setUserDao(IUserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("执行：UserService.afterPropertiesSet");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("执行：UserService.destroy");
//    }
//
//    @Override
//    public void setBeanName(String name) {
//        System.out.println("Bean Name is：" + name);
//    }
//
//    @Override
//    public void setBeanClassLoader(ClassLoader classLoader) {
//        System.out.println("ClassLoader：" + classLoader);
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}


public class UserService {

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
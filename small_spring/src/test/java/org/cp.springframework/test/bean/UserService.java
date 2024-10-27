package org.cp.springframework.test.bean;

/**
 * @author: cp
 * @date: 2024-10-25 20:36
 */
public class UserService {

    private String uId;
    private String name;

    private String company;
    private String location;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    public String queryUserInfo07() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }

    public String queryUserInfo2() {
        return userDao.queryUserName(uId);
    }

    private UserDao userDao;

    public void queryUserInfoByDao() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

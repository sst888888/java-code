package pojo;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/18 21:33
 * @Version 1.0
 **/
public class User implements Serializable {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

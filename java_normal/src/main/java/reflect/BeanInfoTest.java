package reflect;


import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName BeanInfoTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/25 11:12
 * @Version 1.0
 **/
public class BeanInfoTest {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("peida");

        try {
            BeanInfoUtil.getProperty(userInfo,"userName");
            BeanInfoUtil.setProperty(userInfo,"userName");
            BeanInfoUtil.getProperty(userInfo,"userName");
            BeanInfoUtil.setPropertyByIntrospector(userInfo,"userName");
            BeanInfoUtil.getPropertyByIntrospector(userInfo,"userName");
            BeanInfoUtil.setProperty(userInfo,"age");
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

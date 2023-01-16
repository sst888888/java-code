package sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Test test = new Test();
        String s = test.insert(new Student("测试", 20));
        System.out.println("----" + s);
    }

    public String insert(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //insert into student(name,age) values ("测试",20)
        StringBuilder sql = new StringBuilder();
        Class<?> clazz = object.getClass();
        sql.append("insert into ");
        sql.append(clazz.getSimpleName()).append("(");
        Field[] fields = clazz.getDeclaredFields(); // 不获取父类属性
        for (Field field : fields) {
            sql.append(field.getName()).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append(" values (");
        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            String str1 = fieldName.substring(0, 1).toUpperCase();
            String str2 = fieldName.substring(1, fieldName.length());
            String strValue = str1.concat(str2);
            Method method = clazz.getMethod("get" + strValue, null);
            Object value1 = method.invoke(object, null);

            // Object value = field.get(object); value 与 value1 一样的值

//     if(value1.getClass().equals(String.class))
//     if(field.getType().equals(String.class))
            if (value1 instanceof String) {
                sql.append("\"").append(value1).append("\"").append(",");
            } else {
                sql.append(value1).append(",");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println(sql.toString());
        return sql.toString();
    }

}

package catchfinally;

import org.junit.Test;

/**
 * 在catch中有return的情况下,finally中的内容还是会执行，并且是先执行finally再return。
 *
 * 需要注意的是，如果返回的是一个基本数据类型，则finally中的内容对返回的值没有影响。因为返回的是 finally执行之前生成的一个副本。
 *
 * 当catch和finally都有return时，return的是finally的值。
 */
public class TestCatch {

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());

        Student1 student1 = new Student1("abc");
        Student2 student2 = new Student2("abcfff");
        System.out.println(student1.equals(student2));
    }


    public static int test1() {
        int i = 1;
        try {
            throw new Exception("yyy");
        } catch (Exception exception) {
            System.out.println("return ----");
            return i;
        } finally {
            System.out.println("finally ----");
        }
    }

    public static int test2() {
        try {
            throw new Exception("yyy");
        } catch (Exception exception) {
            System.out.println("return ----");
            return 2;
        } finally {
            System.out.println("finally ----");
            return 22;
        }
    }

    public static int test3() {
        int i = 1;
        try {
            throw new Exception("yyy");
        } catch (Exception exception) {
            System.out.println("return ----");
            return i;
        } finally {
            i++;
            System.out.println("finally ----");
        }
    }

    public static int test4() {
        int i = 1;
        try {
            throw new Exception("yyy");
        } catch (Exception exception) {
            System.out.println("return ----");
            return i;
        } finally {
            System.out.println("finally ----");
            i++;
            return i;
        }
    }


}

package string;

public class Jtest {


    public static void main(String[] args) {

        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");

        oper(a, b);

        System.out.print(a + "," + b);

        test();
    }

    static void oper(StringBuffer c, StringBuffer d) {
        c.append("B");
        d = c;
    }

    static void test() {
        // https://blog.csdn.net/u011069294/article/details/107640761
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.print(s1 == s3);

        final String s4 = "javaEE";
        String s5 = s4 + "hadoop";
        System.out.print(s1 == s5);
    }

}

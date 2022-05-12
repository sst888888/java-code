import org.junit.Test;

import java.math.BigDecimal;

/**
 * @ClassName ByteTest
 * @Description TODO
 * @Author Chaiphat
 * @Date 2021/6/11 19:04
 * @Version 1.0
 **/
public class ByteTest {

    public static void main(String[] args) {
        byte sst = Byte.parseByte("124");
        System.out.println(sst);
        System.out.println((byte) Integer.parseInt("9D", 16));
        System.out.println(Short.reverseBytes((short) 5));
        System.out.println((int)0x80000000);
        System.out.println(Integer.reverse(2));
        System.out.println(Float.floatToIntBits(50));
        System.out.println(Float.valueOf(50));
//        System.out.println(Integer.stringSize(+12345));
        System.out.println(Character.toTitleCase(22));
    }

    @Test
    public void testCharacter() {
        char[] c1 = {'明','日','科','技'};
        int cha = Character.codePointAt(c1,2);
        System.out.println(cha);

        System.out.println(Character.getName(31185));
        System.out.println(Character.getName(0x54c8));

        // 0 ≤ digit < radix
        System.out.println(Character.forDigit(8, 10));
        System.out.println(Character.forDigit(5, 7));
        System.out.println(Character.forDigit(10, 18));
        System.out.println(Character.forDigit(10, 11));
    }


    @Test
    public void testBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(12000.0000);
        System.out.println(bigDecimal.stripTrailingZeros());
        System.out.println(Math.hypot(3,4));
    }

    @Test
    public void main() {
        Integer money = 26000;
        Double rate = 1.25;
        System.out.println(money * rate - 3200);
//        System.out.println(money * rate * rate);
//        System.out.println(money * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate);
//        System.out.println(money * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate * rate);
    }
}

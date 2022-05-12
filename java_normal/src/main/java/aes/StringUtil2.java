package aes;

import org.springframework.util.Assert;

import java.util.Random;

public class StringUtil2 {
    public static String random(int count) {
        return random(count, RandomType.ALL);
    }

    public static String random(int count, RandomType randomType) {
        if (count == 0) {
            return "";
        } else {
            Assert.isTrue(count > 0, "Requested random string length " + count + " is less than 0.");
            Random random = Holder.SECURE_RANDOM;
            char[] buffer = new char[count];

            for(int i = 0; i < count; ++i) {
                String factor = randomType.getFactor();
                buffer[i] = factor.charAt(random.nextInt(factor.length()));
            }

            return new String(buffer);
        }
    }


}

package rsa;

import java.util.Base64;

public abstract class Coder {

    public Coder() {
    }

    public static byte[] decryptBASE64(String key) {
        byte[] bytes = null;

        try {
            bytes = Base64.getDecoder().decode(key);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        return bytes;
    }

    public static String encryptBASE64(byte[] key) {
        String content = null;

        try {
            content = Base64.getEncoder().encodeToString(key);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return content;
    }
}

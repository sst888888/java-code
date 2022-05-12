package aes;

import com.google.common.base.Charsets;
import jodd.util.StringUtil;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import reactor.core.Exceptions;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class AesUtil {
    public static final Charset DEFAULT_CHARSET;

    public AesUtil() {
    }

    public static String genAesKey() {
        return StringUtil2.random(32);
    }

    public static byte[] encrypt(String content, String aesTextKey) {
        return encrypt(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] encrypt(String content, Charset charset, String aesTextKey) {
        return encrypt(content.getBytes(charset), aesTextKey);
    }

    public static byte[] encrypt(byte[] content, String aesTextKey) {
        return encrypt(content, ((String) Objects.requireNonNull(aesTextKey)).getBytes(DEFAULT_CHARSET));
    }

    public static byte[] encryptByPkcs5(byte[] content, String aesTextKey) {
        return encryptByPkcs5(content, ((String)Objects.requireNonNull(aesTextKey)).getBytes(DEFAULT_CHARSET));
    }

    public static String encryptToHex(String content, String aesTextKey) {
        return HexUtil.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToHex(byte[] content, String aesTextKey) {
        return HexUtil.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToBase64(String content, String aesTextKey) {
        return Base64Util.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToBase64ByPkcs5(String content, String aesTextKey) {
        return encryptToBase64ByPkcs5(content.getBytes(StandardCharsets.UTF_8), aesTextKey);
    }

    public static String encryptToBase64(byte[] content, String aesTextKey) {
        return Base64Util.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToBase64ByPkcs5(byte[] content, String aesTextKey) {
        return Base64Util.encodeToString(encryptByPkcs5(content, aesTextKey));
    }

    @Nullable
    public static String decryptFormHexToString(@Nullable String content, String aesTextKey) {
        byte[] hexBytes = decryptFormHex(content, aesTextKey);
        return hexBytes == null ? null : new String(hexBytes, DEFAULT_CHARSET);
    }

    @Nullable
    public static byte[] decryptFormHex(@Nullable String content, String aesTextKey) {
        return StringUtil.isBlank(content) ? null : decryptFormHex(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] decryptFormHex(byte[] content, String aesTextKey) {
        return decrypt(HexUtil.decode(content), aesTextKey);
    }

    @Nullable
    public static String decryptFormBase64ToString(@Nullable String content, String aesTextKey) {
        byte[] hexBytes = decryptFormBase64(content, aesTextKey);
        return hexBytes == null ? null : new String(hexBytes, DEFAULT_CHARSET);
    }

    @Nullable
    public static byte[] decryptFormBase64(@Nullable String content, String aesTextKey) {
        return StringUtil.isBlank(content) ? null : decryptFormBase64(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    @Nullable
    public static byte[] decryptFormBase64ByPkcs5(@Nullable String content, String aesTextKey) {
        return StringUtil.isBlank(content) ? null : decryptFormBase64ByPkcs5(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] decryptFormBase64(byte[] content, String aesTextKey) {
        return decrypt(Base64Util.decode(content), aesTextKey);
    }

    public static byte[] decryptFormBase64ByPkcs5(byte[] content, String aesTextKey) {
        return decryptByPkcs5(Base64Util.decode(content), aesTextKey);
    }

    public static String decryptToString(byte[] content, String aesTextKey) {
        return new String(decrypt(content, aesTextKey), DEFAULT_CHARSET);
    }

    public static byte[] decrypt(byte[] content, String aesTextKey) {
        return decrypt(content, ((String)Objects.requireNonNull(aesTextKey)).getBytes(DEFAULT_CHARSET));
    }

    public static byte[] decryptByPkcs5(byte[] content, String aesTextKey) {
        return decryptByPkcs5(content, ((String)Objects.requireNonNull(aesTextKey)).getBytes(DEFAULT_CHARSET));
    }

    public static byte[] encrypt(byte[] content, byte[] aesKey) {
        return aes(AesUtil.Pkcs7Encoder.encode(content), aesKey, 1);
    }

    public static byte[] encryptByPkcs5(byte[] content, byte[] aesKey) {
        return aesByPkcs5(content, aesKey, 1);
    }

    public static byte[] decrypt(byte[] encrypted, byte[] aesKey) {
        return AesUtil.Pkcs7Encoder.decode(aes(encrypted, aesKey, 2));
    }

    public static byte[] decryptByPkcs5(byte[] encrypted, byte[] aesKey) {
        return aesByPkcs5(encrypted, aesKey, 2);
    }

    private static byte[] aes(byte[] encrypted, byte[] aesKey, int mode) {
        Assert.isTrue(aesKey.length == 32, "IllegalAesKey, aesKey's length must be 32");

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(mode, keySpec, iv);
            return cipher.doFinal(encrypted);
        } catch (Exception var6) {
            throw Exceptions.failWithCancel();
        }
    }

    private static byte[] aesByPkcs5(byte[] encrypted, byte[] aesKey, int mode) {
        Assert.isTrue(aesKey.length == 32, "IllegalAesKey, aesKey's length must be 32");

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(mode, keySpec, iv);
            return cipher.doFinal(encrypted);
        } catch (Exception var6) {
            throw Exceptions.failWithCancel();
        }
    }

    static {
        DEFAULT_CHARSET = Charsets.UTF_8;
    }

    private static class Pkcs7Encoder {
        private static final int BLOCK_SIZE = 32;

        private Pkcs7Encoder() {
        }

        private static byte[] encode(byte[] src) {
            int count = src.length;
            int amountToPad = 32 - count % 32;
            byte pad = (byte)(amountToPad & 255);
            byte[] pads = new byte[amountToPad];

            int length;
            for(length = 0; length < amountToPad; ++length) {
                pads[length] = pad;
            }

            length = count + amountToPad;
            byte[] dest = new byte[length];
            System.arraycopy(src, 0, dest, 0, count);
            System.arraycopy(pads, 0, dest, count, amountToPad);
            return dest;
        }

        private static byte[] decode(byte[] decrypted) {
            int pad = decrypted[decrypted.length - 1];
            if (pad < 1 || pad > 32) {
                pad = 0;
            }

            return pad > 0 ? Arrays.copyOfRange(decrypted, 0, decrypted.length - pad) : decrypted;
        }
    }
}


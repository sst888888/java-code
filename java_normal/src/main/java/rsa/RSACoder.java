package rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public abstract class RSACoder extends Coder {
    public RSACoder() {
    }

    public static boolean verify(byte[] data, String publicKey, String sign) {
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;
        boolean success = false;

        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(pKey);
            signature.update(data);
            success = signature.verify(decryptBASE64(sign));
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        } catch (InvalidKeySpecException var10) {
            var10.printStackTrace();
        } catch (InvalidKeyException var11) {
            var11.printStackTrace();
        } catch (SignatureException var12) {
            var12.printStackTrace();
        }

        return success;
    }

    public static byte[] decryptByPrivateKey(byte[] encryptedData, String key) {
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        byte[] decryptedData = null;
        ByteArrayOutputStream out = null;

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Key pKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(2, pKey);
            out = new ByteArrayOutputStream();
            cutting(encryptedData, cipher, encryptedData.length, out, 128);
            decryptedData = out.toByteArray();
        } catch (NoSuchAlgorithmException var23) {
            var23.printStackTrace();
        } catch (InvalidKeySpecException var24) {
            var24.printStackTrace();
        } catch (NoSuchPaddingException var25) {
            var25.printStackTrace();
        } catch (InvalidKeyException var26) {
            var26.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var22) {
                var22.printStackTrace();
            }

        }

        return decryptedData;
    }

    public static byte[] encryptByPublicKey(byte[] data, String key) {
        byte[] keyBytes = null;
        byte[] dataBytes = null;
        keyBytes = decryptBASE64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;
        ByteArrayOutputStream out = null;

        try {
            keyFactory = KeyFactory.getInstance("RSA");
            Key pKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(1, pKey);
            out = new ByteArrayOutputStream();
            cutting(data, cipher, data.length, out, 117);
            dataBytes = out.toByteArray();
        } catch (NoSuchAlgorithmException var23) {
            var23.printStackTrace();
        } catch (InvalidKeySpecException var24) {
            var24.printStackTrace();
        } catch (NoSuchPaddingException var25) {
            var25.printStackTrace();
        } catch (InvalidKeyException var26) {
            var26.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var22) {
                var22.printStackTrace();
            }

        }

        return dataBytes;
    }

    private static void cutting(byte[] data, Cipher cipher, int inputLen, ByteArrayOutputStream out, int num) {
        try {
            int offSet = 0;

            for(int i = 0; inputLen - offSet > 0; offSet = i * num) {
                byte[] cache;
                if (inputLen - offSet > num) {
                    cache = cipher.doFinal(data, offSet, num);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }

                out.write(cache, 0, cache.length);
                ++i;
            }
        } catch (BadPaddingException var8) {
            var8.printStackTrace();
        } catch (IllegalBlockSizeException var9) {
            var9.printStackTrace();
        }
    }

}

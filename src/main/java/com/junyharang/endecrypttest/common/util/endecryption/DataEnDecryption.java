package com.junyharang.endecrypttest.common.util.endecryption;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.PropertyValueException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;

public class DataEnDecryption {

    public static String base64Encoder(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String dataEnDecrypt(String cipherKey, String data, int cipherMode) {

        String result = null;

        try {
            Cipher cipher = Cipher.getInstance("AES");

            byte[] initializationVector = new byte[32];
            int index = 0;

            for (byte b : cipherKey.getBytes(StandardCharsets.UTF_8)) {
                initializationVector[index++ % 32] ^= b;
            }

            SecretKeySpec keySpec = new SecretKeySpec(initializationVector, "AES");
            cipher.init(cipherMode, keySpec);

            if (cipherMode == Cipher.DECRYPT_MODE) {
                result = new String(cipher.doFinal(Hex.decodeHex(data)), StandardCharsets.UTF_8);
            } else if (cipherMode == Cipher.ENCRYPT_MODE) {
                result = new String(Hex.encodeHex(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)))).toUpperCase(Locale.ROOT);
            } else {
                throw new PropertyValueException("암/복호화 Mode 변수값이 잘못 되었습니다.", "DataEnDecryption.dataEnDecrypt(int cipherMode)", Integer.toString(cipherMode));
            }
        } catch (NoSuchAlgorithmException |
                 NoSuchPaddingException |
                 DecoderException |
                 IllegalBlockSizeException |
                 BadPaddingException |
                 InvalidKeyException error) {

            error.printStackTrace();
        }
        return result;
    }
}

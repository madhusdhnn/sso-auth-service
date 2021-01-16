package com.thetechmaddy.authservice.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptUtils {

    public static String aesEncrypt(String plainText, String key) {
        try {
            SecretKeySpec spec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            Cipher eCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            eCipher.init(Cipher.ENCRYPT_MODE, spec);
            return Base64.getEncoder().encodeToString(eCipher.doFinal(plainText.getBytes(UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException ex) {
            throw new RuntimeException("Error while encrypting data", ex);
        }
    }

    public static String aesDecrypt(String cipherText, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            Cipher dCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            dCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(dCipher.doFinal(Base64.getDecoder().decode(cipherText)), UTF_8);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException ex) {
            throw new RuntimeException("Error while decrypting data", ex);
        }
    }

}

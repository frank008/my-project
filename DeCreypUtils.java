package com.yinse.datacenter.TestController;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DeCreypUtils {

    public static void main(String args[]) throws GeneralSecurityException {

        System.out.println(encrypt("password", "qinda")); //qinda是在源码中发现的key
        System.out.println(decrypt("CoQ7Bz3UZsgjZvJiNrTneg==", "qinda"));
    }

    public static String encrypt(String paramString1, String paramString2)
            throws GeneralSecurityException {
        return encrypt(paramString1, paramString2, "8859_1");
    }

    private static Cipher getCipher(String paramString, SecretKey paramSecretKey,
                                    AlgorithmParameterSpec paramAlgorithmParameterSpec, int paramInt)
            throws GeneralSecurityException {
        String str = "";

        Cipher localCipher = null;
        try {
            str = str + " , getting encrypt cipher";

            localCipher = Cipher.getInstance(paramString);

            str = str + " , initializing cipher";

            localCipher.init(paramInt, paramSecretKey,
                    paramAlgorithmParameterSpec);
        } catch (GeneralSecurityException localGeneralSecurityException) {
            localGeneralSecurityException.printStackTrace();
        }
        return localCipher;
    }

    private static Cipher getCipher(String paramString, int paramInt)
            throws GeneralSecurityException {
        Cipher localCipher = null;

        SecretKey localSecretKey = getSecretKey(paramString);

        byte[] arrayOfByte = {-114, 18, 57, -100, 7, 114, 111, 90};

        PBEParameterSpec localPBEParameterSpec = new PBEParameterSpec(
                arrayOfByte, 10);

        String str = "PBEWithMD5AndDES";

        localCipher = getCipher(str, localSecretKey, localPBEParameterSpec, paramInt);

        return localCipher;
    }

    private static SecretKey getSecretKey(String paramString)
            throws GeneralSecurityException {
        SecretKey localSecretKey = null;
        try {
            PBEKeySpec localPBEKeySpec = new PBEKeySpec(
                    paramString.toCharArray());

            SecretKeyFactory localSecretKeyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndDES");

            localSecretKey = localSecretKeyFactory
                    .generateSecret(localPBEKeySpec);
        } catch (GeneralSecurityException localGeneralSecurityException) {

        }
        return localSecretKey;
    }

    public static String encrypt(String paramString1, String paramString2,
                                 String paramString3) throws GeneralSecurityException {
        Cipher localCipher = getCipher(paramString2, 1);
        try {
            byte[] arrayOfByte1 = paramString1.getBytes(paramString3);

            byte[] arrayOfByte2 = Base64.encode(
                    localCipher.doFinal(arrayOfByte1)).getBytes();

            return new String(arrayOfByte2, paramString3);

        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {

        }
        return null;
    }

    public static String decrypt(String text, String key)
            throws GeneralSecurityException {
        return decrypt(text, key, "8859_1");
    }

    public static String decrypt(String encrptedText, String key, String encoding)
            throws GeneralSecurityException {
        Cipher localCipher = getCipher(key, 2);
        try {
            byte[] arrayOfByte1 = encrptedText.getBytes(encoding);

            byte[] arrayOfByte2 = Base64.decode((arrayOfByte1));

            byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);

            return new String(arrayOfByte3, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}

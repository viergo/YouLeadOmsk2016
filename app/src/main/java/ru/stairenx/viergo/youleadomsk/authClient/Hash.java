package ru.stairenx.viergo.youleadomsk.authClient;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by viergo on 30.03.16.
 */
public class Hash {


    public static String md5Custom(String pass) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String genNewToken(){
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randString = new StringBuilder();
        int count1 = 15;
        for(int i=0;i<count1;i++)
            randString.append(symbols.charAt((int)(Math.random()*symbols.length())));

        String numeric = "0123456789";
        StringBuilder randNumeric = new StringBuilder();
        int count2 = 15;
        for(int i=0;i<count2;i++)
            randNumeric.append(numeric.charAt((int)(Math.random()*numeric.length())));

        String mix = randString.toString()+randNumeric.toString();
        StringBuilder token = new StringBuilder();
        int count3 = 30;
        for(int i=0;i<count3;i++)
            token.append(mix.charAt((int)(Math.random()*mix.length())));

        return String.valueOf(token);
    }

}

package cn.dails.base.utils;


import java.util.Random;

public class RandomUtils {
    public static final String _BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String _NUM_ONLY = "0123456789";
    public static final String _CHAR_ONLY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String _CASE_ONLY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String _UNCASE_ONLY ="abcdefghijklmnopqrstuvwxyz";

    public static String randomCode(int size, String sources) {
        if(sources == null || sources.length() == 0){
            sources = _BASE;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder str = new StringBuilder(size);
        for(int i = 0; i < size; i++){
            str.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return str.toString();
    }
}

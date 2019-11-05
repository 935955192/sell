package com.imooc.util;

import java.util.Random;

/**
 * Created by Administrator on 2019/10/26.
 */
public class KeyUtil {
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        getUniqueKey();
    }
}

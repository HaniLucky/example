package com.example.my.project.common;

import java.util.UUID;

/**
 * Created by Covet on 2018/12/6.
 */
public class UUIDUtil {

    public static String Creeate32UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        String s = UUIDUtil.Creeate32UUID();
        System.out.println(s);
    }
}

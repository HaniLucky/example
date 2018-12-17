package com.example.ERP;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Created by Administrator on 2018/12/17.
 */
public class Md5Test {

    @Test
    public  void md5Encode(){
        System.out.println(new Md5Hash("123456","admin ",2));
        System.out.println(new Md5Hash("123456"));
    }

}

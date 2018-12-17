package com.example.ERP;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErpApplicationTests {

	@Test
	public void contextLoads() {
        Md5Hash md5Hash = new Md5Hash("123456", "admin", 2);
        System.out.println(md5Hash);
    }

}

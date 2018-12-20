package shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EncodeMd5 {
	
	public static void main(String[] args) {
		// 928bfd2577490322a6e19b793691467e
		System.out.println(new Md5Hash("123456","admin",2));
	}

}

package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.vo.User;

@Repository
public class UserMapper {

	public List<User> getUsers(){
		ArrayList<User> users = new ArrayList<>();
		User user001 = new User("001", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user002 = new User("002", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user003 = new User("003", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user004 = new User("004", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user005 = new User("005", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user006 = new User("006", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user007 = new User("007", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user008 = new User("008", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user009 = new User("009", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user010 = new User("010", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user011 = new User("011", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user012 = new User("012", "张三", "男", "北京", "11111", "1111@QQ.com");
		User user013 = new User("013", "张三", "男", "北京", "11111", "1111@QQ.com");
		users.add(user001);
		users.add(user002);
		users.add(user003);
		users.add(user004);
		users.add(user005);
		users.add(user006);
		users.add(user007);
		users.add(user008);
		users.add(user009);
		users.add(user010);
		users.add(user011);
		users.add(user012);
		users.add(user013);
		return users;
	}
}

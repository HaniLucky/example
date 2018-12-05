package com.example.my.project.user.mapper;

import com.example.my.project.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Covet on 2018/12/4.
 */
@Mapper
public interface UserMapper {
    public List<User> queryUser();
}

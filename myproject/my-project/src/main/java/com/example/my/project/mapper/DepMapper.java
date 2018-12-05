package com.example.my.project.mapper;

import com.example.my.project.vo.Dep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Covet on 2018/12/5.
 */
@Mapper
public interface DepMapper {
    @Select(value = "SELECT UUID,NAME,TELE FROM DEP")
    public List<Dep> queryDepList();
}

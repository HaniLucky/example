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
    // @Select(value = "SELECT UUID,NAME,TELE FROM DEP")
    @Select(value = "<script>" +
            "SELECT UUID,NAME,TELE FROM DEP" +
            "WHERE 1=1 AND" +
               // "<if test='name != null and name != ''''>" +
                    "AND NAME = #{name}" +
               // "</if>" +
                //"<if test='tele != null and tele != ''''>" +
                     "AND TELE = #{tele}" +
               // "</if>" +
            "</script>" )
    public List<Dep> queryDepList(String name,String tele);
}

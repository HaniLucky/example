package com.example.ERP.mapper;

import com.example.ERP.vo.Menu;
import com.example.ERP.vo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@Mapper
public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<Role>{
    List<Menu> selectRoleMeunByRoleId(String id);

    List<Role> selectEmpRoleByEmpId(String id);
}

package com.example.ERP.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Covet on 2018/12/13.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    private String menuid; // 菜单id
    private String menuname;    // 菜单名称
    private String icon;    // 菜单图标
    private String url;     // 菜单url
    private String pid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Menu> menus;   // 下级菜单

}

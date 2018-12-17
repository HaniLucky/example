package com.example.ERP.service;

import com.example.ERP.common.BaseService;
import com.example.ERP.vo.Menu;

/**
 * Created by Covet on 2018/12/16.
 */
public interface MenuService  extends BaseService<Menu>{
    Menu menuTree();
}

package com.example.ERP.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.common.BaseService;
import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.service.MenuService;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Menu;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
@RestController
@RequestMapping(value = "/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private MenuService menuService;

    //@PostMapping(value = "/listByPage")
    public PageBean<Emp> listByPage(Integer page, Integer rows) {
        return empService.queryByPage(page, rows);
    }

    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public PageBean<EmpVo> allDep(EmpVo emp, Integer page, Integer rows) throws ParseException {
        return empService.queryListPageBaenByWheres(emp, page, rows);
    }

    @PostMapping(value = "/")
    public Result add(@RequestBody Emp emp) {
        return new Result(true, "添加成功", empService.save(emp));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = empService.deleteById(id);
        return new Result(true, "删除成功", num);
    }

    @PutMapping(value = "/")
    public Result update(@RequestBody Emp emp) {
        int num = empService.updateByIdSelective(emp);
        return new Result(true, "更新成功", num);
    }

    @GetMapping(value = "/{id}")
    public Emp get(@PathVariable Long id) {
        return empService.queryById(id);
    }

    /**
     * 登录
     *
     * @param emp
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(Emp emp, HttpServletRequest request, HttpServletResponse response) {
        // 登录时应该使用加密后的密码
        emp.setPwd(new Md5Hash(emp.getPwd(),emp.getUsername(),2).toString());
        Emp user = empService.login(emp);
        if (user == null) {
            return new Result(false, "登录失败", null);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", emp);
        return new Result(true, "登录成功", user);
    }

    /**
     * 返回用户名
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "showName", method = RequestMethod.GET)
    public Result showName(HttpServletRequest request) {
        Emp user = (Emp) request.getSession().getAttribute("user");
        if (user != null) {
            return new Result(true, user.getUsername(), null);
        }
        return new Result(false, null, null);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new Result(true, "SUCCESS", null);
    }

    @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu meunTree() {
        // 查询所有菜单数据
        List<Menu> menus = menuService.queryAll();
        // 查询父层级
        Menu rootMenu = menuService.queryById("0");
        Menu childrens = treeRoot(menus, rootMenu);

        System.out.println(JSONObject.toJSON(childrens));
        return childrens;
    }

    private Menu treeRoot(List<Menu> sourceList, Menu rootMenu) {
        if (sourceList == null) {
            return null;
        }
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : sourceList) {
            if (rootMenu.getMenuid().equals(menu.getPid())) {
                Menu menuChild = treeRoot(sourceList, menu);
                childList.add(menuChild);
            }
        }
        if (childList.size() == 0) {
            return rootMenu;
        }
        rootMenu.setMenus(childList);
        return rootMenu;
    }


}

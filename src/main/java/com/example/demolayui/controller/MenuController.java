package com.example.demolayui.controller;

import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.service.MenuService;
import com.example.demolayui.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 15:42
 */
@Controller
@RequestMapping("menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("list")
    public String listPage() {
        return "/menu/list";
    }
    @GetMapping("add")
    public String addPage() {
        return "/menu/add";
    }
    @GetMapping("edit")
    public String editPage() {
        return "/menu/edit";
    }

    @GetMapping("tree")
    @ResponseBody
    public SysMenu tree() {
        return menuService.tree();
    }

    @GetMapping("treevo")
    @ResponseBody
    public Map<String, Object> treeVO(Long roleId) {
        Map<String, Object> result = new HashMap<>();
        List<MenuVO> list = new ArrayList<>();
        MenuVO menuVO = menuService.treeVO(roleId);
        list.add(menuVO);

        Map<String, String> status = new HashMap<>();
        status.put("code", "200");
        status.put("message", "success");

        result.put("data", list);
        result.put("status", status);
        result.put("code", 0);
        result.put("msg", "success");
        return result;
    }

    @GetMapping("index_info")
    @ResponseBody
    public IndexVO indexInfo() {
        LogoInfo logoInfo = new LogoInfo();
        logoInfo.setTitle("DEMO LAYUI");
        logoInfo.setImage("images/logo.png");
        logoInfo.setHref("");

        List<SysMenu> menuInfo = new ArrayList<>();
        menuInfo.add(tree());

        HomeInfo homeInfo = new HomeInfo();
        SysMenu firstMenu = getFirstMenu(menuInfo);
        homeInfo.setTitle(firstMenu.getTitle());
        homeInfo.setHref(firstMenu.getHref());

        IndexVO indexVO = new IndexVO();
        indexVO.setHomeInfo(homeInfo);
        indexVO.setLogoInfo(logoInfo);
        indexVO.setMenuInfo(menuInfo);
        return indexVO;
    }

    private SysMenu getFirstMenu(List<SysMenu> menus) {
        if (!CollectionUtils.isEmpty(menus) && !CollectionUtils.isEmpty(menus.get(0).getChild())) {
            return getFirstMenu(menus.get(0).getChild());
        }
        return menus.get(0);
    }

    @GetMapping
    public List<MenuAndPermissionVO> menuList() {
        List<MenuAndPermissionVO> menus = new ArrayList<>();
        SysMenu sysMenu = menuService.treeAndPermissions();
        return null;
    }

    public void convert(SysMenu sysMenu, List<MenuAndPermissionVO> menus) {
        MenuAndPermissionVO mp = new MenuAndPermissionVO();
        mp.setMenuId(sysMenu.getId());

    }
}

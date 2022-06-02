package com.example.demolayui.controller;

import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.service.MenuService;
import com.example.demolayui.vo.HomeInfo;
import com.example.demolayui.vo.IndexVO;
import com.example.demolayui.vo.LogoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 15:42
 */
@RestController
@RequestMapping("menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("tree")
    public SysMenu tree() {
        return menuService.tree();
    }

    @GetMapping("index_info")
    public IndexVO indexInfo() {
        HomeInfo homeInfo = new HomeInfo();
        homeInfo.setTitle("首页");
        homeInfo.setHref("page/welcome-1.html?t=1");

        LogoInfo logoInfo = new LogoInfo();
        logoInfo.setTitle("DEMO LAYUI");
        logoInfo.setImage("images/logo.png");
        logoInfo.setHref("");

        List<SysMenu> menuInfo = new ArrayList<>();
        menuInfo.add(tree());

        IndexVO indexVO = new IndexVO();
        indexVO.setHomeInfo(homeInfo);
        indexVO.setLogoInfo(logoInfo);
        indexVO.setMenuInfo(menuInfo);
        return indexVO;
    }
}

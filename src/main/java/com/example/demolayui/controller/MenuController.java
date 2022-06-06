package com.example.demolayui.controller;

import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.service.MenuService;
import com.example.demolayui.vo.HomeInfo;
import com.example.demolayui.vo.IndexVO;
import com.example.demolayui.vo.LogoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
}

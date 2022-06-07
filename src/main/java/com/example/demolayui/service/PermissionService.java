package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.entity.SysPermission;
import com.example.demolayui.mapper.MenuMapper;
import com.example.demolayui.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:23
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, SysPermission> {

    @Autowired
    private MenuMapper menuMapper;

    public List<SysPermission> findByRoleId(Long roleId) {
        List<SysMenu> menus = menuMapper.findByRoleIds(Stream.of(roleId).collect(Collectors.toList()));

        return baseMapper.findByMenuIds(menus.stream().map(SysMenu::getId).collect(Collectors.toList()));
    }

    public List<SysPermission> findByMenuId(Long menuId) {
        return baseMapper.findByMenuIds(Stream.of(menuId).collect(Collectors.toList()));
    }
}

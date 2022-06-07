package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 15:10
 */
@Service
@Transactional
public class MenuService extends ServiceImpl<MenuMapper, SysMenu> {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    public SysMenu tree() {
        SysMenu root = rootMenu();
        root.setChild(treeRecursion(root));
        return root;
    }

    public SysMenu treeAndPermissions() {
        SysMenu root = tree();
        loadPermission(root);
        return root;
    }

    public void loadPermission(SysMenu sysMenu) {
        sysMenu.setPermissions(permissionService.findByMenuId(sysMenu.getId()));
        if (!CollectionUtils.isEmpty(sysMenu.getChild())) {
            sysMenu.getChild().forEach(this::loadPermission);
        }
    }

    private List<SysMenu> treeRecursion(SysMenu menu){
        List<SysMenu> items = baseMapper.findByParenId(menu.getId(), authorityMenu());
        if (!CollectionUtils.isEmpty(items)) {
            items.forEach(p -> p.setChild(treeRecursion(p)));
        }
        return items;
    }

    public void update(SysMenu menu) {

    }

    public void delete(SysMenu menu) {

    }

    private SysMenu load(Long id) {
        return baseMapper.selectById(id);
    }

    private SysMenu rootMenu() {
        return baseMapper.findByCode("sys_init");
    }

    public List<Long> authorityMenu(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roleNames = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<SysRole> roles = roleService.findByRoleNames(roleNames);
        return baseMapper.findByRoleIds(
                                        roles.stream()
                                        .map(SysRole::getId)
                                        .collect(Collectors.toList()))
                            .parallelStream()
                            .map(SysMenu::getId)
                            .collect(Collectors.toList());
    }
}

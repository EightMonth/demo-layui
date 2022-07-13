package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:19
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, SysRole> {
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean save(SysRole entity) {
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysRole entity) {
        entity.setModifyTime(new Date());
        return super.updateById(entity);
    }

    public List<SysRole> findByUserId(Long userId) {
        List<SysRole> sysRoleList = baseMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(sysRoleList)) {
            return null;
        }
        fillPermission(sysRoleList);
        return sysRoleList;
    }

    public List<SysRole> list() {
        List<SysRole> sysRoleList = super.list();
        fillPermission(sysRoleList);
        return sysRoleList;
    }

    private void fillPermission(SysRole sysRole) {
        sysRole.setPermissions(permissionService.findByRoleId(sysRole.getId()));
    }

    private void fillPermission(List<SysRole> sysRoleList) {
        sysRoleList.forEach(this::fillPermission);
    }

    public List<SysRole> findByRoleNames(List<String> roleNames) {
        return baseMapper.findByNames(roleNames);
    }

    public void addMenu(Long roleId, List<Long> menuIds) {
        baseMapper.removeAllMenu(roleId);
        menuIds.forEach(m -> {
            baseMapper.addMenu(roleId, m);
        });
    }

    public SysRole defaultRole() {
        return baseMapper.defaultRole();
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(p -> baseMapper.removeAllMenu((Long) p));
        return super.removeByIds(idList);
    }
}

package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.entity.SysUser;
import com.example.demolayui.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 16:49
 */
@Service
public class UserService extends ServiceImpl<UserMapper, SysUser> {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean save(SysUser entity) {
        entity.setLastLoginTime(new Date());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        int id = baseMapper.insert(entity);

        SysRole defaultRole = roleService.defaultRole();
        baseMapper.insertUserRole((long) id, defaultRole.getId());

        return SqlHelper.retBool(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(p -> baseMapper.deleteUserRole((Long) p));
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(SysUser entity) {
        entity.setModifyTime(new Date());
        return super.updateById(entity);
    }

    public void recordLoginTime(Long Id) {
        SysUser loginUser = getById(Id);
        loginUser.setLastLoginTime(new Date());
        super.updateById(loginUser);
    }

    public SysUser findByUsername(String username) {
        SysUser sysUser = this.getBaseMapper().findByUsername(username);
        fillRole(sysUser);
        return sysUser;
    }

    public List<SysUser> list() {
        List<SysUser> userList = super.list();
        fillRole(userList);
        return userList;
    }

    private void fillRole(SysUser sysUser) {
        sysUser.setRoles(roleService.findByUserId(sysUser.getId()));
    }

    private void fillRole(List<SysUser> sysRoleList) {
        sysRoleList.forEach(this::fillRole);
    }

    public void addRole(Long userId, List<Long> roleIds)  {
        baseMapper.deleteUserRole(userId);

        roleIds.forEach(p -> baseMapper.insertUserRole(userId, p));
    }
}

package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysUser;
import com.example.demolayui.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return super.save(entity);
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
}

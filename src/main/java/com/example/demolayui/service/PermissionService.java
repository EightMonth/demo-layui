package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysPermission;
import com.example.demolayui.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:23
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, SysPermission> {

    public List<SysPermission> findByRoleId(Long roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}

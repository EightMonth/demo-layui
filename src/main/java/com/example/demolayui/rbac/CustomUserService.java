package com.example.demolayui.rbac;

import com.example.demolayui.entity.SysUser;
import com.example.demolayui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/1 16:31
 */
@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser loginUser = userService.findByUsername(username);

        if (!ObjectUtils.isEmpty(loginUser)) {
            userService.recordLoginTime(loginUser.getId());
        }

        return loginUser;
    }
}

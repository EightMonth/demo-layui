package com.example.demolayui.rbac;

import com.example.demolayui.entity.SysPermission;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.mapper.PermissionMapper;
import com.example.demolayui.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/1 15:55
 */
@Component
public class RbacService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PathMatcher pathMatcher;

    /**
     * 判断当前请求的路径是否有权限访问
     * @param request 请求
     * @param authentication 认证信息
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Collection<? extends GrantedAuthority> authorities = mockAuthorities();
        String requestPath = request.getRequestURI();

        List<String> privilegeUrls = privilegeUrls(authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        for (String urlPattern : privilegeUrls) {
            boolean pass = pathMatcher.match(urlPattern, requestPath);
            if (pass) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据解决名称，获取角色权限内的url
     * @param roleNames 角色名称
     * @return
     */
    private List<String> privilegeUrls(List<String> roleNames) {
        List<SysRole> roles = roleMapper.findByNames(roleNames);
        for (SysRole role : roles) {
            role.setPermissions(permissionMapper.findByRoleId(role.getId()));
        }

        return roles.parallelStream()
                .flatMap(p -> p.getPermissions().stream())
                .map(SysPermission::getUrl)
                .distinct()
                .collect(Collectors.toList());
    }

    // 测试使用
    private List<GrantedAuthority> mockAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        authorities.add(new SimpleGrantedAuthority("user"));
        return authorities;
    }
}

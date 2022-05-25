package com.example.demolayui.controller;

import com.example.demolayui.entity.SysUser;
import com.example.demolayui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 16:52
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<SysUser> list() {
        return userService.list();
    }
}

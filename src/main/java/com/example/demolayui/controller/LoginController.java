package com.example.demolayui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/5 10:34
 */
@Controller
public class LoginController {
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("loginPost")
    public String loginPost(String username, String password) {
        return "index";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }
}

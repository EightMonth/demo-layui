package com.example.demolayui.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return mav;
    }
}

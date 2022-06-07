package com.example.demolayui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 14:39
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @GetMapping("list")
    public String listPage() {
        return "/role/list";
    }
    @GetMapping("add")
    public String addPage() {
        return "/role/add";
    }
    @GetMapping("edit")
    public String editPage() {
        return "/role/edit";
    }
}

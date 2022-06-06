package com.example.demolayui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/25 9:35
 */
@Controller
@RequestMapping("/page")
public class IndexController {

    @GetMapping("white")
    public String white() {
        return "white";
    }

    @GetMapping("404.html")
    public String page404() {
        return "page/404";
    }

    @GetMapping("area.html")
    public String area() {
        return "page/area";
    }

    @GetMapping("button.html")
    public String button() {
        return "page/button";
    }

    @GetMapping("color-select.html")
    public String colorSelect() {
        return "page/color-select";
    }

    @GetMapping("editor.html")
    public String editor() {
        return "page/editor";
    }

    @GetMapping("form.html")
    public String form() {
        return "page/form";
    }

    @GetMapping("form-step.html")
    public String formStep() {
        return "page/form-step";
    }

    @GetMapping("icon.html")
    public String icon() {
        return "page/icon";
    }

    @GetMapping("icon-picker.html")
    public String iconPicker() {
        return "page/icon-picker";
    }

    @GetMapping("layer.html")
    public String layer() {
        return "page/layer";
    }

    @GetMapping("login")
    public String login1() {
        return "page/login-1";
    }

    @GetMapping("login-2.html")
    public String login2() {
        return "page/login-2";
    }

    @GetMapping("login-3.html")
    public String login3() {
        return "page/login-3";
    }

    @GetMapping("menu.html")
    public String menu() {
        return "page/menu";
    }

    @GetMapping("setting.html")
    public String setting() {
        return "page/setting";
    }

    @GetMapping("table.html")
    public String table() {
        return "page/table";
    }

    @GetMapping("table-select.html")
    public String tableSelect() {
        return "page/table-select";
    }

    @GetMapping("upload.html")
    public String upload() {
        return "page/upload";
    }

    @GetMapping("user-password.html")
    public String userPassword() {
        return "page/user-password";
    }

    @GetMapping("user-setting.html")
    public String userSetting() {
        return "page/user-setting";
    }

    @GetMapping("welcome-1.html")
    public String welcome1() {
        return "page/welcome-1";
    }

    @GetMapping("welcome-2.html")
    public String welcome2() {
        return "page/welcome-2";
    }

    @GetMapping("welcome-3.html")
    public String welcome3() {
        return "page/welcome-3";
    }

    @GetMapping("table/add.html")
    public String tableAdd() {
        return "page/table/add";
    }

    @GetMapping("table/edit.html")
    public String tableEdit() {
        return "page/table/edit";
    }
}

package com.example.demolayui.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demolayui.entity.SysUser;
import com.example.demolayui.service.UserService;
import com.example.demolayui.vo.ResponseData;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 16:52
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String listPage() {
        return "/user/list";
    }
    @GetMapping("add")
    public String addPage() {
        return "/user/add";
    }
    @GetMapping("edit")
    public String editPage() {
        return "/user/edit";
    }

    @GetMapping
    @ResponseBody
    public List<SysUser> list() {
        return userService.list();
    }

    @GetMapping("/page")
    @ResponseBody
    public ResponseData<SysUser> page(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit,
                                      @RequestParam(value = "searchParams", required = false) String searchParams) {

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(searchParams)) {
            SysUser sysUser = JSON.parseObject(searchParams, SysUser.class);
            queryWrapper.like("username", sysUser.getUsername());
        }

        Page<SysUser> pageParam = new Page<>();
        pageParam.setPages(page);
        pageParam.setSize(limit);
        Page<SysUser> pageUser = userService.page(pageParam, queryWrapper);

        ResponseData<SysUser> returnData = new ResponseData<>();
        returnData.setCode(0);
        returnData.setData(pageUser.getRecords());
        returnData.setCount(pageUser.getTotal());
        returnData.setMsg("");
        return returnData;
    }

    @PostMapping
    @ResponseBody
    public void add(@RequestBody SysUser sysUser) {
        userService.save(sysUser);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        userService.removeById(id);
    }
}

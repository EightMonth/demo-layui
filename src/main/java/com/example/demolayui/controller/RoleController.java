package com.example.demolayui.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.service.RoleService;
import com.example.demolayui.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 14:39
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

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

    @GetMapping("page")
    @ResponseBody
    public ApiResponse<SysRole> page(@RequestParam("page") Integer page,
                                     @RequestParam("limit") Integer limit,
                                     @RequestParam(value = "searchParams", required = false) String searchParams) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(searchParams)) {
            SysRole sysRole = JSON.parseObject(searchParams, SysRole.class);
            queryWrapper.like("name", sysRole.getName());
        }

        Page<SysRole> pageParam = new Page<>();
        pageParam.setPages(page);
        pageParam.setSize(limit);
        Page<SysRole> pageUser = roleService.page(pageParam, queryWrapper);

        ApiResponse<SysRole> returnData = new ApiResponse<>();
        returnData.setCode(0);
        returnData.setData(pageUser.getRecords());
        returnData.setCount(pageUser.getTotal());
        returnData.setMsg("");

        return returnData;
    }
}

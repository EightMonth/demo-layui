package com.example.demolayui.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demolayui.entity.SysRole;
import com.example.demolayui.entity.SysUser;
import com.example.demolayui.service.RoleService;
import com.example.demolayui.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @GetMapping("menu")
    public String menuPage() {
        return "/role/menu";
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

    @PostMapping
    @ResponseBody
    public void add(@RequestBody SysRole sysRole) {
        roleService.save(sysRole);
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public void delete(@PathVariable String ids) {
        List<Long> rmIds = Stream.of(ids.split(",")).map(Long::valueOf).collect(Collectors.toList());
        roleService.removeByIds(rmIds);
    }

    @PutMapping
    @ResponseBody
    public void update(@RequestBody SysRole sysRole) {
        SysRole old = roleService.getById(sysRole.getId());

        old.setCode(sysRole.getCode());
        old.setName(sysRole.getName());
        old.setRemark(sysRole.getRemark());

        roleService.updateById(old);
    }

    @GetMapping("/role/menu")
    @ResponseBody
    public void roleMenu(@RequestBody Long roleId) {



    }
}

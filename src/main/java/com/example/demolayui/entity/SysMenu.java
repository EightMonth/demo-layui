package com.example.demolayui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 14:06
 */
@Data
@TableName("t_sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 572047483011117931L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单链接
     */
    private String href;
    /**
     * 菜单打开地址
     * _self 当前页面打开，_black新页面打开
     */
    private String target;

    /**
     * 上级菜单
     */
    private Long parentId;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> child;
}

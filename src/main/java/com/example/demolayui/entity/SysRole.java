package com.example.demolayui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/10/25 10:30
 */
@Data
@TableName("t_sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 7548622718859100313L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
    private String remark;
    private Date createTime;
    private Date modifyTime;

    @TableField(exist = false)
    private List<SysPermission> permissions = new ArrayList<>();

    //页面样式字段
    @TableField(exist = false)
    private boolean disabled;
    @TableField(exist = false)
    private boolean checked;

    // 是否拥有该角色
    @TableField(exist = false)
    private boolean have;
}

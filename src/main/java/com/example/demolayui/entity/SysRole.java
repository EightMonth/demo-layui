package com.example.demolayui.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 7548622718859100313L;

    private Long id;
    private String code;
    private String name;
    private String remark;
    private Date createTime;
    private Date modifyTime;

    @TableField(exist = false)
    private List<SysPermission> permissions = new ArrayList<>();
}

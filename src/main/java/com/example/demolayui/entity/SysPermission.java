package com.example.demolayui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/10/25 10:41
 */
@Data
@TableName("t_sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 8785181546237481140L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String url;
    private Date createTime;
    private Date modifyTime;
    private String code;
    private String type;
    private String name;
}

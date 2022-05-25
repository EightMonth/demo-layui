package com.example.demolayui.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/10/25 10:41
 */
@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 8785181546237481140L;
    private Long id;
    private String url;
    private Date createTime;
    private Date modifyTime;
}

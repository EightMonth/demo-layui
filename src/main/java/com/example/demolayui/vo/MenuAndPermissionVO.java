package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/7 15:07
 */
@Data
public class MenuAndPermissionVO implements Serializable {
    private static final long serialVersionUID = 7062876369717702913L;
    private Long menuId;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Date createTime;
    private Date updateTime;
    private Integer isMenu;
    private Long parentId;
}

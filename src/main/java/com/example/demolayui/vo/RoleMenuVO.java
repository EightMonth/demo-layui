package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/13 16:42
 */
@Data
public class RoleMenuVO implements Serializable {

    private static final long serialVersionUID = -8443381521836588527L;
    private Long roleId;
    private List<Long> menuIds;
}

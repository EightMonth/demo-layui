package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/7/13 10:43
 */
@Data
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = -6069452659479224509L;

    private Long userId;

    private List<Long> roleIds;
}

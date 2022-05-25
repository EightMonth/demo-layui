package com.example.demolayui.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/25 16:30
 */
@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = -7108668574811337506L;

    private String username;
    private String password;
}

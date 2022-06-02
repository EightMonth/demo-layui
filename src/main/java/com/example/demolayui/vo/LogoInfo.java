package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/2 13:51
 */
@Data
public class LogoInfo implements Serializable {

    private static final long serialVersionUID = 3371914786117288872L;

    private String title;
    private String image;
    private String href;
}

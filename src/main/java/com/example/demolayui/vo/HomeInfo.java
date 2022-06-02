package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/2 13:50
 */
@Data
public class HomeInfo implements Serializable {
    private static final long serialVersionUID = 5589513928908379126L;

    private String title;
    private String href;
}

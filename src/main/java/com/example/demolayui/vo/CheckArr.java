package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/14 20:30
 */
@Data
public class CheckArr implements Serializable {

    private static final long serialVersionUID = 3813429635832614250L;

    /** 复选框标记*/
    private String type;
    /** 复选框是否选中*/
    private String checked;

    public CheckArr() {
        this.type = "0";
        this.checked = "0";
    }
}

package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/2 14:26
 */
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 1007922859806400775L;

    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

}

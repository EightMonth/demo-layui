package com.example.demolayui.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/14 18:19
 */
@Data
public class MenuVO implements Serializable {

    private static final long serialVersionUID = -7986459513252073888L;

    private Long id;
    private String title;
    private boolean last;
    private Long parentId;
    private List<MenuVO> children;
    private List<CheckArr> checkArr = new ArrayList<>();

}

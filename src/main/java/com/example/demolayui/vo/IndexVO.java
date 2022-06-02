package com.example.demolayui.vo;

import com.example.demolayui.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/6/2 13:52
 */
@Data
public class IndexVO implements Serializable {
    private static final long serialVersionUID = -5319390712885894269L;

    private HomeInfo homeInfo;
    private LogoInfo logoInfo;
    private List<SysMenu> menuInfo;
}

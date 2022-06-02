package com.example.demolayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolayui.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 14:14
 */
@Mapper
public interface MenuMapper extends BaseMapper<SysMenu> {
    @Select("select * from t_sys_menu where code = #{code}")
    SysMenu findByCode(@Param("code") String code);

    @Select("select * from t_sys_menu where parent_id = #{parentId}")
    List<SysMenu> findByParenId(@Param("parentId") Long parentId);
}

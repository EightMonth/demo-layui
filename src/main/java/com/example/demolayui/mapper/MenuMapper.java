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

    @Select({"<script>select * from t_sys_menu where parent_id = #{parentId} and id in <foreach collection='authorityMenu' item='authId' open='(' separator=',' close=')'>#{authId}</foreach></script>"})
    List<SysMenu> findByParenId(@Param("parentId") Long parentId, @Param("authorityMenu") List<Long> authorityMenuIds);

    @Select({"<script>select m.* from t_sys_menu m inner join t_sys_role_menu rm on m.id = rm.menu_id where rm.role_id in <foreach collection='roleIds' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>"})
    List<SysMenu> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}

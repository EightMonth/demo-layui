package com.example.demolayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolayui.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:05
 */
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {

    @Select("<script>" +
            "select * from t_sys_role where name in " +
            "<foreach item='name' index='index' collection='names' open='(' separator=',' close=')' >" +
            " #{name} " +
            "</foreach>" +
            "</script>")
    List<SysRole> findByNames(@Param("names") List<String> names);

    @Select("select r.* from t_sys_role r inner join t_sys_user_role ur on ur.role_id=r.id where ur.user_id = #{userId}")
    List<SysRole> findByUserId(@Param("userId") Long userId);

    @Insert("insert into t_sys_role_menu(roleId, menuId) values(#{roleId}, #{menuId})))")
    void addMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    @Delete("delete from t_sys_role_menu where roleId = #{roleId}")
    void removeAllMenu(@Param("roleId") Long roleId);
}

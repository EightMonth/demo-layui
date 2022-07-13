package com.example.demolayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolayui.entity.SysUser;
import org.apache.ibatis.annotations.*;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 16:46
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    @Select("select * from t_sys_user where username = #{username}")
    public SysUser findByUsername(@Param("username") String username);

    @Delete("delete from t_sys_user_role where user_id = #{userId}")
    public void deleteUserRole(@Param("userId") Long userId);

    @Insert("insert into t_sys_user_role(`user_id`, `role_id`) values(#{userId}, #{roleId})")
    public void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}

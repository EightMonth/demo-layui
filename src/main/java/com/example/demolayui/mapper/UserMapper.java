package com.example.demolayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolayui.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 16:46
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    @Select("select * from t_sys_user where username = #{username}")
    public SysUser findByUsername(@Param("username") String username);
}

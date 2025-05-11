package cn.dails.dao;

import cn.dails.dao.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.SysUserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserDao  extends BaseMapper<SysUserEntity> {

    @Select("SELECT * FROM dails_sys_user WHERE username = #{username} LIMIT 1")
    SysUserEntity selectByUsername(String username);

    @Select("SELECT r.* FROM dails_sys_role r " +
            "JOIN dails_sys_user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<SysRoleEntity> selectRolesByUserId(Long userId);

}

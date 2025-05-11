package cn.dails.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleDao  extends BaseMapper<SysRoleEntity> {

    @Select({
            "SELECT DISTINCT r.role_code",
            "FROM dails_sys_user_role ur",
            "JOIN dails_sys_role r ON ur.role_id = r.id",
            "WHERE ur.user_id = #{userId}"
    })
    List<String> findRoleCodesByUserId(@Param("userId") Long userId);

    @Select("SELECT r.* FROM dails_sys_role r " +
            "JOIN dails_sys_user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<SysRoleEntity> selectRolesByUserId(@Param("userId") Long userId);


    @Select("SELECT r.* FROM dails_sys_role r " +
            "JOIN dails_sys_role_permission rp ON r.id = rp.role_id " +
            "WHERE rp.permission_id = #{permissionId}")
    List<SysRoleEntity> selectRolesByPermissionId(@Param("permissionId") Long permissionId);
}

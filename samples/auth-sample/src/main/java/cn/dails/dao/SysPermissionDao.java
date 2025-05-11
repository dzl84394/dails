package cn.dails.dao;

import cn.dails.dao.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.SysPermissionEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionDao  extends BaseMapper<SysPermissionEntity> {

    @Select("SELECT p.* FROM dails_sys_permission p " +
            "JOIN dails_sys_role_permission rp ON p.id = rp.permission_id " +
            "JOIN dails_sys_user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<SysPermissionEntity> selectPermissionsByUserId(Long userId);

    @Select("SELECT COUNT(*) > 0 FROM dails_sys_permission p " +
            "JOIN dails_sys_role_permission rp ON p.id = rp.permission_id " +
            "JOIN dails_sys_user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} " +
            "AND p.resource = #{resource} "
           )
    boolean checkUserPermission(@Param("userId") Long userId,
                                @Param("resource") String resource);
}


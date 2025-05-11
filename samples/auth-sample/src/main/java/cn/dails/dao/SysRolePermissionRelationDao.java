package cn.dails.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.SysRolePermissionRelationEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRolePermissionRelationDao  extends BaseMapper<SysRolePermissionRelationEntity> {

    @Select({
            "SELECT DISTINCT p.id",
            "FROM dails_sys_user_role ur",
            "JOIN dails_sys_role_permission rp ON ur.role_id = rp.role_id",
            "JOIN dails_sys_permission p ON rp.permission_id = p.id",
            "WHERE ur.user_id = #{userId}"
    })
    List<Long> findPermCodesByUserId(@Param("userId") Long userId);

}

package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

//RBAC
@Entity
@Table(name = "dails_sys_user_role")
@TableName(value = "dails_sys_user_role",autoResultMap = true)
@Data
public class SysUserRoleRelationEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;


    private Long roleId;
    private Long userId;

    public SysUserRoleRelationEntity() {
    }

    public SysUserRoleRelationEntity(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "dails_sys_role_permission")
@TableName(value = "dails_sys_role_permission",autoResultMap = true)
@Data
public class SysRolePermissionRelation extends BaseEntity implements Serializable {
//     `id` int NOT NULL AUTO_INCREMENT,
//  `role_id` int NOT NULL COMMENT '角色ID',
//            `menu_id` int NOT NULL COMMENT '菜单ID',

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;
    private Long permissioId;

}

package cn.dails.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Entity
@Table(name = "dails_sys_role")
@TableName(value = "dails_sys_role",autoResultMap = true)
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;//项目编号或服务号
    private String serviceName;//微服务名字
    private String  roleCode;//role_
    private String  name;


    private String  deptId;
    private String  roleType;//
    private String  remark;
    private Long  status = 0L;//1 启用 0 禁用',


//     `       role_id` bigint NOT NULL,
//            `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
//            `role_type` int NULL DEFAULT NULL COMMENT '0、自定义角色1、管理角色 2、客户管理角色 3、人事角色 4、财务角色 5、项目角色 8、项目自定义角色',
//            `sorting` int NULL DEFAULT 0 COMMENT '排序',
//            `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
//            `status` int NULL DEFAULT 1 COMMENT '1 启用 0 禁用',
//            `data_type` int NOT NULL DEFAULT 5 COMMENT '数据权限 1、本人，2、本人及下属，3、本部门，4、本部门及下属部门，5、全部 ',
//            `is_hidden` int NOT NULL DEFAULT 1 COMMENT '0 隐藏 1 不隐藏',
//            `label` int NULL DEFAULT NULL COMMENT '1 系统项目管理员角色 2 项目管理角色 3 项目编辑角色 4 项目只读角色',


}

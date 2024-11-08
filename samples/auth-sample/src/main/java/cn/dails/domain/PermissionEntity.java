package cn.dails.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Entity
@Table(name = "dails_sys_permission")
@TableName(value = "dails_sys_permission",autoResultMap = true)
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;//项目编号或服务号
    private String serviceName;//微服务名字

    private Long parentId = 0L;

    private String mtype = "目录";// 1目录 2 菜单 3 按钮 4,页面，5url',
    private String requestType ;//GET,POST,PATCH.PUT,
    private Integer sort = 0;
    private String status = "启用";//'状态 1 启用 0 禁用',
    private String remarks ;//备注

    //    `menu_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
//            `parent_id` int UNSIGNED NULL DEFAULT 0 COMMENT '上级菜单ID',
//            `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单名称',
//            `realm` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限标识',
//            `realm_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限URL',
//            `realm_module` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属模块',
//            `menu_type` int NULL DEFAULT NULL COMMENT '菜单类型  1目录 2 菜单 3 按钮 4特殊',
//            `sort` int UNSIGNED NULL DEFAULT 0 COMMENT '排序（同级有效）',
//            `status` int NULL DEFAULT 1 COMMENT '状态 1 启用 0 禁用',
//            `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单说明',
//            `project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '1普通项目 2敏捷项目',


}

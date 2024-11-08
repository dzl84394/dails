package cn.dails.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "dails_sys_user")
@TableName(value = "dails_sys_user",autoResultMap = true)
@Data
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long deptId;
    private Long roleId;
    private String role;
    private String username;
    private String umCode;
    private String mobile;
    private String email;
    private String status;

    private Long reportId;//汇报对象
    private Long contactId;//替补，交接人

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT )
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.UPDATE )
    private Date updateDate;

    @TableField(fill = FieldFill.INSERT )
    private String creator;

    @TableField(fill = FieldFill.UPDATE )
    private String updater;

    /**
     * 逻辑删除
     * value = "" 默认的原值
     * delval = "" 删除后的值
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT )
    private Integer deleted;

    private boolean enabled = true ;//禁用，1是禁用

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private String password;// 用户密码



    public String getUsername() {
        return username;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    public boolean isEnabled() {
        return enabled;
    }


//  `employee_id` bigint NOT NULL COMMENT '员工id',
//            `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工姓名',
//            `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
//            `country` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家地区',
//            `nation` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '民族',
//            `id_type` int NULL DEFAULT NULL COMMENT '证件类型 1 身份证 2 港澳通行证 3 台湾通行证 4 护照 5 其他',
//            `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号码',
//            `sex` int NULL DEFAULT NULL COMMENT '性别 1 男 2 女',
//            `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
//            `native_place` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '籍贯',
//            `date_of_birth` datetime NULL DEFAULT NULL COMMENT '出生日期',
//            `birthday_type` int NULL DEFAULT 1 COMMENT '生日类型 1 阳历 2 农历',
//            `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日 示例：0323',
//            `age` int NULL DEFAULT NULL COMMENT '年龄',
//            `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '户籍地址',
//            `highest_education` int NULL DEFAULT NULL COMMENT '最高学历',
//            `entry_time` datetime NULL DEFAULT NULL COMMENT '入职时间',
//            `probation` int NULL DEFAULT NULL COMMENT '试用期 0 无试用期',
//            `become_time` datetime NULL DEFAULT NULL COMMENT '转正日期',
//            `job_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
//  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
//            `parent_id` bigint NULL DEFAULT NULL COMMENT '直属上级ID',
//            `post` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
//            `post_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位职级',
//            `work_address` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作地点',
//            `work_detail_address` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作详细地址',
//            `work_city` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作城市',
//            `channel_id` bigint NULL DEFAULT NULL COMMENT '招聘渠道',
//            `employment_forms` int NULL DEFAULT NULL COMMENT '聘用形式 1 正式 2 非正式',
//            `status` int NULL DEFAULT NULL COMMENT '员工状态 1正式 2试用  3实习 4兼职 5劳务 6顾问 7返聘 8外包',
//            `company_age_start_time` datetime NULL DEFAULT NULL COMMENT '司龄开始日期',
//            `company_age` int NULL DEFAULT 0 COMMENT '司龄',
//            `entry_status` int NULL DEFAULT NULL COMMENT '入职状态 1 在职 2 待入职 3 待离职 4 离职',
//            `candidate_id` bigint NULL DEFAULT NULL COMMENT '候选人id',
//            `is_del` int NULL DEFAULT 0 COMMENT '0 未删除 1 删除',
}

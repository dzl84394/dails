package cn.dails.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dails_sys_dept")
@TableName(value = "dails_sys_dept",autoResultMap = true)
@Data
public class DeptEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long parentId = 0L;
    private int  deptType=0;// '0，未知，1 集团，2 公司 3 部门',4项目组
    private String  code;//集团3位，公司2位，部门3位，项目组3位
    private String parentCode ;
    private String  name;


    private Long  owerId;//部门负责人
//    `dept_id` bigint NOT NULL,
//            `parent_id` bigint NULL DEFAULT 0 COMMENT '父级ID 顶级部门为0',
//            `dept_type` int NULL DEFAULT NULL COMMENT '1 公司 2 部门',
//            `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '部门名称',
//            `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门编码',
//            `main_employee_id` bigint NULL DEFAULT NULL COMMENT '部门负责人ID',
//            `leader_employee_id` bigint NULL DEFAULT NULL COMMENT '分管领导',


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
}

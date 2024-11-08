package cn.dails.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 地区省市县，行业，银行
 */
@Entity
@Table(name = "dails_sys_conf")
@TableName(value = "dails_sys_conf",autoResultMap = true)
@Data
public class ConfCommEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3806354313724979772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String clsType;//区域,pmp
    private String subType;//省、市、县,

    private String code;//编号
    private String name;//名称




    private String  parentId;//父节点id
    private String  parentCode;
    private String  parentName;



    private int status = 0;//默认是0，-1是错误，，1已过期（不主动展示，但是能翻译）
    private String mark;//掩码，加密
    private String ten;//业务

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

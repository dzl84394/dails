package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 地区省市县，行业，银行
 */
@Entity
@Table(name = "dails_sys_conf")
@TableName(value = "dails_sys_conf",autoResultMap = true)
@Data
public class ConfCommEntity extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3806354313724979772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String clsType;//一级分类
    private String subType;//二级分类

    private String code;//编号
    private String name;//名称




    private String  parentId;//父节点id
    private String  parentCode;
    private String  parentName;



    private int status = 0;//默认是0，-1是错误，，1已过期（不主动展示，但是能翻译）


}

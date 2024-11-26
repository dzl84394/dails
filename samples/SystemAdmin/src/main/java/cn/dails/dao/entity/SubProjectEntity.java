package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_project")
@TableName(value = "dails_sub_project",autoResultMap = true)
@Data
public class SubProjectEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String clsType;//一级分类
    private String subType;//二级分类

    //fls-usp-auth
    private String sn;
    //英文全称
    private String nameEn;
    //中文名字
    private String name;
    //描述
    private String detail;

    //业务所有者团队
    private String businessOwner;

    //技术负责人
    private String technologyOwner;

    private String level;//等级

    //代码库
    private String gitUrl;//代码库

    //数据库，中间件其他依赖
    //开发，测试，生产，其他

}

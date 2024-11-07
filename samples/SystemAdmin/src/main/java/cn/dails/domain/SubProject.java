package cn.dails.domain;

import lombok.Data;

@Data
public class SubProject {

    private Integer id;

    //fls-usp-auth
    private String sn;
    //英文全称
    private String nameEn;
    //中文名字
    private String name;
    //描述
    private String desc;

    //业务所有者团队
    private String businessOwner;

    //技术负责人
    private String technologyOwner;

    private String level;//等级

    //代码库

    //数据库
    //开发，测试，生产，其他


}

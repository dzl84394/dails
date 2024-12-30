package cn.dails.dao.entity;


public class DependencySubApiEntity {

    private String apiType;//api,mq,
    private String topic;//如果是mq，要有topic

    private Long apiA; //接口A
    private String serviceA; //服务A
    private String contactorA; //联系人A

    private Long apiB;
    private String serviceB;
    private String contactorB;
}

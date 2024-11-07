package cn.dails.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 逻辑实体
 */
@Entity
@Table(name = "dails_logical")
@TableName(value = "dails_logical",autoResultMap = true)
@Data
public class LogicalEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String ptype;//subSys（Project,microServices,instance），db，nosql(redis)，bigData，
    // mq(kafka,RabbitMQ,RocketMQ,ActiveMQ,ZeroMQ)，
    // middleware（eureka，gateway,cat,apllp-config，xxljob）
    private String serviceType;//单体subSys，微服务 microServices

    private String subType;//上面的二级类型
    private String srcType;//外购无源码，外购有源码，开源，自研，二开
    private String devType;//业务，管理，监控，运维，基础技术服务
    private String language;//java python，go，其他
    private String languageVersion;//
    private String level;//1级重要，2级一般，3级不重要

    private String registrarType;//eureka，nacos ，Service Mesh，Zookeeper/Consul

    private String projectCode;//如果是单体，自己就是微服务本身
    private String projectName;//
    private String serviceName;//applicationName，服务注册名称

    private Long parentId;//
    private String parentCode;//
    private String parentName;

    private String detail;//详情


    private String projectGroup;//项目集，部门线，area
    private Long ptId;//团队id

    private String pmContactor;//项目联系人
    private String bmContactor;//使用方联系人
    private String operatior;//运维负责人

    private boolean demon;//是否是示范

}


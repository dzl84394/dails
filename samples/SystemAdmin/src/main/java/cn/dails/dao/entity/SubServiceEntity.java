package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_service")
@TableName(value = "dails_sub_service",autoResultMap = true)
@Data
public class SubServiceEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long subProjectId;
    private Long subProjectSn;

    private String serviceSn;

    private String serviceName;

    private String serviceType;
    //类别，网关，公共组件 Microservices middleware （eureka，配置中心，xxljob）
    private String language;//语言

    private String serviceSubType;//应用微服务，eureka，网关，
    //mq,数据库，缓存，nosql，其他中间件

    //分类：微服务，数据库，nosql，mq，网关，其他中间件

}

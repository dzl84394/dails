package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_middleware_dependency")
@TableName(value = "dails_middleware_dependency",autoResultMap = true)
@Data
public class DependencyMiddlewareEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
 //middleware
    private Long middlewareId;
    private String middlewareType;//mq，注册中心，定时任务
    private String subProjectSn;
    private String middlewareSn;//逻辑实体

    private Long bid;
    private String serviceTypeB;//mq，注册中心，定时任务
    private String subProjectSnB;
    private String subServiceSnB;//逻辑实体
    private String nameB;//

}

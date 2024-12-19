package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_service_dependency")
@TableName(value = "dails_service_dependency",autoResultMap = true)
@Data
public class ServiceDependencyEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
 //middleware
    private Long aid;
    private String serviceType;//mq，注册中心，定时任务
    private String subProjectSn;
    private String subServiceSn;//逻辑实体
    private String name;//

    private Long bid;
    private String serviceTypeB;//mq，注册中心，定时任务
    private String subProjectSnB;
    private String subServiceSnB;//逻辑实体
    private String nameB;//

}

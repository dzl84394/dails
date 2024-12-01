package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_mq")
@TableName(value = "dails_sub_mq",autoResultMap = true)
@Data
public class MqEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String mqType;// 服务器、network网络设备和存储设备
    private String subProjectSn;
    private String subServiceSn;//逻辑实体
    // mqid ，逻辑实体
    // topic
    private String topic;
    private String detail;//描述
    // 生产者 逻辑实体（app）  serviceName,负责人
    // 消费者 逻辑实体，serviceName，负责人
}

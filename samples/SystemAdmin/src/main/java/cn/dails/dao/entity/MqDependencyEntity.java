package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_mq_dependency")
@TableName(value = "dails_sub_mq_dependency",autoResultMap = true)
@Data
public class MqDependencyEntity  extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String subServiceSn;//逻辑实体
    private String mqType;//kafka,mq,还是啥
    private String topic;//


    private String client;//描述

    private String clientType;//生产还是消费者
    private String clientUser;//groupId
}

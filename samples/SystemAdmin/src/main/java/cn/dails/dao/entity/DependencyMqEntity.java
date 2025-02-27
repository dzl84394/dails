package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_dependency_mq")
@TableName(value = "dails_dependency_mq",autoResultMap = true)
@Data
public class DependencyMqEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String mqSn;
    private String mqType;//kafka,mq,还是啥
    private String exchangeName;//rabbit
    private String exchangeType;
    private String quene;//rabbit
    private String routingkey;//rabbit

    private String topic;
    private String clientId;
    private String groupId;

    private String role;//生产者还是消费者,Producer生产&Consumer消费，ALL


    private String projectSn;
    private String serviceSn;//逻辑实

    private String status;
    private String env;//环境

}

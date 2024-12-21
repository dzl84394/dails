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
public class DependencyMqEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long mqId;//逻辑实体
    private String mqSn;
    private String mqType;//kafka,mq,还是啥
    private String topic;//

    private String role;//生产者还是消费者,生产&消费


    private Long followId;
    private String subProjectSnFollow;
    private String subServiceSnFollow;//逻辑实
    private String clienttName;
    private String groupName;
    private String status;
    private String env;//环境

}

package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_subapi_scope")
@TableName(value = "dails_subapi_scope",autoResultMap = true)
@Data
public class SubApiScopeEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;


    private String projectSn;
    private String serviceSn;// application serviceName
    private String scope;//子系统内，企业内，公共

    private String parentScope;//父节点，正常情况下是没有二级节点的
}

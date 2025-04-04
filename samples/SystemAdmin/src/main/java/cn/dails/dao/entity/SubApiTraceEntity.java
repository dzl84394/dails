package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_api_trace")
@TableName(value = "dails_sub_api_trace",autoResultMap = true)
@Data
public class SubApiTraceEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    //起点服务接口
    //上游服务接口
    //下游服务接口
    private String projectSn;
    private String serviceSnRoot;// application serviceName
    private String scope;//子系统内，企业内，公共
    private String className;//cn.dails.controller.SubServiceController
    private String methodName;//类里面的函数名 findList


}

package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "dails_fault_record")
@TableName(value = "dails_fault_record",autoResultMap = true)
@Data
public class FaultRecordEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id; // 记录的唯一标识
    private String faultDescription; // 故障描述
    private String reportedBy; // 报告人
    private Date   reportTime; // 报告时间
    private String impactScope; // 影响范围
    private long   duration; // 故障持续时间（单位：小时）
    private String resolution; // 解决方案
    private String resolutionProcess; // 处理过程
    private Date   resolutionTime; // 解决日期

    private String followUpScope; // 后续补充范围
    private String rootCause; // 根因记录
    private String affectedServices; // 影响的服务
    private String affectedMiddleware; // 影响的中间件
    private String improvementMeasures; // 处理改进措施

    private String status; // 故障状态（如：已解决、未解决等）

}

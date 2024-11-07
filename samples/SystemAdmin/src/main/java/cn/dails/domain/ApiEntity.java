package cn.dails.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_api")
@TableName(value = "dails_api",autoResultMap = true)
@Data
public class ApiEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long logicalId;

    private String serviceName;

    private String request;
    private HttpMethod method;//GET

    private String header;
    private String url;
    private String host;
    private int port;
    private String urlRaw;
    private String response;
    // 定时任务
    // apiType ;//GET,POST,PUT,DELETE，PATCH部分修改
    // project
    // application serviceName
    // url
    // 入参
    // 出参
    // 入参demo
    // 出参demo


}

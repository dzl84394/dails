package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_api")
@TableName(value = "dails_sub_api",autoResultMap = true)
@Data
public class SubApiEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;


    private String projectSn;
    private String serviceSn;// application serviceName



    private String method;//GET,POST,PUT,DELETE，PATCH部分修改
    private String path;

    private String header;
    private String url;
    private String host;
    private int port;
    private String urlRaw;
    private String requestBody;
    private String response;



    // url
    // 入参
    // 出参
    // 入参demo
    // 出参demo
}

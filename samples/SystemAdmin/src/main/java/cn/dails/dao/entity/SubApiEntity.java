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

    private String apiName;
    private String apiStstus;//0 启用，1禁止
    private String datail;
    private String scope;//子系统内，企业内，公共
    private String apiType;//技术api，客户api（客户经营，客户管理，业务流程），管理员api

    private String method;//GET,POST,PUT,DELETE，PATCH部分修改
    private String protocol;//http,https
    private String level;//等级，1级是核心，2级普通，3级无所谓

    private String url;
    private String host;//domain
    private int port;
    private String path;
    private String headerParms;
    private String urlParms;//url参数
    private String pathParms;//path参数
    private String requestBody;
    private String response;
    private String requestDemo;//curl的例子
    private String responseDemo;




}

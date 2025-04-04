package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dails_news_topic")
@TableName(value = "dails_news_topic",autoResultMap = true)
@Data
public class NewsTopicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private int num;//被关联了几个

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT )
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.UPDATE )
    private Date updateDate;

    @TableField(fill = FieldFill.INSERT )
    private String creator;

    @TableField(fill = FieldFill.UPDATE )
    private String updater;

    /**
     * 逻辑删除
     * value = "" 默认的原值
     * delval = "" 删除后的值
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT )
    private Integer deleted;

}

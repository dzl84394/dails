package cn.dails.dao.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dails_news")
@TableName(value = "dails_news",autoResultMap = true)
@Data
public class NewsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;



    private String category;//hotNews热点新闻 ,Joke 笑话，Soup鸡汤文
    private String newsTag;//政治，军事，财经，娱乐，体育，八卦，游戏，汽车


    private Long newsTopic;// 父节点
    private String newsTopicStr;// 父节点

    private String title;


    private String summary;
    private String detail;

    private String source;//来源，baidu，uc，zhihu

    private long number;
    private int  hotIndex;//排序
    private int  repeatCount = 1;//重复次数
    private String date;
    private int everyone = 0;//出圈




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

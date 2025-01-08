package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


//软件资产管理
@Entity
@Table(name = "dails_software_asset")
@TableName(value = "dails_software_asset",autoResultMap = true)
@Data
public class SoftwareAssetEntity  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String assetType;//资产类别，oracle License ，ssl

    private String assetName;//名称
    private String projectSn;//可能被摸个子系统用了
    private String um;//可能被谁用了，
//    private String serviceSn;//

    private Date startDate;

    private Date endDate;

}

package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_devicecenter")
@TableName(value = "dails_sub_devicecenter",autoResultMap = true)
@Data
public class DeviceCenterEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;

    private String deviceType;//数据中心，机房，机柜
    //机房
    // ，机柜，机位，物理机，存储，网络设备，云主机，pks
    private String deviceCenter;
    private String deviceCenterSn;
    //机房
    private String deviceRoom;//三号楼4层，a机房
    private String deviceRoomSn;

    //机柜
    private String cabinetName;
    private String rowcell;//位置，搂层房间号-X排Y行-第几个
    private String cabinetSn;

    //物理机
    private String rackSeat;//机位，45U
    private int rackHigh = 1;//3U
    private String rackSn;//SZF-1121A-1205-3U




    private String env;//DMZ,SF,STG,网管区，环境
    private String status;//Active', 'Inactive', 'Maintenance'
}

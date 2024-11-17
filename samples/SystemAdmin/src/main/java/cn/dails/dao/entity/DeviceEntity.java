package cn.dails.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dails_sub_device")
@TableName(value = "dails_sub_device",autoResultMap = true)
@Data
public class DeviceEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deviceType;//
    //机房
    // ，机柜，机位，物理机，存储，网络设备，云主机，pks
    private String deviceCenter;
    private String deviceCenterSn;
    //机房
    private String deviceRoom;
    private String deviceRoomSn;

    //机柜
    private String rowcell;//位置，搂层房间号-X排Y行-第几个
    private String rowcellSn;

    //物理机
    private String seat;//机位，45U
    private String seatNum;//3U
    private String seatSn;//SZF-1121A-1205-3U


    private String lscpu;//cpu信息
    private String freem;//内存情况
    private String disk;//磁盘情况
    private String os;
    private String ip;//依赖设备.网络设备忽略

    private String env;//DMZ,SF,STG,网管区，环境
    private String status;//Active', 'Inactive', 'Maintenance'
}

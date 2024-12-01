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
    private String deviceType;// 服务器、network网络设备和存储设备
    //机房
    // 物理机，存储，网络设备，云主机，pks
    private String deviceCenterSn;
    //机房
    private String deviceRoomSn;

    //机柜
    private String rackSn;

    //物理机
    private String deviceSn;//SZF-1121A-1205-3U



    private String manufacturern;//制造商，表示物理机的制造商信息。
    private String modeln;;//型号，表示物理机的型号信息。

    private String cpuModeln;//CPU 型号，表示物理机所搭载的 CPU 的型号。
    private String cpuCores;//CPU 核心数，表示物理机 CPU 的核心数量。
    private String memorySize;//内存大小，表示物理机的内存容量。
    private String storageSize;//存储容量，表示物理机的存储容量。
    private String networkInterfaces;//网络接口，表示物理机的网络接口信息。
    private String powerSupply;//电源信息，表示物理机的电源供应情况。
    private String os;//操作系统，表示物理机所安装的操作系统信息。




    private String env;//DMZ,SF,STG,网管区，环境
    private String status;//Active', 'Inactive', 'Maintenance'
}

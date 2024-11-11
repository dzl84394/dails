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
    private String deviceType;
    //机房
    // ，机柜，机位，物理机，存储，网络设备，云主机，pks

    //机房
    private String deviceRoom;
    private String deviceRoomSn;

    //机柜
    private String location;//位置，搂-层-房间号-排行-第几个
    private String locationSn;

    //物理机
    private String seat;
    private String seatSn;

    private String ip;//依赖设备


}

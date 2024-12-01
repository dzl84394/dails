package cn.dails.bean.vo;

import cn.dails.base.bean.PageVo;
import lombok.Data;

@Data
public class DeviceCenterRequestVo extends PageVo {
    private String deviceType;

    private String deviceCenterSn;
    private String deviceRoomSn;
}

package cn.dails.service;


import cn.dails.bean.vo.DeviceRequestVo;
import cn.dails.dao.entity.DeviceEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IDeviceService extends IService<DeviceEntity> {

    IPage<DeviceEntity> findPage(DeviceRequestVo vo);

    List<DeviceEntity> findList(DeviceRequestVo vo) ;

}

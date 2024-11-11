package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.DeviceEntity;
import cn.dails.bean.vo.DeviceRequestVo;


public interface IDeviceService extends IService<DeviceEntity> {

    IPage<DeviceEntity> findPage(DeviceRequestVo vo);

    List<DeviceEntity> findList(DeviceRequestVo vo) ;

}

package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.DeviceCenterEntity;
import cn.dails.bean.vo.DeviceCenterRequestVo;


public interface IDeviceCenterService extends IService<DeviceCenterEntity> {

    IPage<DeviceCenterEntity> findPage(DeviceCenterRequestVo vo);

    List<DeviceCenterEntity> findList(DeviceCenterRequestVo vo) ;

}

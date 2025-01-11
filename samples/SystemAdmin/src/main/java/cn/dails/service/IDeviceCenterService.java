package cn.dails.service;


import cn.dails.bean.vo.DeviceCenterRequestVo;
import cn.dails.dao.entity.DeviceCenterEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IDeviceCenterService extends IService<DeviceCenterEntity> {

    IPage<DeviceCenterEntity> findPage(DeviceCenterRequestVo vo);

    List<DeviceCenterEntity> findList(DeviceCenterRequestVo vo) ;

}

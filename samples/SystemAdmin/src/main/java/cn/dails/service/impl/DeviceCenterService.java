package cn.dails.service.impl;


import cn.dails.dao.entity.DeviceEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import cn.dails.dao.DeviceCenterDao;
import cn.dails.dao.entity.DeviceCenterEntity;
import cn.dails.service.IDeviceCenterService;
import cn.dails.bean.vo.DeviceCenterRequestVo;


@Service
@Slf4j
public class DeviceCenterService extends ServiceImpl<DeviceCenterDao,DeviceCenterEntity> implements IDeviceCenterService {

   @Autowired
      private DeviceCenterDao dao;

      @Override
      public IPage<DeviceCenterEntity> findPage(DeviceCenterRequestVo vo) {
          IPage<DeviceCenterEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<DeviceCenterEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getDeviceType())){
              wrapper.eq(DeviceCenterEntity::getDeviceType,vo.getDeviceType());
          }
           wrapper.orderByDesc(DeviceCenterEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<DeviceCenterEntity> findList(DeviceCenterRequestVo vo) {
          LambdaQueryWrapper<DeviceCenterEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getDeviceType())){
              wrapper.eq(DeviceCenterEntity::getDeviceType,vo.getDeviceType());
          }
          if (!Strings.isNullOrEmpty(vo.getDeviceCenterSn())){
              wrapper.eq(DeviceCenterEntity::getDeviceCenterSn,vo.getDeviceCenterSn());
          }
          if (!Strings.isNullOrEmpty(vo.getDeviceRoomSn())){
              wrapper.eq(DeviceCenterEntity::getDeviceRoomSn,vo.getDeviceRoomSn());
          }
          List<DeviceCenterEntity> list = dao.selectList(wrapper);
          return list;
      }



}

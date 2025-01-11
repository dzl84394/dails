package cn.dails.service.impl;


import cn.dails.bean.vo.DeviceRequestVo;
import cn.dails.dao.DeviceDao;
import cn.dails.dao.entity.DeviceEntity;
import cn.dails.service.IDeviceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class DeviceService extends ServiceImpl<DeviceDao,DeviceEntity> implements IDeviceService {

   @Autowired
      private DeviceDao dao;

      @Override
      public IPage<DeviceEntity> findPage(DeviceRequestVo vo) {
          IPage<DeviceEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());


          LambdaQueryWrapper<DeviceEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getDeviceType())){
              wrapper.eq(DeviceEntity::getDeviceType,vo.getDeviceType());
          }
           wrapper.orderByDesc(DeviceEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<DeviceEntity> findList(DeviceRequestVo vo) {
          LambdaQueryWrapper<DeviceEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getDeviceType())){
              wrapper.eq(DeviceEntity::getDeviceType,vo.getDeviceType());
          }
          List<DeviceEntity> list = dao.selectList(wrapper);
          return list;
      }



}

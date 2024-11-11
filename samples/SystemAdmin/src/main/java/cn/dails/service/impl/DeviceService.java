package cn.dails.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import cn.dails.dao.DeviceDao;
import cn.dails.dao.entity.DeviceEntity;
import cn.dails.service.IDeviceService;
import cn.dails.bean.vo.DeviceRequestVo;


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
           wrapper.orderByDesc(DeviceEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<DeviceEntity> findList(DeviceRequestVo vo) {
          LambdaQueryWrapper<DeviceEntity> wrapper = new LambdaQueryWrapper<>();

          List<DeviceEntity> list = dao.selectList(wrapper);
          return list;
      }



}
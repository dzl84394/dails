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


import cn.dails.dao.ServiceAdminDao;
import cn.dails.dao.entity.ServiceAdminEntity;
import cn.dails.service.IServiceAdminService;
import cn.dails.bean.vo.ServiceAdminRequestVo;


@Service
@Slf4j
public class ServiceAdminService extends ServiceImpl<ServiceAdminDao,ServiceAdminEntity> implements IServiceAdminService {

   @Autowired
      private ServiceAdminDao dao;

      @Override
      public IPage<ServiceAdminEntity> findPage(ServiceAdminRequestVo vo) {
          IPage<ServiceAdminEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<ServiceAdminEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(ServiceAdminEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<ServiceAdminEntity> findList(ServiceAdminRequestVo vo) {
          LambdaQueryWrapper<ServiceAdminEntity> wrapper = new LambdaQueryWrapper<>();

          List<ServiceAdminEntity> list = dao.selectList(wrapper);
          return list;
      }



}

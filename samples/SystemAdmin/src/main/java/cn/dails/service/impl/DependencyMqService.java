package cn.dails.service.impl;


import cn.dails.bean.vo.DependencyMqRequestVo;
import cn.dails.dao.DependencyMqDao;
import cn.dails.dao.entity.DependencyMqEntity;
import cn.dails.service.IDependencyMqService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class DependencyMqService extends ServiceImpl<DependencyMqDao,DependencyMqEntity> implements IDependencyMqService {

   @Autowired
      private DependencyMqDao dao;

      @Override
      public IPage<DependencyMqEntity> findPage(DependencyMqRequestVo vo) {
          IPage<DependencyMqEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<DependencyMqEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(DependencyMqEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<DependencyMqEntity> findList(DependencyMqRequestVo vo) {
          LambdaQueryWrapper<DependencyMqEntity> wrapper = new LambdaQueryWrapper<>();

          List<DependencyMqEntity> list = dao.selectList(wrapper);
          return list;
      }



}

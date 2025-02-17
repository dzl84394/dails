package cn.dails.service.impl;


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


import cn.dails.dao.DependencyServiceDao;
import cn.dails.dao.entity.DependencyServiceEntity;
import cn.dails.service.IDependencyServiceService;
import cn.dails.bean.vo.DependencyServiceRequestVo;


@Service
@Slf4j
public class DependencyServiceService extends ServiceImpl<DependencyServiceDao,DependencyServiceEntity> implements IDependencyServiceService {

   @Autowired
      private DependencyServiceDao dao;

      @Override
      public IPage<DependencyServiceEntity> findPage(DependencyServiceRequestVo vo) {
          IPage<DependencyServiceEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<DependencyServiceEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(DependencyServiceEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }
      @Override
      public List<DependencyServiceEntity> findList(DependencyServiceRequestVo vo) {
          LambdaQueryWrapper<DependencyServiceEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getSubProjectSna())){
              wrapper.eq(DependencyServiceEntity::getSubProjectSna,vo.getSubProjectSna());
          }
          if (!Strings.isNullOrEmpty(vo.getSubServiceSna())){
              wrapper.eq(DependencyServiceEntity::getServiceTypea,vo.getSubServiceSna());
          }
          List<DependencyServiceEntity> list = dao.selectList(wrapper);
          return list;
      }



}

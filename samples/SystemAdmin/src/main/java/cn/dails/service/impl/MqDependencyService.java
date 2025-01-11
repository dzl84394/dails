package cn.dails.service.impl;


import cn.dails.bean.vo.MqDependencyRequestVo;
import cn.dails.dao.MqDependencyDao;
import cn.dails.dao.entity.DependencyMqEntity;
import cn.dails.service.IMqDependencyService;
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
public class MqDependencyService extends ServiceImpl<MqDependencyDao, DependencyMqEntity> implements IMqDependencyService {

   @Autowired
      private MqDependencyDao dao;

      @Override
      public IPage<DependencyMqEntity> findPage(MqDependencyRequestVo vo) {
          IPage<DependencyMqEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<DependencyMqEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(DependencyMqEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<DependencyMqEntity> findList(MqDependencyRequestVo vo) {
          LambdaQueryWrapper<DependencyMqEntity> wrapper = new LambdaQueryWrapper<>();

          List<DependencyMqEntity> list = dao.selectList(wrapper);
          return list;
      }



}

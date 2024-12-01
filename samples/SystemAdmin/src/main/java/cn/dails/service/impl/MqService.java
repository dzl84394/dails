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


import cn.dails.dao.MqDao;
import cn.dails.dao.entity.MqEntity;
import cn.dails.service.IMqService;
import cn.dails.bean.vo.MqRequestVo;


@Service
@Slf4j
public class MqService extends ServiceImpl<MqDao,MqEntity> implements IMqService {

   @Autowired
      private MqDao dao;

      @Override
      public IPage<MqEntity> findPage(MqRequestVo vo) {
          IPage<MqEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<MqEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(MqEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<MqEntity> findList(MqRequestVo vo) {
          LambdaQueryWrapper<MqEntity> wrapper = new LambdaQueryWrapper<>();

          List<MqEntity> list = dao.selectList(wrapper);
          return list;
      }



}

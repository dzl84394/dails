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


import cn.dails.dao.ConfCommDao;
import cn.dails.dao.entity.ConfCommEntity;
import cn.dails.service.IConfCommService;
import cn.dails.bean.vo.ConfCommRequestVo;


@Service
@Slf4j
public class ConfCommService extends ServiceImpl<ConfCommDao,ConfCommEntity> implements IConfCommService {

   @Autowired
      private ConfCommDao dao;

      @Override
      public IPage<ConfCommEntity> findPage(ConfCommRequestVo vo) {
          IPage<ConfCommEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<ConfCommEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(ConfCommEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<ConfCommEntity> findList(ConfCommRequestVo vo) {
          LambdaQueryWrapper<ConfCommEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getClsType())){
              wrapper.eq(ConfCommEntity::getClsType,vo.getClsType());
          }
          if (!Strings.isNullOrEmpty(vo.getSubType())){
              wrapper.eq(ConfCommEntity::getSubType,vo.getSubType());
          }
          List<ConfCommEntity> list = dao.selectList(wrapper);
          return list;
      }



}

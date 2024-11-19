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


import cn.dails.dao.SubApiDao;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.service.ISubApiService;
import cn.dails.bean.vo.SubApiRequestVo;


@Service
@Slf4j
public class SubApiService extends ServiceImpl<SubApiDao,SubApiEntity> implements ISubApiService {

   @Autowired
      private SubApiDao dao;

      @Override
      public IPage<SubApiEntity> findPage(SubApiRequestVo vo) {
          IPage<SubApiEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SubApiEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SubApiEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SubApiEntity> findList(SubApiRequestVo vo) {
          LambdaQueryWrapper<SubApiEntity> wrapper = new LambdaQueryWrapper<>();

          List<SubApiEntity> list = dao.selectList(wrapper);
          return list;
      }



}

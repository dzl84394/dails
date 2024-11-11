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


import cn.dails.dao.SubServiceDao;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.service.ISubServiceService;
import cn.dails.bean.vo.SubServiceRequestVo;


@Service
@Slf4j
public class SubServiceService extends ServiceImpl<SubServiceDao,SubServiceEntity> implements ISubServiceService {

   @Autowired
      private SubServiceDao dao;

      @Override
      public IPage<SubServiceEntity> findPage(SubServiceRequestVo vo) {
          IPage<SubServiceEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SubServiceEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SubServiceEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SubServiceEntity> findList(SubServiceRequestVo vo) {
          LambdaQueryWrapper<SubServiceEntity> wrapper = new LambdaQueryWrapper<>();

          List<SubServiceEntity> list = dao.selectList(wrapper);
          return list;
      }



}

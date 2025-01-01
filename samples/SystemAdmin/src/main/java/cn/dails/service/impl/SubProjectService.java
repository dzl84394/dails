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


import cn.dails.dao.SubProjectDao;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.service.ISubProjectService;
import cn.dails.bean.vo.SubProjectRequestVo;


@Service
@Slf4j
public class SubProjectService extends ServiceImpl<SubProjectDao,SubProjectEntity> implements ISubProjectService {

   @Autowired
      private SubProjectDao dao;

      @Override
      public IPage<SubProjectEntity> findPage(SubProjectRequestVo vo) {
          IPage<SubProjectEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SubProjectEntity> wrapper = new LambdaQueryWrapper<>();
//           wrapper.orderByDesc(SubProjectEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SubProjectEntity> findList(SubProjectRequestVo vo) {
          LambdaQueryWrapper<SubProjectEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getSubProjectSn())){
              wrapper.eq(SubProjectEntity::getProjectSn,vo.getSubProjectSn());
          }
          List<SubProjectEntity> list = dao.selectList(wrapper);
          return list;
      }



}

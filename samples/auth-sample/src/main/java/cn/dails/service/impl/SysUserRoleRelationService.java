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


import cn.dails.dao.SysUserRoleRelationDao;
import cn.dails.dao.entity.SysUserRoleRelationEntity;
import cn.dails.service.ISysUserRoleRelationService;
import cn.dails.bean.vo.SysUserRoleRelationSearch;


@Service
@Slf4j
public class SysUserRoleRelationService extends ServiceImpl<SysUserRoleRelationDao,SysUserRoleRelationEntity> implements ISysUserRoleRelationService {

   @Autowired
      private SysUserRoleRelationDao dao;

      @Override
      public IPage<SysUserRoleRelationEntity> findPage(SysUserRoleRelationSearch vo) {
          IPage<SysUserRoleRelationEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SysUserRoleRelationEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SysUserRoleRelationEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SysUserRoleRelationEntity> findList(SysUserRoleRelationSearch vo) {
          LambdaQueryWrapper<SysUserRoleRelationEntity> wrapper = new LambdaQueryWrapper<>();

          List<SysUserRoleRelationEntity> list = dao.selectList(wrapper);
          return list;
      }



}

package cn.dails.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


import cn.dails.dao.SysRolePermissionRelationDao;
import cn.dails.dao.entity.SysRolePermissionRelationEntity;
import cn.dails.service.ISysRolePermissionRelationService;
import cn.dails.bean.vo.SysRolePermissionRelationSearch;


@Service
@Slf4j
public class SysRolePermissionRelationService extends ServiceImpl<SysRolePermissionRelationDao,SysRolePermissionRelationEntity> implements ISysRolePermissionRelationService {

   @Autowired
      private SysRolePermissionRelationDao dao;

      @Override
      public IPage<SysRolePermissionRelationEntity> findPage(SysRolePermissionRelationSearch vo) {
          IPage<SysRolePermissionRelationEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SysRolePermissionRelationEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SysRolePermissionRelationEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SysRolePermissionRelationEntity> findList(SysRolePermissionRelationSearch vo) {
          LambdaQueryWrapper<SysRolePermissionRelationEntity> wrapper = new LambdaQueryWrapper<>();

          List<SysRolePermissionRelationEntity> list = dao.selectList(wrapper);
          return list;
      }

    public List<SysRolePermissionRelationEntity> findList(Long roleId) {
        LambdaQueryWrapper<SysRolePermissionRelationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRolePermissionRelationEntity::getRoleId ,roleId);
        List<SysRolePermissionRelationEntity> list = dao.selectList(wrapper);
        return list;
    }





}

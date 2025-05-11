package cn.dails.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import cn.dails.dao.SysPermissionDao;
import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.service.ISysPermissionService;
import cn.dails.bean.vo.SysPermissionSearch;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysPermissionService extends ServiceImpl<SysPermissionDao,SysPermissionEntity> implements ISysPermissionService {


    private final SysPermissionDao dao;
    @Override
    public IPage<SysPermissionEntity> findPage(SysPermissionSearch vo) {
          IPage<SysPermissionEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SysPermissionEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SysPermissionEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
    }

      @Override
      public List<SysPermissionEntity> findList(SysPermissionSearch vo) {
          LambdaQueryWrapper<SysPermissionEntity> wrapper = new LambdaQueryWrapper<>();

          List<SysPermissionEntity> list = dao.selectList(wrapper);
          return list;
      }

    public List<SysPermissionEntity> findList(List ids) {
        LambdaQueryWrapper<SysPermissionEntity> wrapper = new LambdaQueryWrapper<>();

        List<SysPermissionEntity> list = dao.selectList(wrapper);
        return list;
    }

    public List<SysPermissionEntity> findAll() {
        LambdaQueryWrapper<SysPermissionEntity> wrapper = new LambdaQueryWrapper<>();

        List<SysPermissionEntity> list = dao.selectList(wrapper);
        return list;
    }


    public List<SysPermissionEntity> getPermissionsByUserId(Long userId) {
        return dao.selectPermissionsByUserId(userId);
    }

    public List<SysPermissionEntity> getUserPermissions(Long userId) {
        return dao.selectPermissionsByUserId(userId);
    }

    public boolean hasPermission(Long userId, String resource, String action) {
        return dao.checkUserPermission(userId, resource);
    }



}

package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.bean.vo.SysPermissionSearch;


public interface ISysPermissionService extends IService<SysPermissionEntity> {

    IPage<SysPermissionEntity> findPage(SysPermissionSearch vo);

    List<SysPermissionEntity> findList(SysPermissionSearch vo) ;

    List<SysPermissionEntity> getPermissionsByUserId(Long userId) ;

    List<SysPermissionEntity> findAll();
}

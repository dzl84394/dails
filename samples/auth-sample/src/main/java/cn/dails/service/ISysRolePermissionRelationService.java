package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SysRolePermissionRelationEntity;
import cn.dails.bean.vo.SysRolePermissionRelationSearch;


public interface ISysRolePermissionRelationService extends IService<SysRolePermissionRelationEntity> {

    IPage<SysRolePermissionRelationEntity> findPage(SysRolePermissionRelationSearch vo);

    List<SysRolePermissionRelationEntity> findList(SysRolePermissionRelationSearch vo) ;

}

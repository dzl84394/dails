package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SysUserRoleRelationEntity;
import cn.dails.bean.vo.SysUserRoleRelationSearch;


public interface ISysUserRoleRelationService extends IService<SysUserRoleRelationEntity> {

    IPage<SysUserRoleRelationEntity> findPage(SysUserRoleRelationSearch vo);

    List<SysUserRoleRelationEntity> findList(SysUserRoleRelationSearch vo) ;

}

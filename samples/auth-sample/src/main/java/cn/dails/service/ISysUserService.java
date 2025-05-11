package cn.dails.service;


import cn.dails.dao.entity.SysRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SysUserEntity;
import cn.dails.bean.vo.SysUserSearch;


public interface ISysUserService extends IService<SysUserEntity> {

    IPage<SysUserEntity> findPage(SysUserSearch vo);

    List<SysUserEntity> findList(SysUserSearch vo) ;



    public SysUserEntity findByUsername(String username);
    public List<SysRoleEntity> getUserRoles(Long userId);

}

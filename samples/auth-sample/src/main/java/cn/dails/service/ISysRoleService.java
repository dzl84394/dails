package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.bean.vo.SysRoleSearch;
import org.apache.ibatis.annotations.Param;


public interface ISysRoleService extends IService<SysRoleEntity> {

    IPage<SysRoleEntity> findPage(SysRoleSearch vo);

    List<SysRoleEntity> findList(SysRoleSearch vo) ;
    List<String> findRoleCodesByUserId(@Param("userId") Long userId);

}

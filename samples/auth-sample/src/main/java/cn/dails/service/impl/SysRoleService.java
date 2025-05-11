package cn.dails.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import cn.dails.dao.SysRoleDao;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.service.ISysRoleService;
import cn.dails.bean.vo.SysRoleSearch;


@Service
@Slf4j
public class SysRoleService extends ServiceImpl<SysRoleDao,SysRoleEntity> implements ISysRoleService {
    @Autowired
    private SysRoleDao dao;

    @Override
    public IPage<SysRoleEntity> findPage(SysRoleSearch vo) {
        IPage<SysRoleEntity> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getSize());

        LambdaQueryWrapper<SysRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SysRoleEntity::getCreateDate);
        page = dao.selectPage(page,wrapper);

        return page;
    }

    @Override
    public List<SysRoleEntity> findList(SysRoleSearch vo) {
        LambdaQueryWrapper<SysRoleEntity> wrapper = new LambdaQueryWrapper<>();

        List<SysRoleEntity> list = dao.selectList(wrapper);
        return list;
    }

    @Override
    public List<String> findRoleCodesByUserId(@Param("userId") Long userId){
       return  dao.findRoleCodesByUserId(userId);
    }

}

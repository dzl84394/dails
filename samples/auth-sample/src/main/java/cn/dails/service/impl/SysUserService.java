package cn.dails.service.impl;


import cn.dails.bean.vo.SysUserSearch;
import cn.dails.dao.SysUserDao;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.dao.entity.SysUserEntity;
import cn.dails.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserDao,SysUserEntity> implements ISysUserService {

    private final SysUserDao dao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public IPage<SysUserEntity> findPage(SysUserSearch vo) {
        IPage<SysUserEntity> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getSize());

        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SysUserEntity::getCreateDate);
        page = dao.selectPage(page,wrapper);

        return page;
    }



    @Override
    public List<SysUserEntity> findList(SysUserSearch vo) {
       LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();

        List<SysUserEntity> list = dao.selectList(wrapper);
        return list;
    }

    @Override
    public SysUserEntity findByUsername(String username) {
        return dao.selectOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username));
    }
    @Override
    public List<SysRoleEntity> getUserRoles(Long userId) {
        return dao.selectRolesByUserId(userId);
    }


}

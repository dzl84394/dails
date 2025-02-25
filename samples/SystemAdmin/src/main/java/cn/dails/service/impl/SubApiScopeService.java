package cn.dails.service.impl;


import cn.dails.dao.entity.SubApiEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import cn.dails.dao.SubApiScopeDao;
import cn.dails.dao.entity.SubApiScopeEntity;
import cn.dails.service.ISubApiScopeService;
import cn.dails.bean.vo.SubApiScopeRequestVo;


@Service
@Slf4j
public class SubApiScopeService extends ServiceImpl<SubApiScopeDao,SubApiScopeEntity> implements ISubApiScopeService {

    @Autowired
    private SubApiScopeDao dao;
    @Override
    public IPage<SubApiScopeEntity> findPage(SubApiScopeRequestVo vo) {
        IPage<SubApiScopeEntity> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getSize());

        LambdaQueryWrapper<SubApiScopeEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(vo.getProjectSn())){
            wrapper.eq(SubApiScopeEntity::getProjectSn,vo.getProjectSn());
        }
        if (!Strings.isNullOrEmpty(vo.getServiceSn())){
            wrapper.eq(SubApiScopeEntity::getServiceSn,vo.getServiceSn());
        }
        wrapper.orderByDesc(SubApiScopeEntity::getCreateDate);
        page = dao.selectPage(page,wrapper);

       return page;
    }

    @Override
    public List<SubApiScopeEntity> findList(SubApiScopeRequestVo vo) {
        LambdaQueryWrapper<SubApiScopeEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(vo.getProjectSn())){
            wrapper.eq(SubApiScopeEntity::getProjectSn,vo.getProjectSn());
        }
        if (!Strings.isNullOrEmpty(vo.getServiceSn())){
            wrapper.eq(SubApiScopeEntity::getServiceSn,vo.getServiceSn());
        }
        List<SubApiScopeEntity> list = dao.selectList(wrapper);
        return list;
    }

    @Override
    public SubApiScopeEntity saveByName(SubApiEntity api) {
        LambdaQueryWrapper<SubApiScopeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubApiScopeEntity::getProjectSn,api.getProjectSn());
        wrapper.eq(SubApiScopeEntity::getServiceSn,api.getServiceSn());
        wrapper.eq(SubApiScopeEntity::getScope,api.getScope());
        SubApiScopeEntity entity = dao.selectOne(wrapper);
        if (entity==null){
            entity = new SubApiScopeEntity();
            entity.setProjectSn(api.getProjectSn());
            entity.setServiceSn(api.getServiceSn());
            entity.setScope(api.getScope());
            dao.insert(entity);
        }
        return entity;
    }
    @Override
    public SubApiScopeEntity saveByName(String projectSn,String serviceSn,String scope) {
        LambdaQueryWrapper<SubApiScopeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubApiScopeEntity::getProjectSn,projectSn);
        wrapper.eq(SubApiScopeEntity::getServiceSn,serviceSn);
        wrapper.eq(SubApiScopeEntity::getScope,scope);
        SubApiScopeEntity entity = dao.selectOne(wrapper);
        if (entity==null){
            entity = new SubApiScopeEntity();
            entity.setProjectSn(projectSn);
            entity.setServiceSn(serviceSn);
            entity.setScope(scope);
            dao.insert(entity);
        }
        return entity;
    }


}

package cn.dails.service.impl;


import cn.dails.dao.entity.SubApiEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


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
           wrapper.orderByDesc(SubApiScopeEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SubApiScopeEntity> findList(SubApiScopeRequestVo vo) {
          LambdaQueryWrapper<SubApiScopeEntity> wrapper = new LambdaQueryWrapper<>();

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


}

package cn.dails.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


import cn.dails.dao.SoftwareAssetDao;
import cn.dails.dao.entity.SoftwareAssetEntity;
import cn.dails.service.ISoftwareAssetService;
import cn.dails.bean.vo.SoftwareAssetRequestVo;


@Service
@Slf4j
public class SoftwareAssetService extends ServiceImpl<SoftwareAssetDao,SoftwareAssetEntity> implements ISoftwareAssetService {

   @Autowired
      private SoftwareAssetDao dao;

      @Override
      public IPage<SoftwareAssetEntity> findPage(SoftwareAssetRequestVo vo) {
          IPage<SoftwareAssetEntity> page = new Page<>();
          page.setCurrent(vo.getCurrentPage());
          page.setSize(vo.getSize());

          LambdaQueryWrapper<SoftwareAssetEntity> wrapper = new LambdaQueryWrapper<>();
           wrapper.orderByDesc(SoftwareAssetEntity::getCreateDate);
          page = dao.selectPage(page,wrapper);

          return page;
      }

      @Override
      public List<SoftwareAssetEntity> findList(SoftwareAssetRequestVo vo) {
          LambdaQueryWrapper<SoftwareAssetEntity> wrapper = new LambdaQueryWrapper<>();

          List<SoftwareAssetEntity> list = dao.selectList(wrapper);
          return list;
      }



}

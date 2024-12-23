package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.DependencyMqEntity;
import cn.dails.bean.vo.DependencyMqRequestVo;


public interface IDependencyMqService extends IService<DependencyMqEntity> {

    IPage<DependencyMqEntity> findPage(DependencyMqRequestVo vo);

    List<DependencyMqEntity> findList(DependencyMqRequestVo vo) ;

}

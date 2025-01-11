package cn.dails.service;


import cn.dails.bean.vo.DependencyMqRequestVo;
import cn.dails.dao.entity.DependencyMqEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IDependencyMqService extends IService<DependencyMqEntity> {

    IPage<DependencyMqEntity> findPage(DependencyMqRequestVo vo);

    List<DependencyMqEntity> findList(DependencyMqRequestVo vo) ;

}

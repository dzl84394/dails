package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.DependencyMqEntity;
import cn.dails.bean.vo.MqDependencyRequestVo;


public interface IMqDependencyService extends IService<DependencyMqEntity> {

    IPage<DependencyMqEntity> findPage(MqDependencyRequestVo vo);

    List<DependencyMqEntity> findList(MqDependencyRequestVo vo) ;

}

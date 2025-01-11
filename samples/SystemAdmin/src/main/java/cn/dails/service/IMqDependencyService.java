package cn.dails.service;


import cn.dails.bean.vo.MqDependencyRequestVo;
import cn.dails.dao.entity.DependencyMqEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IMqDependencyService extends IService<DependencyMqEntity> {

    IPage<DependencyMqEntity> findPage(MqDependencyRequestVo vo);

    List<DependencyMqEntity> findList(MqDependencyRequestVo vo) ;

}

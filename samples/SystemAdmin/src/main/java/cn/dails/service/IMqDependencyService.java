package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.MqDependencyEntity;
import cn.dails.bean.vo.MqDependencyRequestVo;


public interface IMqDependencyService extends IService<MqDependencyEntity> {

    IPage<MqDependencyEntity> findPage(MqDependencyRequestVo vo);

    List<MqDependencyEntity> findList(MqDependencyRequestVo vo) ;

}

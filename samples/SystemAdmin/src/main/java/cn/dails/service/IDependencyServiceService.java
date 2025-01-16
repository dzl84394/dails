package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.DependencyServiceEntity;
import cn.dails.bean.vo.DependencyServiceRequestVo;


public interface IDependencyServiceService extends IService<DependencyServiceEntity> {

    IPage<DependencyServiceEntity> findPage(DependencyServiceRequestVo vo);

    List<DependencyServiceEntity> findList(DependencyServiceRequestVo vo) ;

}

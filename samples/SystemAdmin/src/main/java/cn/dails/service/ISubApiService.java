package cn.dails.service;


import cn.dails.bean.vo.SubApiRequestVo;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.dao.entity.SubServiceEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISubApiService extends IService<SubApiEntity> {

    IPage<SubApiEntity> findPage(SubApiRequestVo vo);

    List<SubApiEntity> findList(SubApiRequestVo vo) ;
    void checkMappings(SubServiceEntity subServiceEntity);
}

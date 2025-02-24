package cn.dails.service;


import cn.dails.dao.entity.SubApiEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SubApiScopeEntity;
import cn.dails.bean.vo.SubApiScopeRequestVo;


public interface ISubApiScopeService extends IService<SubApiScopeEntity> {

    IPage<SubApiScopeEntity> findPage(SubApiScopeRequestVo vo);

    List<SubApiScopeEntity> findList(SubApiScopeRequestVo vo) ;

    SubApiScopeEntity saveByName(SubApiEntity api);
    SubApiScopeEntity saveByName(String projectSn,String serviceSn,String scope);

}

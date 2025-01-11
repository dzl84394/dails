package cn.dails.service;


import cn.dails.bean.vo.SubServiceRequestVo;
import cn.dails.dao.entity.SubServiceEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISubServiceService extends IService<SubServiceEntity> {

    IPage<SubServiceEntity> findPage(SubServiceRequestVo vo);

    List<SubServiceEntity> findList(SubServiceRequestVo vo) ;
    SubServiceEntity findByServiceSn(String sn);

    void checkEureka(SubServiceEntity subServiceEntity);

}

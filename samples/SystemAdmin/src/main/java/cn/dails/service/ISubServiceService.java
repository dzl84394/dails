package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.bean.vo.SubServiceRequestVo;


public interface ISubServiceService extends IService<SubServiceEntity> {

    IPage<SubServiceEntity> findPage(SubServiceRequestVo vo);

    List<SubServiceEntity> findList(SubServiceRequestVo vo) ;

}

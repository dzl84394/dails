package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SubApiEntity;
import cn.dails.bean.vo.SubApiRequestVo;


public interface ISubApiService extends IService<SubApiEntity> {

    IPage<SubApiEntity> findPage(SubApiRequestVo vo);

    List<SubApiEntity> findList(SubApiRequestVo vo) ;

}

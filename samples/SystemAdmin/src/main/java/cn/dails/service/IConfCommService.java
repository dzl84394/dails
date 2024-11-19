package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.ConfCommEntity;
import cn.dails.bean.vo.ConfCommRequestVo;


public interface IConfCommService extends IService<ConfCommEntity> {

    IPage<ConfCommEntity> findPage(ConfCommRequestVo vo);

    List<ConfCommEntity> findList(ConfCommRequestVo vo) ;

}

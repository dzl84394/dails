package cn.dails.service;


import cn.dails.bean.vo.ConfCommRequestVo;
import cn.dails.dao.entity.ConfCommEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IConfCommService extends IService<ConfCommEntity> {

    IPage<ConfCommEntity> findPage(ConfCommRequestVo vo);

    List<ConfCommEntity> findList(ConfCommRequestVo vo) ;

}

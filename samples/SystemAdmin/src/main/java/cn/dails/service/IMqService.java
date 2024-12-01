package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.MqEntity;
import cn.dails.bean.vo.MqRequestVo;


public interface IMqService extends IService<MqEntity> {

    IPage<MqEntity> findPage(MqRequestVo vo);

    List<MqEntity> findList(MqRequestVo vo) ;

}

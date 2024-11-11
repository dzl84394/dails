package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.bean.vo.SubProjectRequestVo;


public interface ISubProjectService extends IService<SubProjectEntity> {

    IPage<SubProjectEntity> findPage(SubProjectRequestVo vo);

    List<SubProjectEntity> findList(SubProjectRequestVo vo) ;

}

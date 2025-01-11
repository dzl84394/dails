package cn.dails.service;


import cn.dails.bean.vo.SubProjectRequestVo;
import cn.dails.dao.entity.SubProjectEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISubProjectService extends IService<SubProjectEntity> {

    IPage<SubProjectEntity> findPage(SubProjectRequestVo vo);

    List<SubProjectEntity> findList(SubProjectRequestVo vo) ;

}

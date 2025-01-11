package cn.dails.service;


import cn.dails.bean.vo.SoftwareAssetRequestVo;
import cn.dails.dao.entity.SoftwareAssetEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISoftwareAssetService extends IService<SoftwareAssetEntity> {

    IPage<SoftwareAssetEntity> findPage(SoftwareAssetRequestVo vo);

    List<SoftwareAssetEntity> findList(SoftwareAssetRequestVo vo) ;

}

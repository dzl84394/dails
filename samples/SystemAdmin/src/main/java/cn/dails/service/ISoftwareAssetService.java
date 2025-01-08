package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.SoftwareAssetEntity;
import cn.dails.bean.vo.SoftwareAssetRequestVo;


public interface ISoftwareAssetService extends IService<SoftwareAssetEntity> {

    IPage<SoftwareAssetEntity> findPage(SoftwareAssetRequestVo vo);

    List<SoftwareAssetEntity> findList(SoftwareAssetRequestVo vo) ;

}

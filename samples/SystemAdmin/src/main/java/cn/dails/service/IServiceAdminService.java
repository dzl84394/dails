package cn.dails.service;


import cn.dails.bean.vo.ServiceAdminRequestVo;
import cn.dails.dao.entity.ServiceAdminEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IServiceAdminService extends IService<ServiceAdminEntity> {

    IPage<ServiceAdminEntity> findPage(ServiceAdminRequestVo vo);

    List<ServiceAdminEntity> findList(ServiceAdminRequestVo vo) ;

}

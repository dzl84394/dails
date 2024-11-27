package cn.dails.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import cn.dails.dao.entity.ServiceAdminEntity;
import cn.dails.bean.vo.ServiceAdminRequestVo;


public interface IServiceAdminService extends IService<ServiceAdminEntity> {

    IPage<ServiceAdminEntity> findPage(ServiceAdminRequestVo vo);

    List<ServiceAdminEntity> findList(ServiceAdminRequestVo vo) ;

}

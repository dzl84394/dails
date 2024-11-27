package cn.dails.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.ServiceAdminEntity;

@Mapper
public interface ServiceAdminDao  extends BaseMapper<ServiceAdminEntity> {
}

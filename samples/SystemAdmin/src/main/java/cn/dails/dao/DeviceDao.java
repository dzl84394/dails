package cn.dails.dao;

import cn.dails.dao.entity.DeviceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceDao  extends BaseMapper<DeviceEntity> {
}

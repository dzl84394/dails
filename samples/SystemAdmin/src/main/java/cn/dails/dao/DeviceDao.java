package cn.dails.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.DeviceEntity;

@Mapper
public interface DeviceDao  extends BaseMapper<DeviceEntity> {
}
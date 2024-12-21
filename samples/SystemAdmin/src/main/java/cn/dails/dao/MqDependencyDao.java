package cn.dails.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import cn.dails.dao.entity.DependencyMqEntity;

@Mapper
public interface MqDependencyDao  extends BaseMapper<DependencyMqEntity> {
}

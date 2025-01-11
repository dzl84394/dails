package cn.dails.dao;

import cn.dails.dao.entity.DependencyMqEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MqDependencyDao  extends BaseMapper<DependencyMqEntity> {
}

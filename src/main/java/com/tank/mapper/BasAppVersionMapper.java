package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasAppVersion;
import com.tank.model.BasAppVersionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAppVersionMapper {
    int countByExample(BasAppVersionExample example);

    int deleteByExample(BasAppVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAppVersion record);

    int insertSelective(BasAppVersion record);

    List<BasAppVersion> selectByExample(BasAppVersionExample example);

    BasAppVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAppVersion record, @Param("example") BasAppVersionExample example);

    int updateByExample(@Param("record") BasAppVersion record, @Param("example") BasAppVersionExample example);

    int updateByPrimaryKeySelective(BasAppVersion record);

    int updateByPrimaryKey(BasAppVersion record);
}
package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasRegion;
import com.tank.model.BasRegionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasRegionMapper {
    int countByExample(BasRegionExample example);

    int deleteByExample(BasRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasRegion record);

    int insertSelective(BasRegion record);

    List<BasRegion> selectByExample(BasRegionExample example);

    BasRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasRegion record, @Param("example") BasRegionExample example);

    int updateByExample(@Param("record") BasRegion record, @Param("example") BasRegionExample example);

    int updateByPrimaryKeySelective(BasRegion record);

    int updateByPrimaryKey(BasRegion record);
}
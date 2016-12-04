package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BusinessHot;
import com.tank.model.BusinessHotExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BusinessHotMapper {
    int countByExample(BusinessHotExample example);

    int deleteByExample(BusinessHotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessHot record);

    int insertSelective(BusinessHot record);

    List<BusinessHot> selectByExample(BusinessHotExample example);

    BusinessHot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessHot record, @Param("example") BusinessHotExample example);

    int updateByExample(@Param("record") BusinessHot record, @Param("example") BusinessHotExample example);

    int updateByPrimaryKeySelective(BusinessHot record);

    int updateByPrimaryKey(BusinessHot record);
}
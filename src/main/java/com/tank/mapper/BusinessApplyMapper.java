package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BusinessApply;
import com.tank.model.BusinessApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BusinessApplyMapper {
    int countByExample(BusinessApplyExample example);

    int deleteByExample(BusinessApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessApply record);

    int insertSelective(BusinessApply record);

    List<BusinessApply> selectByExample(BusinessApplyExample example);

    BusinessApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessApply record, @Param("example") BusinessApplyExample example);

    int updateByExample(@Param("record") BusinessApply record, @Param("example") BusinessApplyExample example);

    int updateByPrimaryKeySelective(BusinessApply record);

    int updateByPrimaryKey(BusinessApply record);
}
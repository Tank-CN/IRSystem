package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BusinessCollect;
import com.tank.model.BusinessCollectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BusinessCollectMapper {
    int countByExample(BusinessCollectExample example);

    int deleteByExample(BusinessCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessCollect record);

    int insertSelective(BusinessCollect record);

    List<BusinessCollect> selectByExample(BusinessCollectExample example);

    BusinessCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessCollect record, @Param("example") BusinessCollectExample example);

    int updateByExample(@Param("record") BusinessCollect record, @Param("example") BusinessCollectExample example);

    int updateByPrimaryKeySelective(BusinessCollect record);

    int updateByPrimaryKey(BusinessCollect record);
}
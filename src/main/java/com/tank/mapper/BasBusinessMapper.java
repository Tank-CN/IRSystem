package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasBusiness;
import com.tank.model.BasBusinessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@BatisRepository
public interface BasBusinessMapper {
    int countByExample(BasBusinessExample example);

    int deleteByExample(BasBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasBusiness record);

    int insertSelective(BasBusiness record);

    List<BasBusiness> selectByExample(BasBusinessExample example);

    BasBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasBusiness record, @Param("example") BasBusinessExample example);

    int updateByExample(@Param("record") BasBusiness record, @Param("example") BasBusinessExample example);

    int updateByPrimaryKeySelective(BasBusiness record);

    int updateByPrimaryKey(BasBusiness record);
}
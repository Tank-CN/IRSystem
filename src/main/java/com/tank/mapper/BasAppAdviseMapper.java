package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasAppAdvise;
import com.tank.model.BasAppAdviseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAppAdviseMapper {
    int countByExample(BasAppAdviseExample example);

    int deleteByExample(BasAppAdviseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAppAdvise record);

    int insertSelective(BasAppAdvise record);

    List<BasAppAdvise> selectByExample(BasAppAdviseExample example);

    BasAppAdvise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAppAdvise record, @Param("example") BasAppAdviseExample example);

    int updateByExample(@Param("record") BasAppAdvise record, @Param("example") BasAppAdviseExample example);

    int updateByPrimaryKeySelective(BasAppAdvise record);

    int updateByPrimaryKey(BasAppAdvise record);
}
package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasAttention;
import com.tank.model.BasAttentionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAttentionMapper {
    int countByExample(BasAttentionExample example);

    int deleteByExample(BasAttentionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAttention record);

    int insertSelective(BasAttention record);

    List<BasAttention> selectByExample(BasAttentionExample example);

    BasAttention selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAttention record, @Param("example") BasAttentionExample example);

    int updateByExample(@Param("record") BasAttention record, @Param("example") BasAttentionExample example);

    int updateByPrimaryKeySelective(BasAttention record);

    int updateByPrimaryKey(BasAttention record);
}
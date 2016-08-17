package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.ActivitySignup;
import com.tank.model.ActivitySignupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface ActivitySignupMapper {
    int countByExample(ActivitySignupExample example);

    int deleteByExample(ActivitySignupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivitySignup record);

    int insertSelective(ActivitySignup record);

    List<ActivitySignup> selectByExample(ActivitySignupExample example);

    ActivitySignup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivitySignup record, @Param("example") ActivitySignupExample example);

    int updateByExample(@Param("record") ActivitySignup record, @Param("example") ActivitySignupExample example);

    int updateByPrimaryKeySelective(ActivitySignup record);

    int updateByPrimaryKey(ActivitySignup record);
}
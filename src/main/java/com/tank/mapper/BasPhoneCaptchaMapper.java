package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasPhoneCaptcha;
import com.tank.model.BasPhoneCaptchaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasPhoneCaptchaMapper {
    int countByExample(BasPhoneCaptchaExample example);

    int deleteByExample(BasPhoneCaptchaExample example);

    int deleteByPrimaryKey(String mobile);

    int insert(BasPhoneCaptcha record);

    int insertSelective(BasPhoneCaptcha record);

    List<BasPhoneCaptcha> selectByExample(BasPhoneCaptchaExample example);

    BasPhoneCaptcha selectByPrimaryKey(String mobile);

    int updateByExampleSelective(@Param("record") BasPhoneCaptcha record, @Param("example") BasPhoneCaptchaExample example);

    int updateByExample(@Param("record") BasPhoneCaptcha record, @Param("example") BasPhoneCaptchaExample example);

    int updateByPrimaryKeySelective(BasPhoneCaptcha record);

    int updateByPrimaryKey(BasPhoneCaptcha record);
}
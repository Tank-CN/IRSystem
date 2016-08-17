package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.AppNoticeSetting;
import com.tank.model.AppNoticeSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface AppNoticeSettingMapper {
    int countByExample(AppNoticeSettingExample example);

    int deleteByExample(AppNoticeSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppNoticeSetting record);

    int insertSelective(AppNoticeSetting record);

    List<AppNoticeSetting> selectByExampleWithBLOBs(AppNoticeSettingExample example);

    List<AppNoticeSetting> selectByExample(AppNoticeSettingExample example);

    AppNoticeSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByExample(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByPrimaryKeySelective(AppNoticeSetting record);

    int updateByPrimaryKeyWithBLOBs(AppNoticeSetting record);

    int updateByPrimaryKey(AppNoticeSetting record);
}
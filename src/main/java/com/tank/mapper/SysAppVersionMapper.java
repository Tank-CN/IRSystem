package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.SysAppVersion;
import com.tank.model.SysAppVersionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysAppVersionMapper {
    int countByExample(SysAppVersionExample example);

    int deleteByExample(SysAppVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAppVersion record);

    int insertSelective(SysAppVersion record);
    
    List<SysAppVersion> selectByExample(SysAppVersionExample example);

    SysAppVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAppVersion record, @Param("example") SysAppVersionExample example);

    int updateByExample(@Param("record") SysAppVersion record, @Param("example") SysAppVersionExample example);

    int updateByPrimaryKeySelective(SysAppVersion record);

    int updateByPrimaryKey(SysAppVersion record);
}
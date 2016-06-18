package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.SysModule;
import com.tank.model.SysModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysModuleMapper {
    int countByExample(SysModuleExample example);

    int deleteByExample(SysModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysModule record);

    int insertSelective(SysModule record);
    
    List<SysModule> selectByExample(SysModuleExample example);

    SysModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByExample(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);
}
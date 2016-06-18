package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.SysRoleModule;
import com.tank.model.SysRoleModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysRoleModuleMapper {
    int countByExample(SysRoleModuleExample example);

    int deleteByExample(SysRoleModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleModule record);

    int insertSelective(SysRoleModule record);

    List<SysRoleModule> selectByExample(SysRoleModuleExample example);

    SysRoleModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRoleModule record, @Param("example") SysRoleModuleExample example);

    int updateByExample(@Param("record") SysRoleModule record, @Param("example") SysRoleModuleExample example);

    int updateByPrimaryKeySelective(SysRoleModule record);

    int updateByPrimaryKey(SysRoleModule record);
}
package com.tank.mapper.ex;


import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.SysRoleMapper;
import com.tank.model.SysRole;

@BatisRepository
public interface SysRoleExMapper extends SysRoleMapper {

    int insertBackId(SysRole record);
}
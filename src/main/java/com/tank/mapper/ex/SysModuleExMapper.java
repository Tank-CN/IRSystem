package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.SysModuleMapper;
import com.tank.model.SysModule;

import java.util.List;


@BatisRepository
public interface SysModuleExMapper extends SysModuleMapper {

    List<SysModule> selectByUser(Long userId);
    
    List<SysModule> selectByRole(Long rid);
}

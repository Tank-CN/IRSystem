package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.SysSettingMapper;

import java.util.Map;

@BatisRepository
public interface SysSettingExMapper extends SysSettingMapper {
	
	void updateBatch(Map<String, Object> map);
	
}
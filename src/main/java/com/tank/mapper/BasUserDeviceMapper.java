package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasUserDevice;
import com.tank.model.BasUserDeviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasUserDeviceMapper {
    int countByExample(BasUserDeviceExample example);

    int deleteByExample(BasUserDeviceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasUserDevice record);

    int insertSelective(BasUserDevice record);

    List<BasUserDevice> selectByExample(BasUserDeviceExample example);

    BasUserDevice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasUserDevice record, @Param("example") BasUserDeviceExample example);

    int updateByExample(@Param("record") BasUserDevice record, @Param("example") BasUserDeviceExample example);

    int updateByPrimaryKeySelective(BasUserDevice record);

    int updateByPrimaryKey(BasUserDevice record);
}
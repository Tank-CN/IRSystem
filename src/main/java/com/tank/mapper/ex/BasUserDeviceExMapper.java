package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BasUserDeviceMapper;
import com.tank.model.BasUserDevice;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasUserDeviceExMapper extends BasUserDeviceMapper {

    int insertBackId(BasUserDevice record);
}

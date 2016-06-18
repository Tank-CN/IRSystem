package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BasRegionMapper;
import com.tank.model.BasRegion;


@BatisRepository
public interface BasRegionExMapper extends BasRegionMapper{

    long insertBackId(BasRegion basRegion);
}

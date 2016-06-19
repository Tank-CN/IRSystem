package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.ActivityMapper;
import com.tank.model.Activity;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface ActivityExMapper extends ActivityMapper{

    int insertBackId(Activity record);
}

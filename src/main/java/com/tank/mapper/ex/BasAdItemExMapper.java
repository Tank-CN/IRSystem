package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BasAdItemMapper;
import com.tank.model.BasAdItem;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasAdItemExMapper extends BasAdItemMapper{

    int insertBackId(BasAdItem record);
}

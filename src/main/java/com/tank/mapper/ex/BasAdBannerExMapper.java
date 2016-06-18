package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BasAdBannerMapper;
import com.tank.model.BasAdBanner;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasAdBannerExMapper extends BasAdBannerMapper{

    int insertBackId(BasAdBanner record);
}

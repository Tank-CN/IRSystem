package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.NewsMapper;
import com.tank.model.News;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface NewsExMapper extends NewsMapper {

    int insertBackId(News record);
}

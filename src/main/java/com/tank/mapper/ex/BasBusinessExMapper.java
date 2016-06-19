package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BasBusinessMapper;
import com.tank.model.BasBusiness;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasBusinessExMapper extends BasBusinessMapper {

    int insertBackId(BasBusiness record);
}

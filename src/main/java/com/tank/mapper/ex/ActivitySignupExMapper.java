package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.ActivitySignupMapper;
import com.tank.model.ActivitySignup;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface ActivitySignupExMapper extends ActivitySignupMapper {

    int insertBackId(ActivitySignup record);
}

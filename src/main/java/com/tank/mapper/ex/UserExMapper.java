package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.UserMapper;
import com.tank.model.User;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface UserExMapper extends UserMapper {

    int insertBackId(User record);
}

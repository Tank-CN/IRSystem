package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.AdminMapper;
import com.tank.model.Admin;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface AdminExMapper extends AdminMapper{

    int insertBackId(Admin record);
}

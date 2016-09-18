package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.ActivitySignupMapper;
import com.tank.model.ActivitySignup;
import com.tank.vo.admin.SignCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface ActivitySignupExMapper extends ActivitySignupMapper {

    int insertBackId(ActivitySignup record);

    List<SignCountVo> getSign(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int countSign();
}

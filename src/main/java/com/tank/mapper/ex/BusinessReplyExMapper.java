package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.BusinessReplyMapper;
import com.tank.model.BusinessReply;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BusinessReplyExMapper extends BusinessReplyMapper {

    int insertBackId(BusinessReply record);
}

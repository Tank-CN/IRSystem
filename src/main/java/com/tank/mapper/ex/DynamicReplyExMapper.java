package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.DynamicReplyMapper;
import com.tank.model.DynamicReply;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface DynamicReplyExMapper extends DynamicReplyMapper {

    int insertBackId(DynamicReply record);
}

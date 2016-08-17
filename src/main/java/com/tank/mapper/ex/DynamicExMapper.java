package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.DynamicMapper;
import com.tank.model.Dynamic;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface DynamicExMapper extends DynamicMapper {

    int insertBackId(Dynamic record);

    int replyCountadd1( @Param("id") long id);

    int likeCountadd1( @Param("id") long id);

    int replyCountsub1( @Param("id") long id);

    int likeCountsub1( @Param("id") long id);

}

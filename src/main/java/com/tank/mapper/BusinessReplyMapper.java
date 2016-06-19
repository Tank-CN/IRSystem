package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BusinessReply;
import com.tank.model.BusinessReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@BatisRepository
public interface BusinessReplyMapper {
    int countByExample(BusinessReplyExample example);

    int deleteByExample(BusinessReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessReply record);

    int insertSelective(BusinessReply record);

    List<BusinessReply> selectByExample(BusinessReplyExample example);

    BusinessReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessReply record, @Param("example") BusinessReplyExample example);

    int updateByExample(@Param("record") BusinessReply record, @Param("example") BusinessReplyExample example);

    int updateByPrimaryKeySelective(BusinessReply record);

    int updateByPrimaryKey(BusinessReply record);
}
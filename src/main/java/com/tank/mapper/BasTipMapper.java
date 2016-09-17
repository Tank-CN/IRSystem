package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasTip;
import com.tank.model.BasTipExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasTipMapper {
    int countByExample(BasTipExample example);

    int deleteByExample(BasTipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasTip record);

    int insertSelective(BasTip record);

    List<BasTip> selectByExample(BasTipExample example);

    BasTip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasTip record, @Param("example") BasTipExample example);

    int updateByExample(@Param("record") BasTip record, @Param("example") BasTipExample example);

    int updateByPrimaryKeySelective(BasTip record);

    int updateByPrimaryKey(BasTip record);
}
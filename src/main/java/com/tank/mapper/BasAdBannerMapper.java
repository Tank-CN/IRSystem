package com.tank.mapper;

import com.bs.util.annotation.BatisRepository;
import com.tank.model.BasAdBanner;
import com.tank.model.BasAdBannerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAdBannerMapper {
    int countByExample(BasAdBannerExample example);

    int deleteByExample(BasAdBannerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAdBanner record);

    int insertSelective(BasAdBanner record);

    List<BasAdBanner> selectByExample(BasAdBannerExample example);

    BasAdBanner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAdBanner record, @Param("example") BasAdBannerExample example);

    int updateByExample(@Param("record") BasAdBanner record, @Param("example") BasAdBannerExample example);

    int updateByPrimaryKeySelective(BasAdBanner record);

    int updateByPrimaryKey(BasAdBanner record);
}
package com.tank.manage;

import com.tank.mapper.ex.BasAdBannerExMapper;
import com.tank.model.BasAdBanner;
import com.tank.model.BasAdBannerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasADBannerManage extends BaseManage {

    @Autowired
    BasAdBannerExMapper basAdBannerExMapper;

    public BasAdBanner getById(Long id) {
        return basAdBannerExMapper.selectByPrimaryKey(id);
    }

    public int save(BasAdBanner basRegion) {
        return basAdBannerExMapper.insertSelective(basRegion);
    }

    public int update(BasAdBanner basRegion) {
        return basAdBannerExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return basAdBannerExMapper.deleteByPrimaryKey(id);
    }


    public List<BasAdBanner> list(Integer pageNumber,
                                  Integer pageSize) {
        BasAdBannerExample example = new BasAdBannerExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));

        return basAdBannerExMapper.selectByExample(example);
    }

    public int count() {
        return basAdBannerExMapper.countByExample(null);
    }


    public long insertBackId(BasAdBanner basRegion) {
        if (basAdBannerExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }


}

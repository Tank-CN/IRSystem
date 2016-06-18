package com.tank.manage;

import com.tank.mapper.ex.BasAdItemExMapper;
import com.tank.model.BasAdItem;
import com.tank.model.BasAdItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasADItemManage extends BaseManage {

    @Autowired
    BasAdItemExMapper basAdItemExMapper;

    public BasAdItem getById(Long id) {
        return basAdItemExMapper.selectByPrimaryKey(id);
    }

    public int save(BasAdItem basRegion) {
        return basAdItemExMapper.insertSelective(basRegion);
    }

    public int update(BasAdItem basRegion) {
        return basAdItemExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return basAdItemExMapper.deleteByPrimaryKey(id);
    }


    public List<BasAdItem> list(Integer pageNumber,
                                Integer pageSize) {
        BasAdItemExample example = new BasAdItemExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return basAdItemExMapper.selectByExample(example);
    }

    public int count() {
        return basAdItemExMapper.countByExample(null);
    }

    public long insertBackId(BasAdItem basRegion) {
        if (basAdItemExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }
}

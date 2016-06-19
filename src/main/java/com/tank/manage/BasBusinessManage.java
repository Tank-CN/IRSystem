package com.tank.manage;

import com.tank.mapper.ex.BasBusinessExMapper;
import com.tank.mapper.ex.BusinessReplyExMapper;
import com.tank.model.BasBusiness;
import com.tank.model.BasBusinessExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasBusinessManage extends BaseManage {

    @Autowired
    BasBusinessExMapper basBusinessExMapper;

    @Autowired
    BusinessReplyExMapper businessReplyExMapper;


    public BasBusiness getById(Long id) {
        return basBusinessExMapper.selectByPrimaryKey(id);
    }

    public int save(BasBusiness basRegion) {
        return basBusinessExMapper.insertSelective(basRegion);
    }

    public int update(BasBusiness basRegion) {
        return basBusinessExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return basBusinessExMapper.deleteByPrimaryKey(id);
    }


    public List<BasBusiness> list(Integer pageNumber,
                                  Integer pageSize) {
        BasBusinessExample example = new BasBusinessExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));

        return basBusinessExMapper.selectByExample(example);
    }

    public int count() {
        return basBusinessExMapper.countByExample(null);
    }


    public long insertBackId(BasBusiness basRegion) {
        if (basBusinessExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }

}

package com.tank.manage;

import com.tank.mapper.ex.ActivityExMapper;
import com.tank.mapper.ex.ActivitySignupExMapper;
import com.tank.model.Activity;
import com.tank.model.ActivityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ActivityManage  extends BaseManage {

    @Autowired
    ActivityExMapper activityExMapper;

    @Autowired
    ActivitySignupExMapper activitySignupExMapper;


    public Activity getById(Long id) {
        return activityExMapper.selectByPrimaryKey(id);
    }

    public int save(Activity basRegion) {
        return activityExMapper.insertSelective(basRegion);
    }

    public int update(Activity basRegion) {
        return activityExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return activityExMapper.deleteByPrimaryKey(id);
    }


    public List<Activity> list(Integer pageNumber,
                                  Integer pageSize) {
        ActivityExample example = new ActivityExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return activityExMapper.selectByExample(example);
    }

    public int count() {
        return activityExMapper.countByExample(null);
    }


    public long insertBackId(Activity basRegion) {
        if (activityExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }



}

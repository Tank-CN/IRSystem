package com.tank.manage;

import com.tank.mapper.ex.ActivityExMapper;
import com.tank.mapper.ex.ActivitySignupExMapper;
import com.tank.model.ActivitySignup;
import com.tank.model.ActivitySignupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ActivitySignupManage extends BaseManage {

    @Autowired
    ActivityExMapper activityExMapper;

    @Autowired
    ActivitySignupExMapper activitySignupExMapper;


    public ActivitySignup getById(Long id) {
        return activitySignupExMapper.selectByPrimaryKey(id);
    }

    public int save(ActivitySignup basRegion) {
        return activitySignupExMapper.insertSelective(basRegion);
    }

    public int update(ActivitySignup basRegion) {
        return activitySignupExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return activitySignupExMapper.deleteByPrimaryKey(id);
    }


    public List<ActivitySignup> list(Integer pageNumber,
                                  Integer pageSize) {
        ActivitySignupExample example = new ActivitySignupExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return activitySignupExMapper.selectByExample(example);
    }

    public int count() {
        return activitySignupExMapper.countByExample(null);
    }


    public long insertBackId(ActivitySignup basRegion) {
        if (activitySignupExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }



}

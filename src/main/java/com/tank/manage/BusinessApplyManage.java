package com.tank.manage;

import com.tank.mapper.BusinessApplyMapper;
import com.tank.model.BusinessApply;
import com.tank.model.BusinessApplyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BusinessApplyManage extends BaseManage {

    @Autowired
    BusinessApplyMapper businessApplyMapper;

    @Autowired
    UserManage userManage;


    public boolean save(BusinessApply businessApply) {
        if (businessApplyMapper.insertSelective(businessApply) > 0) {
            return true;
        }
        return false;
    }


    public boolean delete(Long id) {
        if (businessApplyMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        }
        return false;
    }


    public int isApplay(Long uid) {
        BusinessApplyExample example = new BusinessApplyExample();
        BusinessApplyExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<BusinessApply> list = businessApplyMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            BusinessApply apply = list.get(0);
            return apply.getFlag();
        } else {
            return -1;
        }
    }


}

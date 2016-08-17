package com.tank.manage;

import com.tank.mapper.ex.ActivityExMapper;
import com.tank.mapper.ex.ActivitySignupExMapper;
import com.tank.model.*;
import com.tank.vo.ActivitySignUserVo;
import com.tank.vo.ActivityVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class ActivityManage  extends BaseManage {

    @Autowired
    ActivityExMapper activityExMapper;

    @Autowired
    ActivitySignupExMapper activitySignupExMapper;

    @Autowired
    UserManage userManage;


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


    /**
     * 活动参加人数
     * @param aid
     * @return
     */
    public int signCountByAId(Long aid){
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria=example.createCriteria();
        criteria.andAidEqualTo(aid);
        return activitySignupExMapper.countByExample(example);
    }


    /**
     * 所有活动，加入报名数
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivityVo> listAll(Integer pageNumber,
                                      Integer pageSize) {
        ActivityExample example = new ActivityExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<Activity> list= activityExMapper.selectByExample(example);
        if(null!=list&&list.size()>0){
            return parser(list);
        }
        return null;
    }

    /**
     * 用户参加的活动
     *
     * @param uid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivityVo> listByUid(Long uid,Integer pageNumber,
                                    Integer pageSize) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria=example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<Activity> list= activityExMapper.selectByExample(example);
        if(null!=list&&list.size()>0){
            return parser(list);
        }
        return null;
    }


    public List<ActivityVo> parser(List<Activity> list){
        List<ActivityVo> ls=new ArrayList<>();
        for(Activity vo:list){
            ActivityVo av=new ActivityVo();
            try {
                PropertyUtils.copyProperties(av,vo);
                av.setSignCount(signCountByAId(vo.getId()));
                ls.add(av);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }


    /**
     * 活动报名列表
     * @param aid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivitySignUserVo> listSignUserByAid(Long aid,Integer pageNumber,
                                                      Integer pageSize){
        ActivitySignupExample example=new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria=example.createCriteria();
        criteria.andAidEqualTo(aid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<ActivitySignup> list=activitySignupExMapper.selectByExample(example);
        if(null!=list&&list.size()>0){
            List<ActivitySignUserVo> ls=new ArrayList<>();
            User user = null;
            for(ActivitySignup vo:list){
                ActivitySignUserVo av=new ActivitySignUserVo();
                user = userManage.getUserById(vo.getUid());
                if (null != user) {
                    av.setHeader(user.getHeader());
                    av.setUname(user.getNickname());
                }
                av.setUid(vo.getUid());
                av.setAid(vo.getAid());
                av.setCreatedate(vo.getCreatedate());
                av.setId(vo.getId());
                ls.add(av);
            }
            return ls;
        }
        return null;
    }



}

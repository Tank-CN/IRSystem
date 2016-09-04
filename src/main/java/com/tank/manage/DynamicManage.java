package com.tank.manage;

import com.tank.mapper.ex.DynamicExMapper;
import com.tank.mapper.ex.DynamicReplyExMapper;
import com.tank.model.*;
import com.tank.vo.DynamicLikeVo;
import com.tank.vo.DynamicReplyVo;
import com.tank.vo.DynamicVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class DynamicManage extends BaseManage {

    @Autowired
    DynamicExMapper dynamicExMapper;

    @Autowired
    DynamicReplyExMapper dynamicReplyExMapper;

    @Autowired
    UserManage userManage;

    public Dynamic insertBackId(Dynamic dynamic) {
        if (dynamicExMapper.insertBackId(dynamic) > 0) {
            return dynamic;
        }
        return null;
    }


    public int update(Dynamic dynamic) {
        return dynamicExMapper.updateByPrimaryKeySelective(dynamic);
    }


    public List<Dynamic> list(Integer pageNumber,
                              Integer pageSize) {
        DynamicExample example = new DynamicExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return dynamicExMapper.selectByExample(example);
    }

    public List<DynamicReply> listReply(Long did, int type, Integer pageNumber,
                                        Integer pageSize) {
        DynamicReplyExample example = new DynamicReplyExample();
        DynamicReplyExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        criteria.andKindsEqualTo((byte) type);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return dynamicReplyExMapper.selectByExample(example);
    }


    /**
     * 个人动态数
     *
     * @param uid
     * @return
     */
    public int countByUid(Long uid) {
        DynamicExample example = new DynamicExample();
        DynamicExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        return dynamicExMapper.countByExample(example);
    }

    public int countReply(Long did, int type) {
        DynamicReplyExample example = new DynamicReplyExample();
        DynamicReplyExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        criteria.andKindsEqualTo((byte) type);
        return dynamicReplyExMapper.countByExample(example);
    }


    //api//
    public List<DynamicVo> listAll(Integer pageNumber,
                                   Integer pageSize) {
        DynamicExample example = new DynamicExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return parser(dynamicExMapper.selectByExample(example));
    }


    public List<DynamicVo> listByUid(Long uid, Integer pageNumber,
                                     Integer pageSize) {
        DynamicExample example = new DynamicExample();
        DynamicExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return parser(dynamicExMapper.selectByExample(example));
    }


    public List<DynamicVo> parser(List<Dynamic> list) {
        if (null != list && list.size() > 0) {
            List<DynamicVo> ls = new ArrayList<>();
            User user = null;
            for (Dynamic dc : list) {
                DynamicVo vo = new DynamicVo();
                try {
                    PropertyUtils.copyProperties(vo, dc);
                    user = userManage.getUserById(dc.getUid());
                    if (null != user) {
                        vo.setUserVo(getUserVo(user));
                    }
                    //去掉喜欢列表
                    //喜欢列表
//                    vo.setLikeVos(listLikeByDid(dc.getId(), 1, 10));
                    //评论列表
                    vo.setReplyList(listReplyByDid(dc.getId(), 1, 10));
                    ls.add(vo);
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
        return null;
    }


    /**
     * 根据动态ID获取评论列表
     *
     * @param did
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<DynamicReplyVo> listReplyByDid(Long did, Integer pageNumber,
                                               Integer pageSize) {
        DynamicReplyExample example = new DynamicReplyExample();
        DynamicReplyExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        criteria.andKindsEqualTo((byte) 1);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<DynamicReply> list = dynamicReplyExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<DynamicReplyVo> ls = new ArrayList<>();
            User user = null;
            for (DynamicReply r : list) {
                DynamicReplyVo vo = new DynamicReplyVo();
                vo.setId(r.getId());
                vo.setCreatedate(r.getCreatedate());
                vo.setUid(r.getUid());
                vo.setContent(r.getContent());
                vo.setDid(r.getDid());
                user = userManage.getUserById(r.getUid());
                if (null != user) {
                    vo.setUserVo(getUserVo(user));
                }
                ls.add(vo);
            }
            return ls;
        }
        return null;
    }

    /**
     * 根据动态ID获取喜欢列表
     *
     * @param did
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<DynamicLikeVo> listLikeByDid(Long did, Integer pageNumber,
                                             Integer pageSize) {
        DynamicReplyExample example = new DynamicReplyExample();
        DynamicReplyExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        criteria.andKindsEqualTo((byte) 2);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<DynamicReply> list = dynamicReplyExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<DynamicLikeVo> ls = new ArrayList<>();
            for (DynamicReply r : list) {
                DynamicLikeVo vo = new DynamicLikeVo();


                ls.add(vo);
            }
            return ls;
        }
        return null;
    }


    public long addReply(DynamicReply vo) {
        vo.setCreatedate(new Date());
        vo.setKinds((byte) 1);
        if (dynamicReplyExMapper.insertBackId(vo) > 0) {
            dynamicExMapper.replyCountadd1(vo.getDid());
            return vo.getId();
        }
        return 0;
    }

    public long addLike(DynamicReply vo) {
        vo.setCreatedate(new Date());
        vo.setKinds((byte) 2);
        if (dynamicReplyExMapper.insertBackId(vo) > 0) {
            dynamicExMapper.likeCountadd1(vo.getDid());
            return vo.getId();
        }
        return 0;
    }

    public boolean delectLike(Long id) {
        if (dynamicReplyExMapper.deleteByPrimaryKey(id) > 0) {
            dynamicExMapper.likeCountsub1(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean delectReply(Long id) {
        if (dynamicReplyExMapper.deleteByPrimaryKey(id) > 0) {
            dynamicExMapper.replyCountsub1(id);
            return true;
        } else {
            return false;
        }
    }


}

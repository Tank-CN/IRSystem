package com.tank.manage;

import com.tank.mapper.ex.NewsExMapper;
import com.tank.model.News;
import com.tank.model.NewsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class NewsManage  extends BaseManage {

    @Autowired
    NewsExMapper newsExMapper;



    public News getById(Long id) {
        return newsExMapper.selectByPrimaryKey(id);
    }

    public int save(News basRegion) {
        return newsExMapper.insertSelective(basRegion);
    }

    public int update(News basRegion) {
        return newsExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return newsExMapper.deleteByPrimaryKey(id);
    }


    public List<News> list(Integer pageNumber,
                                  Integer pageSize) {
        NewsExample example = new NewsExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));

        return newsExMapper.selectByExample(example);
    }

    public int count() {
        return newsExMapper.countByExample(null);
    }


    public long insertBackId(News basRegion) {
        if (newsExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }



}

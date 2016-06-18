package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.SysDictionaryMapper;
import com.tank.model.SysDictionary;


@BatisRepository
public interface SysDictionaryExMapper extends SysDictionaryMapper {

    int selectMaxIID(Integer parentId);

    int selectMaxCID();
    
    int insertAndGetId(SysDictionary sysDictionary);
}

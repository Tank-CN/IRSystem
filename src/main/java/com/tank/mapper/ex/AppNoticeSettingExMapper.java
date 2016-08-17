package com.tank.mapper.ex;

import com.bs.util.annotation.BatisRepository;
import com.tank.mapper.AppNoticeSettingMapper;
import com.tank.model.AppNoticeSetting;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface AppNoticeSettingExMapper extends AppNoticeSettingMapper {

    int insertBackId(AppNoticeSetting record);
}

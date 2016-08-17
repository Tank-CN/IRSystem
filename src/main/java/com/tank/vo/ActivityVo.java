package com.tank.vo;

import com.tank.model.Activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ActivityVo extends Activity implements Serializable{
    //报名数
    public int signCount;

    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
    }
}

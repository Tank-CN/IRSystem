package com.tank.vo;

import java.io.Serializable;

/**
 * 个人信息主页
 */
public class MyInfoVo implements Serializable{

    public int dynamicCount;
    public int businessCollectCount;
    public String header;
    public String nickname;
    public String info;

    public int getDynamicCount() {
        return dynamicCount;
    }

    public void setDynamicCount(int dynamicCount) {
        this.dynamicCount = dynamicCount;
    }

    public int getBusinessCollectCount() {
        return businessCollectCount;
    }

    public void setBusinessCollectCount(int businessCollectCount) {
        this.businessCollectCount = businessCollectCount;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

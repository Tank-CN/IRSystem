package com.tank.manage;

import com.bs.util.CommonUtils;
import com.tank.model.User;
import com.tank.vo.UserVo;

public class BaseManage {

	/**
	 * Mysql分页字符串
	 * 
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public String getPage(Integer pageNumber, Integer pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append(" id desc ");
		if (offset >= 0) {
			sb.append("limit ").append(offset).append(",").append(pageSize);
		} else {
			sb.append("limit ").append(0).append(",").append(pageSize);
		}
		return sb.toString();
	}

	/**
	 * Mysql分页字符串
	 * 
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public String getPage(String orderStr, Integer pageNumber, Integer pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		StringBuffer sb = new StringBuffer();
		if (CommonUtils.isNull(orderStr)) {
			sb.append(" id desc ");
		} else {
			sb.append(" ").append(orderStr);
		}

		if (offset >= 0) {
			sb.append(" limit ").append(offset).append(",").append(pageSize);
		} else {
			sb.append(" limit ").append(0).append(",").append(pageSize);
		}
		return sb.toString();
	}

	/**
	 * 
	 * 得到开始索引
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public int getOffset(Integer pageNumber, Integer pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		if (offset >= 0) {
			return offset;
		}
		return 0;
	}


	public UserVo getUserVo(User user){
		UserVo userVo=new UserVo();
		userVo.setNickname(user.getNickname());
		userVo.setHeader(user.getHeader());
		userVo.setBirthdate(user.getBirthdate());
		userVo.setId(user.getId());
		userVo.setInfo(user.getInfo());
		userVo.setSexcode(user.getSexcode());
		userVo.setVip(user.getVip());
		return userVo;
	}

}

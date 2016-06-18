//package com.tank.manage;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
///**
// * 设备绑定
// *
// *
// * @author Administrator
// *
// */
//@Component
//@Transactional(readOnly = true)
//public class AccountDeviceManage {
//
//	@Autowired
//	BasAccountDeviceMapper accountDeviceMapper;
//
//	public BasAccountDevice getByUid(Long uid) {
//		BasAccountDeviceExample example = new BasAccountDeviceExample();
//		BasAccountDeviceExample.Criteria criteria = example.createCriteria();
//		criteria.andUidEqualTo(uid);
//		List<BasAccountDevice> list = accountDeviceMapper
//				.selectByExample(example);
//		if (null != list && list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
//	}
//
//	public BasAccountDevice getByUidAndAppType(Long uid, Byte apptype) {
//		BasAccountDeviceExample example = new BasAccountDeviceExample();
//		BasAccountDeviceExample.Criteria criteria = example.createCriteria();
//		criteria.andApptypeEqualTo(apptype);
//		criteria.andUidEqualTo(uid);
//		List<BasAccountDevice> list = accountDeviceMapper
//				.selectByExample(example);
//		if (null != list && list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
//	}
//
//
//
//	public int save(BasAccountDevice device) {
//		return accountDeviceMapper.insertSelective(device);
//	}
//
//	public int update(BasAccountDevice device) {
//		BasAccountDeviceExample example = new BasAccountDeviceExample();
//		BasAccountDeviceExample.Criteria criteria = example.createCriteria();
//		criteria.andUidEqualTo(device.getUid());
//		return accountDeviceMapper.updateByExampleSelective(device, example);
//	}
//
//	public int deleteByUid(Long uid) {
//		BasAccountDeviceExample example = new BasAccountDeviceExample();
//		BasAccountDeviceExample.Criteria criteria = example.createCriteria();
//		criteria.andUidEqualTo(uid);
//		return accountDeviceMapper.deleteByExample(example);
//	}
//
//
//}

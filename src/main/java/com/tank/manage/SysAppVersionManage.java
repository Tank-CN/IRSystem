package com.tank.manage;

import com.tank.mapper.SysAppVersionMapper;
import com.tank.model.SysAppVersion;
import com.tank.model.SysAppVersionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Transactional(readOnly = true)
public class SysAppVersionManage extends BaseManage{
	
	
	public static String ecard_web_url="http://ecard.zgjkw.cn";

	@Autowired
	SysAppVersionMapper sysAppVersionMapper;


	public List<SysAppVersion> list(Integer pageNumber,
									Integer pageSize) {
		SysAppVersionExample example = new SysAppVersionExample();
		example.setOrderByClause(getPage(pageNumber, pageSize));
		return sysAppVersionMapper.selectByExample(example);
	}

	public Integer count() {
		return sysAppVersionMapper.countByExample(null);
	}

	public int save(SysAppVersion appVersion) {
		return sysAppVersionMapper.insert(appVersion);
	}

	public SysAppVersion getByCode(String code) {
		SysAppVersionExample example = new SysAppVersionExample();
		SysAppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andAppcodeEqualTo(code);
		List<SysAppVersion> list=sysAppVersionMapper.selectByExample(example);
		if(null!=list&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	public int update(SysAppVersion appVersion) {
		return sysAppVersionMapper.updateByPrimaryKeySelective(appVersion);
	}

	public int delete(Long id) {
		return sysAppVersionMapper.deleteByPrimaryKey(id);
	}
	
	
	public SysAppVersion getById(Long id) {
		return sysAppVersionMapper.selectByPrimaryKey(id);
	}
	
	

	/**
	 * 版本更新校验
	 * 
	 * @param appCode
	 *            app编号
	 * @param appVersion
	 *            版本号
	 * @return 返回map中code S01 不需要更新 S02需要更新但是不是强制更新 S03为强制更新 data为具体需要更新的对象
	 */
	public Map<String, Object> checkLastUpgradeVersion(String appCode,
			Integer appVersion) {
		Map<String, Object> map = new HashMap<>();
		SysAppVersionExample example = new SysAppVersionExample();
		SysAppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andAppcodeEqualTo(appCode);
		criteria.andAppversionEqualTo(appVersion);
		List<SysAppVersion> appVersionList = sysAppVersionMapper
				.selectByExample(example);

		example = new SysAppVersionExample();
		criteria = example.createCriteria();
		example.setOrderByClause("id desc");
		criteria.andAppcodeEqualTo(appCode);
		criteria.andTypeEqualTo("1");
		List<SysAppVersion> appUpgradeVersion = sysAppVersionMapper
				.selectByExample(example);

		example = new SysAppVersionExample();
		criteria = example.createCriteria();
		example.setOrderByClause("id desc");
		List<SysAppVersion> newUpgradeVersion = sysAppVersionMapper
				.selectByExample(example);

		if (appUpgradeVersion.size() == 0) {
			if (newUpgradeVersion.size() > 0) {
				SysAppVersion newUpgrade = newUpgradeVersion.get(0);
				if (newUpgrade.getAppversion() == appVersion) {
					// 不需要做更新
					map.put("code", "S01");
					return map;
				} else {
					// 需要做更新
					map.put("code", "S02");
					map.put("data", newUpgrade);
					return map;
				}
			} else {
				// 不需要做更新
				map.put("code", "S01");
				return map;
			}
		} else {
			SysAppVersion newUpgrade = newUpgradeVersion.get(0);
			SysAppVersion lastUpgradeVersion = appUpgradeVersion.get(0);
			if (appVersionList.size() == 0) {
				// 需要做更新
				map.put("code", "S02");
				map.put("data", newUpgrade);
				return map;
			}

			SysAppVersion newAppVersion = appVersionList.get(0);
			if (newAppVersion.getAppversion() < lastUpgradeVersion
					.getAppversion()) {
				// 需要强制更新
				map.put("code", "S03");
				map.put("data", newUpgrade);
				return map;
			} else if (newAppVersion.getAppversion() < newUpgrade
					.getAppversion()) {
				// 需要做更新
				map.put("code", "S02");
				map.put("data", newUpgrade);
				return map;
			} else {
				// 不需要做更新
				map.put("code", "S01");
				return map;
			}
		}
	}

	public Map<String, Object> checkLastVersion(String appCode,
			Integer appVersion) {
		Map<String, Object> map = new HashMap<>();
		SysAppVersionExample example = new SysAppVersionExample();
		SysAppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andAppcodeEqualTo(appCode);
		criteria.andAppversionGreaterThan(appVersion);
		List<SysAppVersion> appVersionList = sysAppVersionMapper
				.selectByExample(example);
		if (appVersionList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			boolean force = false;
			for (SysAppVersion version : appVersionList) {
				if (version.getType().equals("2")) {
					force = true;
					break;
				}
			}
			data.put("force", force);
			data.put("url",ecard_web_url
					+ appVersionList.get(appVersionList.size() - 1).getAppurl());
			data.put("descr", appVersionList.get(appVersionList.size() - 1)
					.getDes());
			map.put("msg", "软件有更新");
			map.put("data", data);
		} else {
			map.put("msg", "当前已经是最新版本");
		}

		map.put("code", "0");

		return map;
	}
}

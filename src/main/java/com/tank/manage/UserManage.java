package com.tank.manage;

import com.tank.mapper.ex.UserExMapper;
import com.tank.model.User;
import com.tank.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserManage extends BaseManage {

    @Autowired
    UserExMapper userExMapper;



	public List<User> list(Integer pageNumber, Integer pageSize) {
        UserExample example=new UserExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return userExMapper.selectByExample(example);
	}

	public int count() {
		return userExMapper.countByExample(null);
	}


    public User getUserById(Long id) {
        return userExMapper.selectByPrimaryKey(id);
    }



    	/**
	 * 根据电话得到Account
	 *
	 * @param mobile
	 * @return
	 */
	public User getUserByMobile(String mobile) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		criteria.andFlagEqualTo((byte) 1);
		List<User> accounts =  userExMapper.selectByExample(example);
		if (accounts.size() > 0) {
			return accounts.get(0);
		} else {
			return null;
		}
	}

    	/**
	 * 修改用户信息
	 *
	 * @return
	 */
	public Boolean updateUser(User Account) {
		return userExMapper.updateByPrimaryKeySelective(Account)>0;
	}


    	/**
	 * 修改用户真实姓名
	 *
	 * @param uid
	 *            uid
	 * @param name
	 *            名字
	 * @return
	 */
	public String modifyName(Long uid, String name) {
        User user = userExMapper.selectByPrimaryKey(uid);
		if (user == null) {
			return "S01";
		}
        User newUser = new User();
		newUser.setId(user.getId());
		newUser.setNickname(name);
		int temp = userExMapper.updateByPrimaryKeySelective(newUser);
		if (temp != 1) {
			return "S02";
		}
		return "S200";
	}

	/**
	 * 修改生日
	 *
	 * @param uid
	 *            用户id
	 * @param birthday
	 *            生日
	 * @return
	 */
	public String modifyBirthday(Long uid, Date birthday) {
		User user = userExMapper.selectByPrimaryKey(uid);
		if (user == null) {
			return "S01";
		}
        User newUser = new User();
		newUser.setId(user.getId());
		newUser.setBirthdate(birthday);
		int temp = userExMapper.updateByPrimaryKeySelective(newUser);
		if (temp != 1) {
			return "S02";
		}
		return "S200";
	}


    	/**
	 * 修改性别
	 *
	 * @param uid
	 *            用户id
	 * @param sexcode
	 *            性别编码 1男 2
	 * @return
	 */
	public String modifySex(Long uid, Byte sexcode) {
		User user = userExMapper.selectByPrimaryKey(uid);
		if (user == null) {
			return "S01";
		}
        User newUser = new User();
		newUser.setId(user.getId());
		newUser.setSexcode(sexcode);
		int temp = userExMapper.updateByPrimaryKeySelective(newUser);
		if (temp != 1) {
			return "S02";
		}
		return "S200";
	}

	/**
	 * 验证用户手机号是否正确
	 *
	 * @param uid
	 *            用户id
	 * @param phoneNum
	 *            用户手机号
	 * @return
	 */
	public Boolean verifyPhoneNum(Long uid, String phoneNum) {
		if (phoneNum == null || uid == null) {
			return false;
		}
        User user = userExMapper.selectByPrimaryKey(uid);
		if (user == null) {
			return false;
		}
		if (phoneNum.trim().equals(user.getMobile())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改用户手机号码
	 *
	 * @param phoneNum
	 *            手机号码
	 * @param accountId
	 *            账户id
	 * @return S01 手机号码已经存在 S02账户不存 S03修改失败 S200 修改成功
	 */
	public String modifyMobile(String phoneNum, Long accountId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(phoneNum.trim());
		List<User> list = userExMapper.selectByExample(example);
		if (list.size() > 0) {
			return "S01";
		}

        User account = userExMapper.selectByPrimaryKey(accountId);
		if (account == null) {
			return "S02";
		}
        User accountTemp = new User();
		accountTemp.setId(account.getId());
		accountTemp.setMobile(phoneNum);
		int temp = userExMapper.updateByPrimaryKeySelective(accountTemp);
		if (temp != 1) {
			return "S03";
		}
		return "S200";
	}

}

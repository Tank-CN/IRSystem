//package com.tank.manage;
//
//import com.bs.util.CommonUtils;
//import com.bs.util.common.IdcardInfoExtractor;
//import org.apache.shiro.authc.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//
//@Component
//@Transactional(readOnly = true)
//public class AccountManage extends BaseManage {
//
//	@Autowired
//	AccountExMapper accountExMapper;
//
//	@Autowired
//	BasAccountFamilyManage accountFamilyManage;
//
//	@Autowired
//	SysAccountOrgExMapper accountOrgMapper;
//
//	@Autowired
//	MsgRecordManage msgRecordManage;
//
//	public List<AdminAccount> getAdminList(Integer pageNumber, Integer pageSize) {
//		return accountExMapper.getAdminList(getOffset(pageNumber, pageSize), pageSize);
//	}
//
//	public int countAdminList() {
//		return accountExMapper.countAdminList();
//	}
//
//	/**
//	 * 生成医生账号
//	 *
//	 * @param username
//	 * @param password
//	 * @return
//	 */
//	public long createDocAccount(String username, String password, String header, String name) {
//		String token = UUID.randomUUID().toString();
//		token = token.replace("-", "");
//		Account account = new Account();
//		account.setUtype((byte) 2);
//		account.setUsername(username);
//		account.setPassword(MD5.getMD5(password));
//		account.setToken(token);
//		account.setFlag(Byte.valueOf("1"));
//		account.setRegdate(new Date());
//		account.setLastlogdate(new Date());
//		account.setRegtype(Short.valueOf("5"));
//		account.setRealname(name);
//		if (!CommonUtils.isNull(header)) {
//			account.setHeader(header);
//		}
//		if (accountExMapper.insertBackId(account) > 0) {
//			return account.getId();
//		}
//		return 0;
//	}
//
//	/**
//	 * 根据id获取用户
//	 *
//	 * @param id
//	 * @return
//	 */
//	public Account getAccountById(Long id) {
//		return accountExMapper.selectByPrimaryKey(id);
//	}
//
//	/**
//	 * 根据用户名得到Account
//	 *
//	 * @param username
//	 * @return
//	 */
//	public Account getAccountByUsername(String username) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andUsernameEqualTo(username);
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> accounts = (List<Account>) accountExMapper.selectByExample(example);
//		if (accounts.size() > 0) {
//			return accounts.get(0);
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 根据电话得到Account
//	 *
//	 * @param mobile
//	 * @param utype
//	 * @return
//	 */
//	public Account getAccountByMobile(String mobile, String utype) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andMobileEqualTo(mobile);
//		criteria.andUtypeEqualTo(Byte.valueOf(utype));
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> accounts = (List<Account>) accountExMapper.selectByExample(example);
//		if (accounts.size() > 0) {
//			return accounts.get(0);
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 删除用户
//	 *
//	 * @param id
//	 * @return
//	 */
//	public int delete(Long id) {
//		Account adminAccount = new Account();
//		adminAccount.setFlag(Byte.valueOf("0"));
//		adminAccount.setId(id);
//		int i = accountExMapper.updateByPrimaryKeySelective(adminAccount);
//		if (i > 0) {
//			delAcountOrg(id);
//			return 0;
//		}
//		return 0;
//	}
//
//	public void delAcountOrg(Long uid) {
//		SysAccountOrgExample example = new SysAccountOrgExample();
//		SysAccountOrgExample.Criteria criteria = example.createCriteria();
//		criteria.andUidEqualTo(uid);
//		accountOrgMapper.deleteByExample(example);
//	}
//
//	/**
//	 * 保存后台用户
//	 *
//	 * @param account
//	 * @return
//	 */
//	public Account save(Account account, Long[] orgs) {
//		long i = accountExMapper.insertBackIdSelective(account);
//		if (i > 0 && null != orgs) {
//			for (int l = 0; l < orgs.length; l++) {
//				SysAccountOrg ao = new SysAccountOrg();
//				ao.setOid(orgs[l]);
//				ao.setUid(account.getId());
//				accountOrgMapper.insert(ao);
//			}
//			return account;
//		}
//		return null;
//	}
//
//	/**
//	 * 创建新账户[api]
//	 *
//	 * @param userName
//	 * @param password
//	 * @return
//	 */
//	public Account createAccount(String utype, String mobile, String password, String ip) {
//		String token = UUID.randomUUID().toString();
//		token = token.replace("-", "");
//		Account account = new Account();
//		account.setUtype(Byte.valueOf(utype));
//		account.setPassword(MD5.getMD5(password));
//		account.setToken(token);
//		account.setMobile(mobile);
//		account.setFlag(Byte.valueOf("1"));
//		account.setRegdate(new Date());
//		account.setLastlogdate(new Date());
//		account.setRegip(ip);
//		account.setLastlogip(ip);
//		account.setRegtype(Short.valueOf("1"));
//		long temp = accountExMapper.insertBackId(account);
//		if (temp == 1) {
//			// fuck
//			// 判断家庭关系表中是否有相同的,并设置UID
//			// List<BasAccountFamily> flist = accountFamilyManage
//			// .getFamilyByIdcardMobile(account.getIdcard(),
//			// account.getMobile());
//			// if (null != flist && flist.size() > 0) {
//			//
//			// }
//
//			// **设置通知
//			// noticeService.initUsrNotice(Long.parseLong(uid.toString()));
//			// 存入消息
//			// 注册完成完善信息
//			MsgRecord recordRegister = new MsgRecord();
//			recordRegister.setUid(account.getId());
//			recordRegister.setStatus((byte) 0);// 未查看
//			recordRegister.setEvent((byte) 1);
//			recordRegister.setMsgtype((byte) 3);
//			recordRegister.setDiccode(Constants.MSG_TYPE_0103);
//			recordRegister.setCreatedate(new Date());
//			recordRegister.setContent("您已经注册完成，请到【我的】完善个人信息，方便就医功能的使用!");
//			recordRegister.setComplete((byte) 0);
//			recordRegister.setEventstep((byte) 1);
//			// 上传头像
//			MsgRecord recordHead = new MsgRecord();
//			recordHead.setUid(account.getId());
//			recordHead.setStatus((byte) 0);// 未查看
//			recordHead.setEvent((byte) 0);
//			recordHead.setMsgtype((byte) 2);
//			recordHead.setDiccode(Constants.MSG_TYPE_0102);
//			recordHead.setCreatedate(new Date());
//			recordHead.setContent("请上传个人头像，方便朋友认出你!");
//			recordHead.setComplete((byte) 0);
//			recordHead.setEventstep((byte) 1);
//			// 上传档案
//			MsgRecord recordEhr = new MsgRecord();
//			recordEhr.setUid(account.getId());
//			recordEhr.setStatus((byte) 0);// 未查看
//			recordEhr.setEvent((byte) 0);
//			recordEhr.setMsgtype((byte) 2);
//			recordEhr.setDiccode(Constants.MSG_TYPE_0104);
//			recordEhr.setCreatedate(new Date());
//			recordEhr.setContent("上传病历数据，掌上管理健康数据!");
//			recordEhr.setComplete((byte) 0);
//			recordEhr.setEventstep((byte) 1);
//
//			List<MsgRecord> msgList = new ArrayList<MsgRecord>();
//			msgList.add(recordRegister);
//			msgList.add(recordHead);
//			msgList.add(recordEhr);
//			msgRecordManage.inserBatch(msgList);
//			return account;
//		}
//		return null;
//	}
//
//	/**
//	 * 根据类型获得列表
//	 *
//	 * @param value
//	 * @param pageNumber
//	 * @param pageSize
//	 * @return
//	 */
//	public List<Account> listByUType(Byte value, Integer pageNumber, Integer pageSize) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andUtypeEqualTo(value);
//		criteria.andFlagEqualTo(Byte.valueOf("1"));
//		example.setOrderByClause(getPage("id desc", pageNumber, pageSize));
//		return accountExMapper.selectByExample(example);
//	}
//
//	/**
//	 * 根据类型获得总条数
//	 *
//	 * @param value
//	 * @return
//	 */
//	public int countByUType(Byte value) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andUtypeEqualTo(value);
//		criteria.andFlagEqualTo(Byte.valueOf("1"));
//		return accountExMapper.countByExample(example);
//	}
//
//	/**
//	 * 修改用户信息
//	 *
//	 * @param Account
//	 * @return
//	 */
//	public Integer updateAccount(Account Account) {
//		return accountExMapper.updateByPrimaryKeySelective(Account);
//	}
//
//	public Account updateAccount(Account account, Long[] orgs) {
//		int i = accountExMapper.updateByPrimaryKeySelective(account);
//		if (i > 0) {
//			delAcountOrg(account.getId());
//			for (int l = 0; l < orgs.length; l++) {
//				SysAccountOrg ao = new SysAccountOrg();
//				ao.setOid(orgs[l]);
//				ao.setUid(account.getId());
//				accountOrgMapper.insert(ao);
//			}
//			return account;
//		}
//		return null;
//	}
//
//	/**
//	 * 是否有相同的用户名 有返回false
//	 *
//	 * @param userName
//	 * @return
//	 */
//	public Boolean haveSameUsername(String userName) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andUsernameEqualTo(userName);
//		criteria.andFlagEqualTo((byte) 1);
//		criteria.andUtypeEqualTo((byte) 0);
//		List<Account> accounts = accountExMapper.selectByExample(example);
//		if (accounts.size() == 0) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 该手机号码对应的用户是否绑定身份证信息
//	 *
//	 * @param mobile
//	 * @return
//	 */
//	public Boolean isHaveCard(String mobile, String utype) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andMobileEqualTo(mobile);
//		criteria.andUtypeEqualTo(Byte.valueOf(utype));
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (null == list || list.size() == 0) {
//			return false;
//		}
//		Account account = list.get(0);
//		return !CommonUtils.isNull(account.getIdcard());
//	}
//
//	/**
//	 * 通过电话号码验证证件号码
//	 *
//	 * @param phoneNum
//	 * @param cardNum
//	 * @return
//	 */
//	public Boolean checkCardNumByPhone(String mobile, String idcard, String utype) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andMobileEqualTo(mobile);
//		criteria.andIdcardEqualTo(idcard);
//		criteria.andUtypeEqualTo(Byte.valueOf(utype));
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (null != list && list.size() > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 通过电话号码和证件号码
//	 *
//	 * @param phoneNum
//	 * @param cardNum
//	 * @return
//	 */
//	public Account checkByCardNumPhone(String mobile, String idcard) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andMobileEqualTo(mobile);
//		criteria.andIdcardEqualTo(idcard);
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (null != list && list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 修改用户真实姓名
//	 *
//	 * @param uid
//	 *            uid
//	 * @param name
//	 *            名字
//	 * @return
//	 */
//	public String modifyName(Long uid, String name) {
//		Account user = accountExMapper.selectByPrimaryKey(uid);
//		if (user == null) {
//			return "S01";
//		}
//		Account newUser = new Account();
//		newUser.setId(user.getId());
//		newUser.setRealname(name);
//		int temp = accountExMapper.updateByPrimaryKeySelective(newUser);
//		if (temp != 1) {
//			return "S02";
//		}
//		return "S200";
//	}
//
//	/**
//	 * 修改生日
//	 *
//	 * @param uid
//	 *            用户id
//	 * @param birthday
//	 *            生日
//	 * @return
//	 */
//	public String modifyBirthday(Long uid, Date birthday) {
//		Account user = accountExMapper.selectByPrimaryKey(uid);
//		if (user == null) {
//			return "S01";
//		}
//		Account newUser = new Account();
//		newUser.setId(user.getId());
//		newUser.setBirthdate(birthday);
//		int temp = accountExMapper.updateByPrimaryKeySelective(newUser);
//		if (temp != 1) {
//			return "S02";
//		}
//		return "S200";
//	}
//
//	/**
//	 * 判断是否存在同一个账户。 依据,同一个证件类别是否存在相同的证件号码
//	 *
//	 * @param cardtype
//	 *            证件类别
//	 * @param idcard
//	 *            证件号码
//	 * @return
//	 */
//	public Boolean isExistsSameUser(Byte cardtype, String idcard) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andCardtypeEqualTo(cardtype);
//		criteria.andIdcardEqualTo(idcard);
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (list.size() > 0) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 判断是否有相同的身份证号
//	 *
//	 * @param idcard
//	 * @return
//	 */
//	public Boolean isExistsSameUser(String idcard) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andIdcardEqualTo(idcard);
//		criteria.andFlagEqualTo((byte) 1);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (list.size() > 0) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 修改身份证号码
//	 *
//	 * @param uid
//	 *            用户id
//	 * @param cardNum
//	 *            身份证号码
//	 * @return
//	 */
//	public String modifyCardNum(Long uid, String cardNum) {
//		Account user = accountExMapper.selectByPrimaryKey(uid);
//		if (user == null) {
//			return "S01";
//		}
//		IdcardInfoExtractor ie = new IdcardInfoExtractor(cardNum);
//
//		Account newUser = new Account();
//		newUser.setId(user.getId());
//		newUser.setIdcard(cardNum);
//		newUser.setCardtype((byte) 1);
//
//		if ("男".equals(ie.getGender())) {
//			newUser.setSexcode(Byte.valueOf("1"));
//		} else if ("女".equals(ie.getGender())) {
//			newUser.setSexcode(Byte.valueOf("2"));
//		}
//		if (ie.getBirthday() != null) {
//			newUser.setBirthdate(ie.getBirthday());
//		}
//		int temp = accountExMapper.updateByPrimaryKeySelective(newUser);
//		if (temp != 1) {
//			return "S02";
//		}
//		return "S200";
//	}
//
//	/**
//	 * 修改性别
//	 *
//	 * @param uid
//	 *            用户id
//	 * @param sexcode
//	 *            性别编码 1男 2
//	 * @return
//	 */
//	public String modifySex(Long uid, Byte sexcode) {
//		Account user = accountExMapper.selectByPrimaryKey(uid);
//		if (user == null) {
//			return "S01";
//		}
//		Account newUser = new Account();
//		newUser.setId(user.getId());
//		newUser.setSexcode(sexcode);
//		int temp = accountExMapper.updateByPrimaryKeySelective(newUser);
//		if (temp != 1) {
//			return "S02";
//		}
//		return "S200";
//	}
//
//	/**
//	 * 验证用户手机号是否正确
//	 *
//	 * @param uid
//	 *            用户id
//	 * @param phoneNum
//	 *            用户手机号
//	 * @return
//	 */
//	public Boolean verifyPhoneNum(Long uid, String phoneNum) {
//		if (phoneNum == null || uid == null) {
//			return false;
//		}
//		Account user = accountExMapper.selectByPrimaryKey(uid);
//		if (user == null) {
//			return false;
//		}
//		if (phoneNum.trim().equals(user.getMobile())) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 修改用户手机号码
//	 *
//	 * @param phoneNum
//	 *            手机号码
//	 * @param accountId
//	 *            账户id
//	 * @return S01 手机号码已经存在 S02账户不存 S03修改失败 S200 修改成功
//	 */
//	public String modifyMobile(String phoneNum, Long accountId, byte utype) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andMobileEqualTo(phoneNum.trim());
//		criteria.andUtypeEqualTo(utype);
//		List<Account> list = accountExMapper.selectByExample(example);
//		if (list.size() > 0) {
//			return "S01";
//		}
//
//		Account account = accountExMapper.selectByPrimaryKey(accountId);
//		if (account == null) {
//			return "S02";
//		}
//		Account accountTemp = new Account();
//		accountTemp.setId(account.getId());
//		accountTemp.setMobile(phoneNum);
//		int temp = accountExMapper.updateByPrimaryKeySelective(accountTemp);
//		if (temp != 1) {
//			return "S03";
//		}
//		return "S200";
//	}
//
//	public List<Account> selectAccountsInUids(List<Long> uids) {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andIdIn(uids);
//		criteria.andFlagEqualTo((byte) 1);
//		return accountExMapper.selectByExample(example);
//	}
//
//	public List<DoctorAccount> getDoctorAccounts(Integer pageNumber, Integer pageSize) {
//		return accountExMapper.getDoctorAccounts(getOffset(pageNumber, pageSize), pageSize);
//	}
//
//	public int countDoctorAccounts() {
//		AccountExample example = new AccountExample();
//		AccountExample.Criteria criteria = example.createCriteria();
//		criteria.andUtypeEqualTo((byte) 2);
//		return accountExMapper.countByExample(example);
//	}
//
//	public Long insertAndBackId(Account account){
//		accountExMapper.insertBackIdSelective(account);
//		return account.getId();
//	}
//}

/**    
 * 文件名：UserServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2016年7月6日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.email.MailSend;
import com.ifreework.common.email.entity.MailBean;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.SystemConfigManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.common.shiro.realm.ShiroAuthInterface;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.Resource;
import com.ifreework.entity.system.User;
import com.ifreework.mapper.system.ResourceMapper;
import com.ifreework.mapper.system.UserMapper;
import com.ifreework.util.FileUtil;
import com.ifreework.util.HttpRequestUtil;
import com.ifreework.util.ImageUtil;
import com.ifreework.util.SecurityUtil;
import com.ifreework.util.StringUtil;


/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月6日 下午4:30:28
 * @修改人：wangyh
 * @修改时间：2016年7月6日 下午4:30:28
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService, ShiroAuthInterface {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private ResourceMapper resourceMapper;

	/**
	 * 
	 * @Title: getUserInfoByUserName @Description:
	 *         TODO(通过用户名获取用户信息) @param @return @throws
	 */
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	public User getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	/**
	 * 
	 * 描述：验证PK值是否已经存在
	 * @Title: validatePK
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public PageData validateUserName(PageData pd) {
		User user = getUserByUserName(pd.getString("username"));
		pd.put("valid", user == null || user.getUserId().equals(pd.getString("userId")));
		return pd;
	}

	/**
	 * 
	 * 描述：通过用户名密码验证用户登录是否成功
	 * @Title: validateUserByNameAndPwd
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData validateUserByNameAndPwd(PageData pd) {
		// TODO Auto-generated method stub
		String username = pd.getString("username");
		String pwd = pd.getString("password");
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(pwd)) {
			pd.setResult(Constant.FAILED);
			pd.setMsg("用户名或者密码错误，请重新输入。");
			return pd;
		}

		// shiro加入身份验证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, SecurityUtil.encrypt(pwd));
		try {
			subject.login(token);
			loginLogService.add(username);
			pd.setResult(Constant.SUCCESS);
		} catch (LockedAccountException e) {
			pd.setResult(Constant.FAILED);
			pd.setMsg("用户暂未启用，请与管理员联系。");
		} catch (UnknownAccountException e) {
			pd.setResult(Constant.FAILED);
			pd.setMsg("用户名或者密码错误，请重新输入。");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			pd.setResult(Constant.FAILED);
			pd.setMsg("系统异常，登录失败，请与管理员联系。");
		}
		return pd;
	}

	/**
	 * 文件上传后图片保存位置
	 * 
	 * @param file
	 * @return
	 */
	public PageData userImgUpload(MultipartFile file, double width, double height, double sw, double sh, double sx,
			double sy) {
		PageData pageData = new PageData();
		String rootPath = "";
		String imgPath = "";
		String localPath = "";
		try {
			Map<String, Integer> map = ImageUtil.getImgXY(file.getInputStream());
			int oW = map.get("width"), oH = map.get("height");
			sw = sw * oW / width;
			sh = sh * oH / height;
			sx = sx * oW / width;
			sy = sy * oH / height;

			User user = UserManager.getUser();
			imgPath += "/" + user.getUsername();
			rootPath = FileUtil.getRootPath() + "temp";

			localPath = ImageUtil.cutImage(file.getInputStream(), rootPath + imgPath, file.getOriginalFilename(),
					(int) sx, (int) sy, (int) sw, (int) sh);
			ImageUtil.changeImge(localPath, localPath + ".offLine");
			FileUtil.fileUpload(localPath + ".offLine", imgPath);
			imgPath = FileUtil.fileUpload(localPath, imgPath);
			user.setImgPath(imgPath);
			update(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pageData.put("fileName", imgPath);
		return pageData;
	}

	/**
	 * 描述：根据用户id，重置用户密码 @Title: resetPwd @param @return @throws
	 */
	public PageData resetPwd(PageData pd) {
		final String userId = pd.getString("userId");
		if (StringUtil.isEmpty(userId)) {
			pd.setResult(Constant.FAILED);
			return pd;
		}
		String resetPwd = SystemConfigManager.get(Config.RESET_PWD);
		resetPwd = StringUtil.isEmpty(resetPwd) ? SecurityUtil.encrypt("1") : SecurityUtil.encrypt(resetPwd);
		User user = new User();
		user.setUserId(userId);
		user.setPassword(resetPwd);
		pd = update(user);
		if (Constant.SUCCESS.equals(pd.getResult())) {
			// 开辟新线程发送密码重置邮件，避免出现邮件发送错误导致修改数据误报
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					User user = UserManager.getUser(userId);
					MailBean mailBean = new MailBean(user.getEmail(), "密码重置通知", "您的密码已经重置成功！");
					MailSend h = new MailSend(mailBean);
					h.sendMail();
				}
			}).start();
		}
		return pd;
	}

	/**
	 * 
	 * 描述：重置密码
	 * @Title: reset
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData changePwdSave(PageData pd) {
		String oldPassword = pd.getString("oldPassWord");
		String password = pd.getString("password");
		User user = UserManager.getUser();
		oldPassword = SecurityUtil.encrypt(oldPassword);

		pd = new PageData();

		if (oldPassword.equals(user.getPassword())) {
			User u = new User();
			u.setPassword(SecurityUtil.encrypt(password));
			u.setUserId(user.getUserId());
			update(u);
			pd.setResult(Constant.SUCCESS);
		} else {
			pd.setResult(Constant.FAILED);
			pd.setMsg("旧密码输入错误，请重新输入");
		}
		return pd;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public PageData update(User user) {
		PageData pd = new PageData();
		userMapper.update(user);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 新增用户信息
	 */
	public PageData add(User user) {
		PageData pd = new PageData();
		String userId = StringUtil.uuid();
		user.setUserId(userId);
		userMapper.add(user);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 
	 * @Title: queryContacts @Description:
	 *         TODO(获取所用联系人和在线联系人信息) @param @return @throws
	 */
	public Map<String, Object> queryContacts(PageData pd) {
		Map<String, Object> map = new HashMap<String, Object>();
		// pd.put("username", Jurisdiction.getUser().getUsername());
		// List<User> list = queryUserList(pd);
		// int onLineNum = 0;
		// for (User user : list) {
		// if (Constant.WEBSOCKET_USER_MAP.containsKey(user.getUsername())) {
		// user.setIsOnline("1");
		// onLineNum++;
		// }
		// }
		// Collections.sort(list, new Comparator<User>() {
		// /*
		// * int compare(Student o1, Student o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
		// * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
		// */
		// public int compare(User o1, User o2) {
		// int t = o2.getIsOnline().compareTo(o1.getIsOnline());
		// if (t == 0) {
		// return o2.getPersonName().compareTo(o1.getPersonName());
		// }
		// return t;
		// }
		// });
		// map.put("allContacts", list);// 所有联系人信息
		// map.put("onLineNum", onLineNum);// 在线联系人数量
		// map.put("allNum", list.size());// 全部联系人数量
		return map;

	}

	@Override
	public List<User> queryUserList(PageData pd) {
		return userMapper.queryUserList(pd);
	}

	/**
	 * 
	 * 描述：查询当前用户具有的所有权限
	 * @Title: queryAuthorityByUserId
	 * @param 
	 * 			userId 
	 * @return   
	 * @throws
	 */
	@Override
	public Set<String> queryAuthorityByUserName(String userName) {
		User user = UserManager.getUser();
		List<String> roleList = new ArrayList<String>();
		queryRoleTreeByUserId(user.getUserId(), null, roleList);
		return userMapper.queryAuthorityByUserName(roleList);
	}

	/**
	 * 
	 * 描述：根据当前请求路径，获取该请求所需的权限
	 * @Title: queryAuthorityByUrl
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<String> queryAuthorityByResourceId(String resourceId) {
		return userMapper.queryAuthorityByResourceId(resourceId);
	}

	/**
	 * 
	 * 描述：验证用户名密码是否正确
	 * @Title: queryAuthorityByUrl
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public User login(String username, String password) {
		User user = getUserByUserName(username);

		if (user != null) { // 验证当前用户是否存在
			if (password.equals(user.getPassword())) { // 验证用户密码是否正确
				if ("1".equals(user.getStatus())) { // 验证用户是否被启用
					return user;
				}
				throw new LockedAccountException();
			}
		}
		throw new UnknownAccountException();
	}

	/**
	 * 
	 * 描述：
	 * @Title: getResource
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public Resource getResource(String res, String flag) {

		if ("M".equals(flag) || "A".equals(flag)) {
			return resourceMapper.getResourceByUrl(res);
		}
		return null;
	}

	/**
	 * 
	 * 描述：通过用户名查询用户所拥有的菜单
	 * @Title: queryMenuByUserId
	 * @param 
	 * @return   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public List queryMenuByUserId() {
		User user = UserManager.getUser();
		List<String> roleList = new ArrayList<String>();
		queryRoleTreeByUserId(user.getUserId(), null, roleList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resourceId", "0");
		queryMenuByUserId(map, roleList);
		return (List) map.get("children");
	}

	/**
	 * 描述：递归获取用户所拥有的菜单
	 * @param map 
	 * @return
	 */
	private void queryMenuByUserId(Map<String, Object> map, List<String> roleList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", map.get("resourceId"));
		param.put("roleList", roleList);

		List<Map<String, Object>> list = userMapper.queryMenuByUserId(param);
		map.put("children", list);
		for (Map<String, Object> m : list) {
			queryMenuByUserId(m, roleList);
		}
	}

	/**
	 * 
	 * 描述： 递归查询当前用户所拥有的角色及下级角色
	 * @param userId
	 * @param parentId
	 * @param roleIdList 
	 * @return
	 */
	private void queryRoleTreeByUserId(String userId, String parentId, List<String> roleIdList) {
		PageData pd = new PageData();
		pd.put("userId", userId);
		pd.put("parentId", parentId);
		List<String> roleList = userMapper.queryRoleTreeByUserId(pd);
		for (String roleId : roleList) {
			if (!roleIdList.contains(roleId)) {
				roleIdList.add(roleId);
				queryRoleTreeByUserId(null, roleId, roleIdList);
			}
		}
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	private JSONObject getOauth2AccessToken(String appId,
			String appSecret, String code) {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		String resultStr = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);
		JSONObject json = JSON.parseObject(resultStr);
		return json;
	}

	
	/**
	 * 
	 * 描述：通过用户微信授权code获取用户微信信息
	 * @param code 用户授权code
	 * @return 
	 * @return
	 */
	public User getUserByCode(String code){
		String appid = Config.init().get(Config.WEIXIN_APPID);
		String appSecret = Config.init().get(Config.WEIXIN_APPSECRET);

		JSONObject json = getOauth2AccessToken(appid, appSecret, code);
		
		String userId = json.getString("openid");
		
		User user = getUserById(userId);
		
		if(user == null ){ //如果当前用户id在数据库中不存在，则在微信获取用户ID，并进行注册
			String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";
			
			
			String access_token = json.getString("access_token");
			requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
			requestUrl = requestUrl.replace("OPENID", userId);
			String resultStr = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);
			json = JSON.parseObject(resultStr);
			
			user = new User();
			user.setUserId(json.getString("openid"));
			user.setPersonName(json.getString("nickname"));
			user.setSex(json.getString("sex"));
			
//			user.setProvinceId(json.getString("province"));
//			user.setMunicipalityId(json.getString("city"));
//			user.setCountyId(json.getString("country"));
//			
			user.setImgPath(json.getString("headimgurl"));
			user.setPrivilege(json.getString("privilege"));
			user.setUnionid(json.getString("unionid"));
			userMapper.add(user);
		}
		return user;
	}
	
}

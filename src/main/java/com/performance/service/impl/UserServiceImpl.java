package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Person;
import com.performance.pagemodel.Right;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.pagemodel.UserCate;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.RoleServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.Encrypt;
import com.performance.util.ValidateUtil;
import com.performances.model.TLw;
import com.performances.model.TPerson;
import com.performances.model.TRight;
import com.performances.model.TRole;
import com.performances.model.TUser;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserServiceI {

	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	private BaseDaoI<TUser> userDao;
	private BaseDaoI<TPerson> personDao;
	private RoleServiceI roleService;
	private ImportExcelServiceI<TUser> _importExcel;
	public ImportExcelServiceI<TUser> get_importExcel() {
	return _importExcel;
	}

	private static final  String tableName = "t_user";
	private String hsql;
	private Map<String, Object> params;

	@Autowired
	public void set_importExcel(ImportExcelServiceI<TUser> _importExcel) {
	this._importExcel = _importExcel;
	}

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public BaseDaoI<TUser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<TUser> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User save(User user) {
		TUser t = new TUser();
		BeanUtils.copyProperties(user, t);
		// t.setUserid(UUID.randomUUID().toString());
		// t.setUserid(userid);
		// t.(new Date());
		// t.setName(user.getName());
		// t.setPwd(Encrypt.e(user.getPwd()));
		String[] userRole = user.getComment().split(",");
		
		  List<TRole> roleList=roleService.findRoleInRange(userRole);
		  t.getTRoles().clear();
		  for(TRole trole:roleList){
		  
		  t.getTRoles().add(trole);
		  
		  }
		t.setPwd(Encrypt.md5("123456"));
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		userDao.save(t);
		
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public List<TUser> findUsersByHQL(String domainStr) {
		String[] objs = { domainStr.trim() };
		String hql = "from TUser t where t.domain = ?";
		return userDao.findEntityByHQL(hql, objs);
	}
	
	@Override
	public User userCheck(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		String uc=user.getIds();
		params.put("name", uc);
		TUser t = userDao.get(
				"from TUser t where t.usercode = :name", params);
		if(t!=null){
		BeanUtils.copyProperties(t, user);
		return user;
		}else{
		return null;
			
		}
	}

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pwd", Encrypt.e(user.getPwd()));
		params.put("name", user.getName());
		TUser t = userDao.get(
				"from Tuser t where t.name = :name and t.pwd = :pwd", params);
		if (t != null) {
			return user;
		}
		return null;
	}

	@Override
	public DataGrid datagrid(User user) {
		DataGrid dg = new DataGrid();
		String hql = "from TUser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(user, hql);
//		/**
//		 * 保存导出时会使用到3个参数
//		 */
//		ActionContext actionContext = ActionContext.getContext();
//        Map session = actionContext.getSession();
//        if(session.containsKey("QUERY_HSQL")){
//       	 session.remove("QUERY_HSQL");
//       }
//       if(session.containsKey("QUERY_TABLE")){
//       	session.remove("QUERY_TABLE");
//       }
//       if(session.containsKey("QUERY_PARAMS")){
//       	session.remove("QUERY_PARAMS");
//       }
//        session.put("QUERY_TABLE", "t_user");
//        session.put("QUERY_HSQL", hql);
//        session.put("QUERY_PARAMS", params);

		this.hsql = hql;
		this.params = params;
        
		List<TUser> l = userDao.find(hql, params, user.getPage(),
				user.getRows());
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TUser> l, List<User> nl) {
		if (l != null && l.size() > 0) {
			for (TUser t : l) {
				User u = new User();
				Set<Role> roles = new HashSet<Role>();
				Set<TRole> tr = t.getTRoles();
				Iterator<TRole> itRole = tr.iterator();
				while (itRole.hasNext()) {
					TRole tRole = itRole.next();
					Role role = new Role();
					BeanUtils.copyProperties(tRole, role);
					roles.add(role);

				}
				BeanUtils.copyProperties(t, u);
				u.setRoles(roles);
				nl.add(u);
			}
		}
	}

	private String addOrder(User user, String hql) {
		if (user.getSort() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return hql;
	}

	private String addWhere(User user, String hql, Map<String, Object> params) {
//		if (user.getName() != null && !user.getName().trim().equals("")) {
//			hql += " where t.name like :name";
//			params.put("name", "%%" + user.getName().trim() + "%%");
//		}
		
		StringBuilder whereStr = new StringBuilder(hql);
		if ( (user.getName() != null && !user.getName().isEmpty()) && user.getUsercode() == null) {
			whereStr.append(" where  t.name like :name ");
			params.put("name", "%%" + user.getName().trim() + "%%");
		}
		
		if( (user.getName() != null && !user.getName().isEmpty()) &&
				(user.getUsercode() != null && !user.getUsercode().isEmpty()) ){
			whereStr.append(" where  t.name like :name or t.usercode like :usercode  ");
			params.put("name", "%%" + user.getName().trim() + "%%");
			params.put("usercode", "%%" + user.getUsercode().trim() + "%%");
		}else if(user.getName() == null&& user.getUsercode()!=null&&!user.getUsercode().isEmpty()){
			whereStr.append(" where  t.usercode like :usercode  ");
			//params.put("name", "%%" + user.getName().trim() + "%%");
			params.put("usercode", "%%" + user.getUsercode().trim() + "%%");
		}
		return whereStr.toString();
	}

	@Override
	public void remove(String ids) {
		// for (String id : ids.split(",")) {
		// Tuser u = userDao.get(Tuser.class, id);
		// if (u != null) {
		// userDao.delete(u);
		// }
		// }
		String[] nids = ids.split(",");
		for(int i=0;i<nids.length;i++){
			TUser tUser=userDao.get(TUser.class,Integer.valueOf(nids[i]));
			if(tUser!=null){
				try{
					
					userDao.delete(tUser);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
//		String hql = "delete TUser t where t.userid in (";
//		for (int i = 0; i < nids.length; i++) {
//			if (i > 0) {
//				hql += ",";
//			}
//			hql += "'" + nids[i] + "'";
//			
//			TUser tUser=userDao.get(TUser.class,Integer.valueOf(nids[i]));
//			if(tUser!=null){
//				try{
//					
//					userDao.delete(tUser);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
//		hql += ")";
		
	//	userDao.executeHql(hql);
	}

	@Override
	public void saver() {
		logger.info("hello");
	}

	/**
	 * 登录验证
	 */
	@Override
	public User findValidateUser(String usercode, String pwd)  throws Exception{
		User t = new User();
		String hql = "from TUser u where u.usercode = ? and u.pwd = ?";
		String[] objs = { usercode, pwd };

		List<TUser> list = userDao.findEntityByHQL(hql, objs);
		//TUser user = ValidateUtil.isValid(list) ? list.get(0) : null;
		if(ValidateUtil.isValid(list)){
			TUser user = list.get(0);
			BeanUtils.copyProperties(user, t);
			return t;
		}else {
			return null;
		}
	}

	@Override
	public List<User> ThesisByPoi(FileInputStream fis) {
		
	List<User> infos = new ArrayList<User>();
		
		try {
			if(fis!=null){
				//执行person同时执行user表中数据的插入和付权限
				/*PersonImportEvent<TPerson> importEvent = new PersonImportEvent<TPerson>();
				_importExcel.setEventProxy(importEvent);*/
				UserImportEvent<TUser> importEvent = new UserImportEvent<TUser>();
				_importExcel.setEventProxy(importEvent);
				_importExcel.ImportExcelToDB(tableName, fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infos;
		
	}
	public class UserImportEvent<T> implements ImportExcelEventI<T>{

		@Override
		public void AfterSaveModel(T model) {
//			User user=new User();
//			user.setComment("5");
//			TPerson person=(TPerson) model;
//			user.setUsercode(person.getUsercode());
//			user.setName(person.getName());
/*//			user.setDepartment(per);
			User user =new User();
			TUser tUser=(TUser)model;
			BeanUtils.copyProperties(tUser, user);
			user.setComment("5");
			user.setPwd(Encrypt.md5("123456"));
			save(user);*/
		}

		@Override
		public void AfterUpdateModel(T model) {

		}

		@Override
		public T BeforeSaveModel(T model) {
			TUser tUser=(TUser)model;
			
			tUser.setComment("5");
			tUser.setPwd(Encrypt.md5("123456"));
			String[] userRole = tUser.getComment().split(",");
			
			  List<TRole> roleList=roleService.findRoleInRange(userRole);
			  tUser.getTRoles().clear();
			  for(TRole trole:roleList){
			  
				  tUser.getTRoles().add(trole);
			  
			  }
			return (T) tUser;
		}
		
		@Override
		public boolean IsValidModel(T model) {
			return true;
		}
	}
	@Override
	public User edit(User user) {
		TUser t = userDao.get(TUser.class, user.getUserid());
		// BeanUtils.copyProperties(user,t, new String[] {"userid"});

		String[] userRole = user.getComment().split(",");
		List<TRole> roleList = roleService.findRoleInRange(userRole);
		Set<TRole> TRjs = new HashSet<TRole>();
		for (TRole trole : roleList) {

			// t.getTRoles().add(trole);
			TRjs.add(trole);

		}
		t.setTRoles(TRjs);

		userDao.saveOrUpdate(t);
		return user;
	}

	@Override
	public TUser findUser(String usercode) {
		String hql = "from TUser u where u.usercode = ?";
		String[] objs = { usercode };

		List<TUser> list = userDao.findEntityByHQL(hql, objs);
		TUser user = ValidateUtil.isValid(list) ? list.get(0) : null;

		return user;
	}
	
	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("User", tableName, hsql, params);
	}

	@Override
	public User findUserById(String userid) {
		String hql = "from TUser u where u.userid = ?";
		Integer [] objs=new Integer[1];
		int i=Integer.parseInt(userid);
		objs[0] = Integer.valueOf(i);
		List<TUser> list = userDao.findEntityByHQL(hql, objs);
		TUser tuser = ValidateUtil.isValid(list) ? list.get(0) : null;
		User user = new User();
		BeanUtils.copyProperties(tuser, user);
		return user;
	}
	
	
	@Override
	public TUser findTUserById(Integer userid) {
		String hql = "from TUser u where u.userid = ?";
		Integer [] objs=new Integer[1];
		objs[0] = userid;
		List<TUser> list = userDao.findEntityByHQL(hql, objs);
		TUser tuser = ValidateUtil.isValid(list) ? list.get(0) : null;
		return tuser;
	}

	@Override
	public TUser resetPasswd(TUser user) throws Exception {
			TUser tUser=userDao.get(TUser.class,user.getUserid());
			//将用户密码重置为123456
			tUser.setPwd(Encrypt.md5("123456"));
			userDao.saveOrUpdate(tUser);
			return user;
			
			
	}
	
	@Override
	public TUser changePasswd(Integer UserID, String password) throws Exception{
		TUser tUser=userDao.get(TUser.class, UserID);
		//将用户密码重置为123456
		tUser.setPwd(Encrypt.md5(password));
		userDao.saveOrUpdate(tUser);
		return tUser;
	}
}

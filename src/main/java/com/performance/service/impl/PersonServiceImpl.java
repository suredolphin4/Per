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
import com.performance.pagemodel.Person;
import com.performance.pagemodel.PubPart;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.PersonServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.Encrypt;
import com.performance.util.ValidateUtil;
import com.performances.model.TPerson;
import com.performances.model.TPubPart;
import com.performances.model.TRole;

@Transactional
@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl implements
		PersonServiceI {

	private static final Logger logger = Logger
			.getLogger(PersonServiceImpl.class);
	private ImportExcelServiceI<TPerson> _importExcel;

	private static final  String tableName = "t_person";
	private String hsql;
	private Map<String, Object> params;

	public ImportExcelServiceI<TPerson> get_importExcel() {
		return _importExcel;
	}
	@Autowired
	public void set_importExcel(ImportExcelServiceI<TPerson> _importExcel) {
		this._importExcel = _importExcel;
	}

	/*private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}*/

	private BaseDaoI<TPerson> PersonDao;

	public BaseDaoI<TPerson> getPersonDao() {
		return PersonDao;
	}

	@Autowired
	public void setPersonDao(BaseDaoI<TPerson> PersonDao) {
		this.PersonDao = PersonDao;
	}

	@Override
	public Person save(Person Person) {
		TPerson t = new TPerson();
		BeanUtils.copyProperties(Person, t, new String[] { "pwd" });
		// t.setPersonid(UUID.randomUUID().toString());
		// t.setPersonid(Personid);
		// t.(new Date());
		t.setName(Person.getName());
		// t.setPwd(Encrypt.e(Person.getPwd()));
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		PersonDao.save(t);
		BeanUtils.copyProperties(t, Person);
		return Person;
	}

	@Override
	public DataGrid datagrid(Person Person) {
		DataGrid dg = new DataGrid();
		String hql = "from TPerson t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(Person, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(Person, hql);
//		/**
//		 * 保存导出时会使用到3个参数
//		 */
//
//		ActionContext actionContext = ActionContext.getContext();
//        Map<String, Object> session = actionContext.getSession();
//        if(session.containsKey("QUERY_HSQL")){
//        	 session.remove("QUERY_HSQL");
//        }
//        if(session.containsKey("QUERY_TABLE")){
//        	session.remove("QUERY_TABLE");
//        }
//        if(session.containsKey("QUERY_PARAMS")){
//        	session.remove("QUERY_PARAMS");
//        }
//
//        if(!session.containsKey("QUERY_HSQL") && !session.containsKey("QUERY_TABLE") && !session.containsKey("QUERY_PARAMS") ){
//	        session.put("QUERY_TABLE", "t_person");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);
//        }else{
//        	session.remove("QUERY_PARAMS");
//        	session.remove("QUERY_TABLE");
//        	session.remove("QUERY_HSQL");
//        }

		this.hsql = hql;
		this.params = params;
        
		List<TPerson> l = PersonDao.find(hql, params, Person.getPage(),
				Person.getRows());
		List<Person> nl = new ArrayList<Person>();
		changeModel(l, nl);
		dg.setTotal(PersonDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TPerson> l, List<Person> nl) {
		if (l != null && l.size() > 0) {
			for (TPerson t : l) {
				Person u = new Person();

				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Person Person, String hql) {
		if (Person.getSort() != null) {
			hql += " order by " + Person.getSort() + " " + Person.getOrder();
		}
		return hql;
	}

	private String addWhere(Person Person, String hql, Map<String, Object> params) {
		StringBuilder whereStr = new StringBuilder(hql);
		whereStr.append(" where 1=1 ");
		if ( (Person.getName() != null && !Person.getName().isEmpty()) && Person.getUsercode() == null) {
			whereStr.append(" and t.name like :name ");
			params.put("name", "%%" + Person.getName().trim() + "%%");
		}
			
		if ( (Person.getName() == null || Person.getName().isEmpty()) && (Person.getUsercode() == null || Person.getUsercode().isEmpty())) {
			whereStr.append("  ");
		}
		
		
		if( (Person.getName() != null && !Person.getName().isEmpty()) &&
				(Person.getUsercode() != null && !Person.getUsercode().isEmpty()) ){
			whereStr.append(" and t.name like :name or t.usercode like :usercode  ");
			params.put("name", "%%" + Person.getName().trim() + "%%");
			params.put("usercode", "%%" + Person.getUsercode().trim() + "%%");
		}else if(Person.getName() == null&& Person.getUsercode()!=null&&!Person.getUsercode().isEmpty()){
			whereStr.append(" and  t.usercode like :usercode  ");
			//params.put("name", "%%" + Person.getName().trim() + "%%");
			params.put("usercode", "%%" + Person.getUsercode().trim() + "%%");
		}
		
		
		
		return whereStr.toString();
	}

	@Override
	public void remove(String ids) {
		// for (String id : ids.split(",")) {
		// TPerson u = PersonDao.get(TPerson.class, id);
		// if (u != null) {
		// PersonDao.delete(u);
		// }
		// }
		String[] nids = ids.split(",");
		String hql = "delete TPerson t where t.personid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		PersonDao.executeHql(hql);
	}

	@Override
	public Person edit(Person Person) {
		TPerson t = PersonDao.get(TPerson.class, Person.getPersonid());
		 BeanUtils.copyProperties(Person,t, new String[] {"personid"});
		 t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		PersonDao.saveOrUpdate(t);
		return Person;
	}
	
	@Override
	public DataGrid getPeronByCode(String code) {
		DataGrid dg = new DataGrid();
		String hql = "from TPerson u where u.usercode = ?";
		String[] objs = { code };
		List<TPerson> resultList = PersonDao.findEntityByHQL(hql, objs);
		List<Person> nl = new ArrayList<Person>();
		changeModel(resultList, nl);
		//String totalHql = "select count(*) " + hql;
		dg.setTotal((long)resultList.size());
		dg.setRows(nl);
		return dg;
	}
	
	@Override
	public TPerson findPerson(String Personcode) {
		String hql = "from TPerson u where u.usercode = ?";
		String[] objs = { Personcode };
		List<TPerson> list = PersonDao.findEntityByHQL(hql, objs);
		TPerson Person = ValidateUtil.isValid(list) ? list.get(0) : null;
		return Person;
	}

	@Override
	public Person findPersonById(String Personid) {
		String hql = "from TPerson u where u.personid = ?";
		Integer[] objs = {};
		objs[0] = Integer.parseInt(Personid.trim());
		List<TPerson> list = PersonDao.findEntityByHQL(hql, objs);
		TPerson tPerson = ValidateUtil.isValid(list) ? list.get(0) : null;
		Person Person = new Person();
		BeanUtils.copyProperties(tPerson, Person);
		return Person;
	}

	@Override
	public List<Person> ThesisByPoi(FileInputStream fis) {
		List<Person> infos = new ArrayList<Person>();
		
		try {
			if(fis!=null){
				//执行person同时执行user表中数据的插入和付权限
				/*PersonImportEvent<TPerson> importEvent = new PersonImportEvent<TPerson>();
				_importExcel.setEventProxy(importEvent);*/
				_importExcel.setEventProxy(null);
				_importExcel.ImportExcelToDB(tableName, fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infos;
	}

	@Override
	public List<TPerson> findPersonsByHQL(String domainString) {
			String[] objs = { domainString.trim() };
			String hql = "from TPerson t where t.domain = ?";
			return PersonDao.findEntityByHQL(hql, objs);
	}
	
	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Person", tableName, hsql, params);
	}	
}

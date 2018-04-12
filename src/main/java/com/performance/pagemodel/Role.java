package com.performance.pagemodel;

// default package

import java.util.HashSet;
import java.util.Set;


/**
 * Role entity. @author yp6124830@126.com
 */

public class Role  extends BaseModel implements java.io.Serializable {

	private Integer roleid;
	private String rolename;
	private String rolevalue;
	private String roledesc;
	private Set<User> users = new HashSet<User>();
	private Set<Right> rights = new HashSet<Right>();
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolevalue() {
		return rolevalue;
	}
	public void setRolevalue(String rolevalue) {
		this.rolevalue = rolevalue;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Right> getRights() {
		return rights;
	}
	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
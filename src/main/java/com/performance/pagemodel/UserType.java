package com.performance.pagemodel;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * UserType entity. @author yp6124830@126.com
 */

public class UserType  extends BaseModel  implements java.io.Serializable {


    // Fields    

     private Integer usertypeid;
     private String name;
     private Set<User> users = new HashSet<User>(0);
	public Integer getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(Integer usertypeid) {
		this.usertypeid = usertypeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
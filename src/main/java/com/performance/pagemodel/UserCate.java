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



public class UserCate   extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer usercateid;
     private String name;
     private String apply;
     private Set<User> users = new HashSet<User>(0);
	public Integer getUsercateid() {
		return usercateid;
	}
	public void setUsercateid(Integer usercateid) {
		this.usercateid = usercateid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}


}
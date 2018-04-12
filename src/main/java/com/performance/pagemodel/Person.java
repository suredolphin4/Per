package com.performance.pagemodel;

import java.io.Serializable;
import java.sql.Timestamp;

public class Person   extends BaseModel implements Serializable{

	
	 private Integer personid;
     private String personcate;//申报级别
     private String persontype;//人员类型
     private String name;
     private String domain; //领域名称
     private String domaincode;//领域编号
     private String lab;
     private String labnum;
     private String department;//崔老师“不用去管”
     private String usercode;
     private String comment;
     private Timestamp savetime;
  	public Timestamp getSavetime() {
  		return savetime;
  	}

  	public void setSavetime(Timestamp savetime) {
  		this.savetime = savetime;
  	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getPersonid() {
		return personid;
	}
	public void setPersonid(Integer personid) {
		this.personid = personid;
	}
	public String getPersoncate() {
		return personcate;
	}
	public void setPersoncate(String personcate) {
		this.personcate = personcate;
	}
	public String getPersontype() {
		return persontype;
	}
	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomaincode() {
		return domaincode;
	}
	public void setDomaincode(String domaincode) {
		this.domaincode = domaincode;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getLabnum() {
		return labnum;
	}
	public void setLabnum(String labnum) {
		this.labnum = labnum;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
     
}

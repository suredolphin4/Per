package com.performances.model;

// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_person", catalog = "per")
@DynamicUpdate(true)
public class TPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer personid;
	private String personcate;// 申报级别
	private String persontype;// 人员类型
	private String name;
	private String domain; // 领域名称
	private String domaincode;// 领域编号
	private String lab;
	private String labnum;
	private String department;// 崔老师“不用去管”
	private String usercode;
	private Timestamp savetime;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userid", unique = true, nullable = false)
	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	@Column(name = "savetime", length = 19)
	public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	@Column(name = "personcate", length = 150)
	public String getPersoncate() {
		return personcate;
	}

	public void setPersoncate(String personcate) {
		this.personcate = personcate;
	}

	@Column(name = "persontype", length = 150)
	public String getPersontype() {
		return persontype;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	@Column(name = "name", length = 150)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "domain", length = 150)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "domaincode", length = 150)
	public String getDomaincode() {
		return domaincode;
	}

	public void setDomaincode(String domaincode) {
		this.domaincode = domaincode;
	}

	@Column(name = "lab", length = 150)
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	@Column(name = "labnum", length = 150)
	public String getLabnum() {
		return labnum;
	}

	public void setLabnum(String labnum) {
		this.labnum = labnum;
	}

	@Column(name = "department", length = 150)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "usercode", length = 150)
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public TPerson(java.sql.Timestamp savetime, Integer personid,
			String personcate, String persontype, String name, String domain,
			String domaincode, String lab, String labnum, String department,
			String usercode) {
		super();
		this.personid = personid;
		this.personcate = personcate;
		this.persontype = persontype;
		this.savetime = savetime;
		this.name = name;
		this.domain = domain;
		this.domaincode = domaincode;
		this.lab = lab;
		this.labnum = labnum;
		this.department = department;
		this.usercode = usercode;
	}

	public TPerson() {
		// TODO Auto-generated constructor stub
	}
}
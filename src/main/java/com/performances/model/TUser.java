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

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "per")
@DynamicUpdate(true)
public class TUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private TUserCate TUserCate;// 级别
	private TUserType TUserType;
	private String name;
	private String pwd;
	private String domain;
	private String domainnum;
	private String lab;
	private String labnum;
	private String comment;
	private Timestamp savetime;
	private String userCategory;
	
	

	@Column(name="savetime", length=19)
	public Timestamp getSavetime() {
		return savetime;
	}
	
	

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	@Column(name="userCategory", length=50)
	public String getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	@Column(name = "domainnum", length = 150)
	public String getDomainnum() {
		return domainnum;
	}

	public void setDomainnum(String domainnum) {
		this.domainnum = domainnum;
	}

	@Column(name = "comment", length = 150)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "userty", length = 150)
	public String getUserTy() {
		return userTy;
	}

	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}

	private String userTy;

	@Column(name = "domain", length = 150)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	private String department;// 崔老师“不用去管”
	private String usercode;

	// private Set<TZl> TZls = new HashSet<TZl>();
	// private Set<TXm> TXms = new HashSet<TXm>();
	// private Set<TZx> TZxes = new HashSet<TZx>();
	private Set<TDomain> TDomains = new HashSet<TDomain>();
	private Set<TLw> TLws = new HashSet<TLw>();
	// private Set<TJl> TJls = new HashSet<TJl>();
	private Set<TLab> TLabs = new HashSet<TLab>();
	private Set<TRole> TRoles = new HashSet<TRole>();
	// private Set<TYj> TYjs = new HashSet<TYj>();
	private Set<TRj> TRjs = new HashSet<TRj>();
	private Set<TDt> TDts = new HashSet<TDt>();
	private Set<TGh> TGhs = new HashSet<TGh>();
	private Set<TTa> TTas = new HashSet<TTa>();
	private Set<TZz> TZzs = new HashSet<TZz>();

	// private Set<TBz> TBzs = new HashSet<TBz>();

	// Constructors

	/** default constructor */
	public TUser() {
	}

	public TUser(String domainnum, String userTy, Integer userid,
			com.performances.model.TUserCate tUserCate,
			com.performances.model.TUserType tUserType, String name,
			String pwd, String domain, String lab, String labnum,
			String department, String usercode, Set<TZx> tZxes,
			Set<TDomain> tDomains, Set<TLw> tLws, Set<TLab> tLabs,String userCategory,
			Set<TRole> tRoles, Set<TRj> tRjs, Set<TDt> tDts,
			Set<TGh> tGhs,Set<TTa> tTas, Set<TZz> tZzs, Set<TBz> tBzs,java.sql.Timestamp savetime) {
		this.userid = userid;
		TUserCate = tUserCate;
		this.savetime=savetime;
		this.userCategory=userCategory;
		TUserType = tUserType;
		this.domainnum = domainnum;
		this.name = name;
		this.pwd = pwd;
		this.domain = domain;
		this.lab = lab;
		this.labnum = labnum;
		this.department = department;
		this.usercode = usercode;
		this.userTy = userTy;
		// TZls = tZls;
		// TXms = tXms;
		// TZxes = tZxes;
		TDomains = tDomains;
		TLws = tLws;
		// TJls = tJls;
		TLabs = tLabs;
		TRoles = tRoles;
		// TYjs = tYjs;
		TRjs = tRjs;
		TDts = tDts;
		TGhs = tGhs;
		TTas = tTas;
		TZzs = tZzs;
		// TBzs = tBzs;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userid", unique = true, nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usercateid")
	public TUserCate getTUserCate() {
		return this.TUserCate;
	}

	public void setTUserCate(TUserCate TUserCate) {
		this.TUserCate = TUserCate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usertypeid")
	public TUserType getTUserType() {
		return this.TUserType;
	}

	public void setTUserType(TUserType TUserType) {
		this.TUserType = TUserType;
	}

	@Column(name = "name", length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pwd", length = 50)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	// @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
	// mappedBy="TUsers")
	//
	// public Set<TZl> getTZls() {
	// return this.TZls;
	// }
	//
	// public void setTZls(Set<TZl> TZls) {
	// this.TZls = TZls;
	// }

	// @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
	// mappedBy="TUsers")
	// public Set<TXm> getTXms() {
	// return this.TXms;
	// }
	//
	// public void setTXms(Set<TXm> TXms) {
	// this.TXms = TXms;
	// }

	// @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
	// mappedBy="TUsers")
	// public Set<TZx> getTZxes() {
	// return this.TZxes;
	// }
	//
	// public void setTZxes(Set<TZx> TZxes) {
	// this.TZxes = TZxes;
	// }

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_domain", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "domainid", nullable = false, updatable = false) })
	public Set<TDomain> getTDomains() {
		return this.TDomains;
	}

	public void setTDomains(Set<TDomain> TDomains) {
		this.TDomains = TDomains;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_lw", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "lwid", nullable = false, updatable = false) })
	public Set<TLw> getTLws() {
		return this.TLws;
	}

	public void setTLws(Set<TLw> TLws) {
		this.TLws = TLws;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_lab", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "labid", nullable = false, updatable = false) })
	public Set<TLab> getTLabs() {
		return this.TLabs;
	}

	public void setTLabs(Set<TLab> TLabs) {
		this.TLabs = TLabs;
	}

	@ManyToMany(cascade ={CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleid", nullable = false, updatable = false) })
	public Set<TRole> getTRoles() {
		return this.TRoles;
	}

	public void setTRoles(Set<TRole> TRoles) {
		this.TRoles = TRoles;
	}

	// @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
	// mappedBy="TUsers")
	// public Set<TYj> getTYjs() {
	// return this.TYjs;
	// }
	//
	// public void setTYjs(Set<TYj> TYjs) {
	// this.TYjs = TYjs;
	// }

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_rj", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "rjid", nullable = false, updatable = false) })
	public Set<TRj> getTRjs() {
		return this.TRjs;
	}

	public void setTRjs(Set<TRj> TRjs) {
		this.TRjs = TRjs;
	}



	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_dt", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "dtid", nullable = false, updatable = false) })
	public Set<TDt> getTDts() {
		return this.TDts;
	}

	public void setTDts(Set<TDt> TDts) {
		this.TDts = TDts;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_gh", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ghid", nullable = false, updatable = false) })
	public Set<TGh> getTGhs() {
		return this.TGhs;
	}

	public void setTGhs(Set<TGh> TGhs) {
		this.TGhs = TGhs;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "relationship_user_ta", catalog = "per", joinColumns = { @JoinColumn(name = "userid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "taid", nullable = false, updatable = false) })
	public Set<TTa> getTTas() {
		return this.TTas;
	}

	public void setTTas(Set<TTa> TTas) {
		this.TTas = TTas;
	}


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUsers")
	public Set<TZz> getTZzs() {
		return this.TZzs;
	}

	public void setTZzs(Set<TZz> TZzs) {
		this.TZzs = TZzs;
	}
	// @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// @JoinTable(name="relationship_user_bz", catalog="per", joinColumns = {
	// @JoinColumn(name="userid", nullable=false, updatable=false) },
	// inverseJoinColumns = {
	// @JoinColumn(name="bzid", nullable=false, updatable=false) })
	//
	// public Set<TBz> getTBzs() {
	// return this.TBzs;
	// }
	//
	// public void setTBzs(Set<TBz> TBzs) {
	// this.TBzs = TBzs;
	// }

}
package com.performances.model;

// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 研究室
 */
@Entity
@Table(name = "t_lab", catalog = "per")
public class TLab implements java.io.Serializable {

	// Fields

	private Integer labid;
	private String code;
	private String name;
	private String domainname;
	private Integer domaincode;

	private TDomain TDomain;
	private Set<TUser> TUsers = new HashSet<TUser>(0);
//	private Set<TDt> TDts = new HashSet<TDt>(0);
//	private Set<TGh> TGhs = new HashSet<TGh>(0);
//	private Set<TBz> TBzs = new HashSet<TBz>(0);
//	private Set<TJl> TJls = new HashSet<TJl>(0);
//	private Set<TRj> TRjs = new HashSet<TRj>(0);
//	private Set<TSk> TSks = new HashSet<TSk>(0);
//	private Set<TXm> TXms = new HashSet<TXm>(0);
//	private Set<TYj> TYjs = new HashSet<TYj>(0);
//	private Set<TZl> TZls = new HashSet<TZl>(0);
	//private Set<TZx> TZxs = new HashSet<TZx>(0);
	private Set<TZz> TZzs = new HashSet<TZz>(0);

	@Column(name = "code", nullable = false, length = 150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	// Constructors
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TDt> getTDts() {
//		return TDts;
//	}
//
//	public void setTDts(Set<TDt> tDts) {
//		TDts = tDts;
//	}

	/** default constructor */
	public TLab() {
	}

	/** minimal constructor */
	public TLab(String name) {
		this.name = name;
	}

	

	public TLab(Integer labid, String code, String name, String domainname, Integer domaincode, com.performances.model.TDomain tDomain, Set<TUser> tUsers, Set<TDt> tDts, Set<TGh> tGhs, Set<TBz> tBzs, Set<TLw> tLws, Set<TRj> tRjs, Set<TZx> tZxs, Set<TZz> tZzs) {
		super();
		this.labid = labid;
		this.code = code;
		this.name = name;
		this.domainname = domainname;
		this.domaincode = domaincode;
		TDomain = tDomain;
		TUsers = tUsers;
//		TDts = tDts;
//		TGhs = tGhs;
//		TBzs = tBzs;
//		TJls = tJls;
//		TRjs = tRjs;
//		TSks = tSks;
//		TXms = tXms;
//		TYjs = tYjs;
//		TZls = tZls;
		//TZxs = tZxs;
		TZzs = tZzs;
	}

	@Column(name = "domainname", nullable = false, length = 150)
	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	@Column(name = "domaincode")
	public Integer getDomaincode() {
		return domaincode;
	}

	public void setDomaincode(Integer domaincode) {
		this.domaincode = domaincode;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "labid", unique = true, nullable = false)
	public Integer getLabid() {
		return this.labid;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TGh> getTGhs() {
//		return TGhs;
//	}
//
//	public void setTGhs(Set<TGh> tGhs) {
//		TGhs = tGhs;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TBz> getTBzs() {
//		return TBzs;
//	}
//
//	public void setTBzs(Set<TBz> tBzs) {
//		TBzs = tBzs;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TJl> getTJls() {
//		return TJls;
//	}
//
//	public void setTJls(Set<TJl> tJls) {
//		TJls = tJls;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TRj> getTRjs() {
//		return TRjs;
//	}
//
//	public void setTRjs(Set<TRj> tRjs) {
//		TRjs = tRjs;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TSk> getTSks() {
//		return TSks;
//	}
//
//	public void setTSks(Set<TSk> tSks) {
//		TSks = tSks;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TXm> getTXms() {
//		return TXms;
//	}
//
//	public void setTXms(Set<TXm> tXms) {
//		TXms = tXms;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TYj> getTYjs() {
//		return TYjs;
//	}
//
//	public void setTYjs(Set<TYj> tYjs) {
//		TYjs = tYjs;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TZl> getTZls() {
//		return TZls;
//	}
//
//	public void setTZls(Set<TZl> tZls) {
//		TZls = tZls;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
//	public Set<TZx> getTZxs() {
//		return TZxs;
//	}
//
//	public void setTZxs(Set<TZx> tZxs) {
//		TZxs = tZxs;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLab")
	public Set<TZz> getTZzs() {
		return TZzs;
	}

	public void setTZzs(Set<TZz> tZzs) {
		TZzs = tZzs;
	}

	public void setLabid(Integer labid) {
		this.labid = labid;
	}

	@Column(name = "name", nullable = false, length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TLabs")
	public Set<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set<TUser> TUsers) {
		this.TUsers = TUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domainid")
	public TDomain getTDomain() {
		return TDomain;
	}

	public void setTDomain(TDomain tDomain) {
		TDomain = tDomain;
	}

}
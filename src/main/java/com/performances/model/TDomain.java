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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 */
@Entity
@Table(name = "t_domain", catalog = "per")
@DynamicUpdate(true)
public class TDomain implements java.io.Serializable {

	// Fields

	private Integer domainid;
	private String name;
	// private Set<TUser> TUsers = new HashSet<TUser>(0);
	// private Set<TDt> TDts = new HashSet<TDt>(0);
	// private Set<TGh> TGhs = new HashSet<TGh>(0);
	// private Set<TBz> TBzs = new HashSet<TBz>(0);
	// private Set<TJl> TJls = new HashSet<TJl>(0);
	// private Set<TRj> TRjs = new HashSet<TRj>(0);
	// private Set<TSk> TSks = new HashSet<TSk>(0);
	// private Set<TXm> TXms = new HashSet<TXm>(0);
	// private Set<TZl> TZls = new HashSet<TZl>(0);
	// private Set<TZx> TZxs = new HashSet<TZx>(0);
	private Set<TZz> TZzs = new HashSet<TZz>(0);
	private Set<TLab> TLabs = new HashSet<TLab>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TDomain")
	public Set<TLab> getTLabs() {
		return TLabs;
	}

	public void setTLabs(Set<TLab> tLabs) {
		TLabs = tLabs;
	}

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TGh> getTGhs() {
	// return TGhs;
	// }
	//
	// public void setTGhs(Set<TGh> tGhs) {
	// TGhs = tGhs;
	// }
	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TBz> getTBzs() {
	// return TBzs;
	// }
	//
	// public void setTBzs(Set<TBz> tBzs) {
	// TBzs = tBzs;
	// }
	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TJl> getTJls() {
	// return TJls;
	// }
	//
	// public void setTJls(Set<TJl> tJls) {
	// TJls = tJls;
	// }

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TRj> getTRjs() {
	// return TRjs;
	// }
	//
	// public void setTRjs(Set<TRj> tRjs) {
	// TRjs = tRjs;
	// }

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TSk> getTSks() {
	// return TSks;
	// }
	//
	// public void setTSks(Set<TSk> tSks) {
	// TSks = tSks;
	// }

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TXm> getTXms() {
	// return TXms;
	// }
	//
	// public void setTXms(Set<TXm> tXms) {
	// TXms = tXms;
	// }

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TZl> getTZls() {
	// return TZls;
	// }
	//
	// public void setTZls(Set<TZl> tZls) {
	// TZls = tZls;
	// }

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TDomain")
	public Set<TZz> getTZzs() {
		return TZzs;
	}

	public void setTZzs(Set<TZz> tZzs) {
		TZzs = tZzs;
	}

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="TDomain")
	// public Set<TDt> getTDts() {
	// return TDts;
	// }
	//
	// public void setTDts(Set<TDt> tDts) {
	// TDts = tDts;
	// }

	/** default constructor */
	public TDomain() {
	}

	/** full constructor */
	public TDomain(String name, Set<TLab> TLabs, Set<TDt> TDts, Set<TGh> TGhs,
			Set<TBz> TBzs, Set<TRj> TRjs, Set<TZx> TZxs, Set<TZz> TZzs) {
		/*
		 * public TDomain(String name, Set<TUser> TUsers,Set<TDt> TDts,Set<TGh>
		 * TGhs,Set<TBz> TBzs,Set<TJl> TJls,Set<TLw> TLws,Set<TRj> TRjs,Set<TSk>
		 * TSks,Set<TXm> TXms,Set<TYj> TYjs,Set<TZl> TZls,Set<TZx> TZxs,Set<TZz>
		 * TZzs) { this.TUsers = TUsers;
		 */
		this.name = name;
		// this.TDts=TDts;
		// this.TGhs=TGhs;
		// this.TBzs=TBzs;
		// this.TRjs=TRjs;
		// this.TSks=TSks;
		// this.TXms=TXms;
		// this.TZls=TZls;
		// this.TZxs=TZxs;
		this.TZzs = TZzs;
		this.TLabs = TLabs;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "domainid", unique = true, nullable = false)
	public Integer getDomainid() {
		return this.domainid;
	}

	public void setDomainid(Integer domainid) {
		this.domainid = domainid;
	}

	@Column(name = "name", nullable = false, length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 * @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
	 * mappedBy="TDomains")
	 * 
	 * public Set<TUser> getTUsers() { return this.TUsers; }
	 * 
	 * public void setTUsers(Set<TUser> TUsers) { this.TUsers = TUsers; }
	 */

}
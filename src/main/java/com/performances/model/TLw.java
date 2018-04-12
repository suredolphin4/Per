package com.performances.model;

// default package

import java.util.Collection;
import java.util.Date;
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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 论文 TLw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_lw", catalog = "per")
@DynamicUpdate(true) // 允许部分更新
public class TLw implements java.io.Serializable {

	// Fieldsd
	private Integer lwid;
	private TJcr TJcr;
	//private TLwStutas TLwStutas;
	private String status;
	private String doi;
	private String append;
	private String examinestatus;
	private Date examinetime;
	private Date ignoretime;
	private String markid;
	private Integer numauthor;
	private String otherauthor;
	private Date savetime;
	private String firstauthor;
	private String fiveauthor;
	private String fourauthor;
	private String secauthor;
	private String threeauthor;
	private String commauthor;

	private String otherauthorcode;
	private String firstauthorcode;
	private String fiveauthorcode;
	private String fourauthorcode;
	private String secauthorcode;
	private String threeauthorcode;
	private String commauthorcode;

	private Date submitTime;
	private String submitUser;
	private TPubPart TPubPart;
	private String pubkind;
	private String pubtitle;
	private String pubcode;
	private String inter;
	private String pubranking;
	private String interpartner;
	private String factor;
	private TPubRank TPubRank;
	private String subclass;
	private String topcomm;
	private String roll;
	private String expect;
	private String begin2end;
	private String unit;
	private String cell;
	private String authnum;
	private String title;
	private String year;
	private Set<TUser> TUsers = new HashSet<TUser>(0);

	// 新增审批意见
	private String auditOpinion;
	private String auditUser;

	// Constructors

	/** default constructor */
	public TLw() {
	}

	/** full constructor */

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lwid", unique = true, nullable = false)
	public Integer getLwid() {
		return this.lwid;
	}

	public TLw(Integer lwid, com.performances.model.TJcr tJcr,
			String status, String append,
			String examinestatus, Date examinetime, Date ignoretime,
			String markid, Integer numauthor, String otherauthor,
			Date savetime, String firstauthor, String fiveauthor,
			String fourauthor, String secauthor, String threeauthor,
			String commauthor, String otherauthorcode, String firstauthorcode,
			String fiveauthorcode, String fourauthorcode, String secauthorcode,
			String threeauthorcode, String commauthorcode,
			Date submittime, com.performances.model.TPubPart tPubPart,
			String pubkind, String pubtitle, String pubcode, String inter,
			String pubranking, String interpartner, String factor,
			com.performances.model.TPubRank tPubRank, String subclass,
			String topcomm, String roll, String expect, String begin2end,
			String unit, String cell, String authnum, String title, String year,
			Set<TUser> tUsers) {
		super();
		this.lwid = lwid;
		TJcr = tJcr;
		//TLwStutas = tLwStutas;
		this.status = status;
		this.append = append;
		this.examinestatus = examinestatus;
		this.examinetime = examinetime;
		this.ignoretime = ignoretime;
		this.markid = markid;
		this.numauthor = numauthor;
		this.otherauthor = otherauthor;
		this.savetime = savetime;
		this.firstauthor = firstauthor;
		this.fiveauthor = fiveauthor;
		this.fourauthor = fourauthor;
		this.secauthor = secauthor;
		this.threeauthor = threeauthor;
		this.commauthor = commauthor;
		this.otherauthorcode = otherauthorcode;
		this.firstauthorcode = firstauthorcode;
		this.fiveauthorcode = fiveauthorcode;
		this.fourauthorcode = fourauthorcode;
		this.secauthorcode = secauthorcode;
		this.threeauthorcode = threeauthorcode;
		this.commauthorcode = commauthorcode;
		this.submitTime = submittime;
		TPubPart = tPubPart;
		this.pubkind = pubkind;
		this.pubtitle = pubtitle;
		this.pubcode = pubcode;
		this.inter = inter;
		this.pubranking = pubranking;
		this.interpartner = interpartner;
		this.factor = factor;
		TPubRank = tPubRank;
		this.subclass = subclass;
		this.topcomm = topcomm;
		this.roll = roll;
		this.expect = expect;
		this.begin2end = begin2end;
		this.unit = unit;
		this.cell = cell;
		this.authnum = authnum;
		this.title = title;
		this.year = year;
		TUsers = tUsers;
	}

	@Column(name = "firstauthorcode", length = 100)
	public String getFirstauthorcode() {
		return firstauthorcode;
	}

	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name = "otherauthorcode", length = 500)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}

	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	@Column(name = "fiveauthorcode", length = 100)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}

	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name = "fourauthorcode", length = 100)
	public String getFourauthorcode() {
		return fourauthorcode;
	}

	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name = "secauthorcode", length = 100)
	public String getSecauthorcode() {
		return secauthorcode;
	}

	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	@Column(name = "threeauthorcode", length = 100)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}

	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name = "commauthorcode", length = 100)
	public String getCommauthorcode() {
		return commauthorcode;
	}

	public void setCommauthorcode(String commauthorcode) {
		this.commauthorcode = commauthorcode;
	}

	public void setLwid(Integer lwid) {
		this.lwid = lwid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jcrid")
	public TJcr getTJcr() {
		return this.TJcr;
	}

	public void setTJcr(TJcr TJcr) {
		this.TJcr = TJcr;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "lwstutasid")
//	public TLwStutas getTLwStutas() {
//		return this.TLwStutas;
//	}
//
//	public void setTLwStutas(TLwStutas TLwStutas) {
//		this.TLwStutas = TLwStutas;
//	}
	
	@Column(name = "status", length=20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "doi", length = 255)
	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	@Column(name = "append", length = 255)
	public String getAppend() {
		return this.append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	@Column(name = "examinestatus")
	public String getExaminestatus() {
		return this.examinestatus;
	}

	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}

	@Column(name = "examinetime", length = 19)
	public Date getExaminetime() {
		return this.examinetime;
	}

	public void setExaminetime(Date examinetime) {
		this.examinetime = examinetime;
	}

	@Column(name = "firstauthor", length = 500)
	public String getFirstauthor() {
		return this.firstauthor;
	}

	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}

	@Column(name = "fiveauthor", length = 500)
	public String getFiveauthor() {
		return this.fiveauthor;
	}

	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}

	@Column(name = "fourauthor", length = 500)
	public String getFourauthor() {
		return this.fourauthor;
	}

	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}

	@Column(name = "ignoretime", length = 19)
	public Date getIgnoretime() {
		return this.ignoretime;
	}

	public void setIgnoretime(Date ignoretime) {
		this.ignoretime = ignoretime;
	}

	@Column(name = "markid", length = 500)
	public String getMarkid() {
		return this.markid;
	}

	public void setMarkid(String markid) {
		this.markid = markid;
	}

	@Column(name = "numauthor")
	public Integer getNumauthor() {
		return this.numauthor;
	}

	public void setNumauthor(Integer numauthor) {
		this.numauthor = numauthor;
	}

	@Column(name = "otherauthor", length = 1024)
	public String getOtherauthor() {
		return this.otherauthor;
	}

	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}

	@Column(name = "savetime", length = 19)
	public Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	@Column(name = "secauthor", length = 500)
	public String getSecauthor() {
		return this.secauthor;
	}

	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}

	@Column(name = "submittime", length = 19)
	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submittime) {
		this.submitTime = submittime;
	}
	
	@Column(name = "submituser", length = 50)
	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	@Column(name = "threeauthor", length = 500)
	public String getThreeauthor() {
		return this.threeauthor;
	}

	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}

	@Column(name = "title", length = 3000)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "year", length = 50)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TLws")
	public Set<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set<TUser> TUsers) {
		this.TUsers = TUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tpid")
	public TPubPart getTPubPart() {
		return TPubPart;
	}

	public void setTPubPart(TPubPart tPubPart) {
		TPubPart = tPubPart;
	}

	@Column(name = "commauthor", length = 200)
	public String getCommauthor() {
		return commauthor;
	}

	public void setCommauthor(String commauthor) {
		this.commauthor = commauthor;
	}

	@Column(name = "pubkind", length = 200)
	public String getPubkind() {
		return pubkind;
	}

	public void setPubkind(String pubkind) {
		this.pubkind = pubkind;
	}

	@Column(name = "pubtitle", length = 200)
	public String getPubtitle() {
		return pubtitle;
	}

	public void setPubtitle(String pubtitle) {
		this.pubtitle = pubtitle;
	}

	@Column(name = "pubcode", length = 200)
	public String getPubcode() {
		return pubcode;
	}

	public void setPubcode(String pubcode) {
		this.pubcode = pubcode;
	}

	@Column(name = "inter", length = 200)
	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	@Column(name = "pubranking", length = 200)
	public String getPubranking() {
		return pubranking;
	}

	public void setPubranking(String pubranking) {
		this.pubranking = pubranking;
	}

	@Column(name = "interpartner", length = 200)
	public String getInterpartner() {
		return interpartner;
	}

	public void setInterpartner(String interpartner) {
		this.interpartner = interpartner;
	}

	@Column(name = "factor", length = 200)
	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trid")
	public TPubRank getTPubRank() {
		return TPubRank;
	}

	public void setTPubRank(TPubRank tPubRank) {
		TPubRank = tPubRank;
	}

	@Column(name = "subclass", length = 200)
	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	@Column(name = "topcomm", length = 200)
	public String getTopcomm() {
		return topcomm;
	}

	public void setTopcomm(String topcomm) {
		this.topcomm = topcomm;
	}

	@Column(name = "roll", length = 200)
	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	@Column(name = "expect", length = 200)
	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	@Column(name = "begin2end", length = 200)
	public String getBegin2end() {
		return begin2end;
	}

	public void setBegin2end(String begin2end) {
		this.begin2end = begin2end;
	}

	@Column(name = "unit", length = 200)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "cell", length = 200)
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	@Column(name = "authnum", length = 200)
	public String getAuthnum() {
		return authnum;
	}

	public void setAuthnum(String authnum) {
		this.authnum = authnum;
	}

	@Column(name = "auditopinion", length = 200)
	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	@Column(name = "audituser", length = 50)
	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	
	private Collection<TLwScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TLwScore> getScore(){
		return score;
	}
	public void setScore(Collection<TLwScore> score){
		this.score = score;
	}
	
}
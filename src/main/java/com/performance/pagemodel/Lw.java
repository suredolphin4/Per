package com.performance.pagemodel;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.File;

import com.performances.model.TJcr;
import com.performances.model.TLwStutas;
import com.performances.model.TPubPart;
import com.performances.model.TPubRank;
import com.performances.model.TUser;

/**
 * 论文 Lw entity. @author yp6124830@126.com
 */

public class Lw extends BaseModel implements java.io.Serializable {

	private Integer lwid;
	private Jcr Jcr;
	//private LwStutas LwStutas;
	private String status;
	private String doi;
	private String examinestatus;
	private String auditOpinion;
	private String auditUser;
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
	private PubPart PubPart;
	private String pubkind;
	private String pubtitle;
	private String pubcode;
	private String inter;
	private String pubranking;
	private String interpartner;
	private String factor;
	private PubRank PubRank;
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
	private Set<User> Users = new HashSet<User>(0);

	// 过滤条件
	private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
	private String s_begin_year;
	private String s_end_year;
	private String s_lw_name;
	private String s_lw_pubname;
	private String s_lw_author;
	private String s_lw_authorcode;
	private String s_which_lab;
	private String s_which_domain;

	// 论文附件上传下载相关
	private String append; // 上传文件名

	// 获取分数
	private String	commauthorscore;
    private String 	firstauthorscore;
    private String	secauthorscore;
    private String  threeauthorscore;
    private String	fourauthorscore;
    private String	fiveauthorscore;
    
	public Integer getLwid() {
		return lwid;
	}

	public void setLwid(Integer lwid) {
		this.lwid = lwid;
	}

	public Jcr getJcr() {
		return Jcr;
	}

	public void setJcr(Jcr jcr) {
		Jcr = jcr;
	}

//	public LwStutas getLwStutas() {
//		return LwStutas;
//	}
//
//	public void setLwStutas(LwStutas lwStutas) {
//		LwStutas = lwStutas;
//	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}
	
	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}
	
	public String getExaminestatus() {
		return examinestatus;
	}

	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getExaminetime() {
		return examinetime;
	}

	public void setExaminetime(Date examinetime) {
		this.examinetime = examinetime;
	}

	public Date getIgnoretime() {
		return ignoretime;
	}

	public void setIgnoretime(Date ignoretime) {
		this.ignoretime = ignoretime;
	}

	public String getMarkid() {
		return markid;
	}

	public void setMarkid(String markid) {
		this.markid = markid;
	}

	public Integer getNumauthor() {
		return numauthor;
	}

	public void setNumauthor(Integer numauthor) {
		this.numauthor = numauthor;
	}

	public String getOtherauthor() {
		return otherauthor;
	}

	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	public String getFirstauthor() {
		return firstauthor;
	}

	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}

	public String getFiveauthor() {
		return fiveauthor;
	}

	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}

	public String getFourauthor() {
		return fourauthor;
	}

	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}

	public String getSecauthor() {
		return secauthor;
	}

	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}

	public String getThreeauthor() {
		return threeauthor;
	}

	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}

	public String getCommauthor() {
		return commauthor;
	}

	public void setCommauthor(String commauthor) {
		this.commauthor = commauthor;
	}

	public String getOtherauthorcode() {
		return otherauthorcode;
	}

	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	public String getFirstauthorcode() {
		return firstauthorcode;
	}

	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	public String getFiveauthorcode() {
		return fiveauthorcode;
	}

	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	public String getFourauthorcode() {
		return fourauthorcode;
	}

	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	public String getSecauthorcode() {
		return secauthorcode;
	}

	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	public String getThreeauthorcode() {
		return threeauthorcode;
	}

	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	public String getCommauthorcode() {
		return commauthorcode;
	}

	public void setCommauthorcode(String commauthorcode) {
		this.commauthorcode = commauthorcode;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submittime) {
		this.submitTime = submittime;
	}
	
	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	public PubPart getPubPart() {
		return PubPart;
	}

	public void setPubPart(PubPart pubPart) {
		PubPart = pubPart;
	}

	public String getPubkind() {
		return pubkind;
	}

	public void setPubkind(String pubkind) {
		this.pubkind = pubkind;
	}

	public String getPubtitle() {
		return pubtitle;
	}

	public void setPubtitle(String pubtitle) {
		this.pubtitle = pubtitle;
	}

	public String getPubcode() {
		return pubcode;
	}

	public void setPubcode(String pubcode) {
		this.pubcode = pubcode;
	}

	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	public String getPubranking() {
		return pubranking;
	}

	public void setPubranking(String pubranking) {
		this.pubranking = pubranking;
	}

	public String getInterpartner() {
		return interpartner;
	}

	public void setInterpartner(String interpartner) {
		this.interpartner = interpartner;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public PubRank getPubRank() {
		return PubRank;
	}

	public void setPubRank(PubRank pubRank) {
		PubRank = pubRank;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getTopcomm() {
		return topcomm;
	}

	public void setTopcomm(String topcomm) {
		this.topcomm = topcomm;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getBegin2end() {
		return begin2end;
	}

	public void setBegin2end(String begin2end) {
		this.begin2end = begin2end;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getAuthnum() {
		return authnum;
	}

	public void setAuthnum(String authnum) {
		this.authnum = authnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Set<User> getUsers() {
		return Users;
	}

	public void setUsers(Set<User> users) {
		Users = users;
	}

	public String getAuditfilter() {
		return auditfilter;
	}

	public void setAuditfilter(String auditfilter) {
		this.auditfilter = auditfilter;
	}

	public String getS_begin_year() {
		return s_begin_year;
	}

	public void setS_begin_year(String s_begin_year) {
		this.s_begin_year = s_begin_year;
	}

	public String getS_end_year() {
		return s_end_year;
	}

	public void setS_end_year(String s_end_year) {
		this.s_end_year = s_end_year;
	}

	public String getS_lw_name() {
		return s_lw_name;
	}

	public void setS_lw_name(String s_lw_name) {
		this.s_lw_name = s_lw_name;
	}

	public String getS_lw_pubname() {
		return s_lw_pubname;
	}

	public void setS_lw_pubname(String s_lw_pubname) {
		this.s_lw_pubname = s_lw_pubname;
	}

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}

	public String getS_lw_author() {
		return s_lw_author;
	}

	public void setS_lw_author(String s_lw_author) {
		this.s_lw_author = s_lw_author;
	}

	public String getS_which_lab() {
		return s_which_lab;
	}

	public void setS_which_lab(String s_which_lab) {
		this.s_which_lab = s_which_lab;
	}

	public String getS_which_domain() {
		return s_which_domain;
	}

	public void setS_which_domain(String s_which_domain) {
		this.s_which_domain = s_which_domain;
	}
	
	//分数
	public String getFirstauthorscore() {
		return firstauthorscore;
	}
	public void setFirstauthorscore(String firstauthorscore) {
		this.firstauthorscore = firstauthorscore;
	}
	public String getSecauthorscore() {
		return secauthorscore;
	}
	public void setSecauthorscore(String secauthorscore) {
		this.secauthorscore = secauthorscore;
	}
	public String getThreeauthorscore() {
		return threeauthorscore;
	}
	public void setThreeauthorscore(String threeauthorscore) {
		this.threeauthorscore = threeauthorscore;
	}
	public String getFourauthorscore() {
		return fourauthorscore;
	}
	public void setFourauthorscore(String fourauthorscore) {
		this.fourauthorscore = fourauthorscore;
	}
	public String getFiveauthorscore() {
		return fiveauthorscore;
	}
	public void setFiveauthorscore(String fiveauthorscore) {
		this.fiveauthorscore = fiveauthorscore;
	}
	public String getCommauthorscore() {
		return commauthorscore;
	}
	public void setCommauthorscore(String commauthorscore) {
		this.commauthorscore = commauthorscore;
	}

}
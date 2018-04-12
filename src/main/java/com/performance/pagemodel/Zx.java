package com.performance.pagemodel;
// default package

import java.io.File;
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

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TDomain;
import com.performances.model.TLab;
/**
 * 咨询报告
 * @author peng
 *
 */

public class Zx   extends BaseModel implements java.io.Serializable {

	 // Fields    
    private Integer zxid;
    private String year;
    private String	title;
    private String auditleader;
    private String	auditdepart;
    private String	auditlevel;
    private String	auditdate;
    private String numauthor;
    private String	firstauthor;
    private String	firstauthorcode;
    private String	secauthor;
    private String 	secauthorcode;
    private String	threeauthor;
    private String	threeauthorcode;
    private String	fourauthor;
    private String	fourauthorcode;
    private String	fiveauthor;
    private String	fiveauthorcode;
    private String	otherauthor;
    private String	otherauthorcode;
    private String	uploadurl;
    private String	examinestatus;  
    private String  auditopinion;
	private Timestamp examinetime;
	private Timestamp ignoretime;
	private Timestamp savetime;
    private Set<User> users = new HashSet<User>(0);
    // 获取分数
    private String 	firstauthorscore;
    private String	secauthorscore;
    private String  threeauthorscore;
    private String	fourauthorscore;
    private String	fiveauthorscore;
    private String	otherauthorscore;
    
    // 过滤条件
 	private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
 	private String s_begin_year;
 	private String s_end_year;
 	private String s_lw_name;
 	private String s_lw_author;
	private String s_lw_authorcode;
 	private String s_which_lab;
 	private String s_which_domain;
   
 // 论文附件上传下载相关
 	private String append; // 上传文件名
 	
 	private String submitUser;
 	private java.sql.Timestamp submitTime;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}
	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}

	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public Integer getZxid() {
		return zxid;
	}
	public void setZxid(Integer zxid) {
		this.zxid = zxid;
	}
	public String getYear() {
		return year;
	}
	public String getAuditopinion() {
		return auditopinion;
	}
	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuditleader() {
		return auditleader;
	}
	public void setAuditleader(String auditleader) {
		this.auditleader = auditleader;
	}
	public String getAuditdepart() {
		return auditdepart;
	}
	public void setAuditdepart(String auditdepart) {
		this.auditdepart = auditdepart;
	}
	public String getAuditlevel() {
		return auditlevel;
	}
	public void setAuditlevel(String auditlevel) {
		this.auditlevel = auditlevel;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getNumauthor() {
		return numauthor;
	}
	public void setNumauthor(String numauthor) {
		this.numauthor = numauthor;
	}
	public String getFirstauthor() {
		return firstauthor;
	}
	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}
	public String getFirstauthorcode() {
		return firstauthorcode;
	}
	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}
	public String getSecauthor() {
		return secauthor;
	}
	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}
	public String getSecauthorarp() {
		return secauthorcode;
	}
	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}
	public String getThreeauthor() {
		return threeauthor;
	}
	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}
	public String getThreeauthorcode() {
		return threeauthorcode;
	}
	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}
	public String getFourauthor() {
		return fourauthor;
	}
	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}
	public String getFourauthorcode() {
		return fourauthorcode;
	}
	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}
	public String getFiveauthor() {
		return fiveauthor;
	}
	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}
	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}
	public String getOtherauthor() {
		return otherauthor;
	}
	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}
	public String getOtherauthorcode() {
		return otherauthorcode;
	}
	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}
	public String getUploadurl() {
		return uploadurl;
	}
	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}
	
	public String getExaminestatus() {
		return examinestatus;
	}
	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}
	public String getSecauthorcode() {
		return secauthorcode;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
		
	public Timestamp getSavetime() {
		return savetime;
	}
	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}
	public Timestamp getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(Timestamp examinetime) {
		this.examinetime = examinetime;
	}
	public Timestamp getIgnoretime() {
		return ignoretime;
	}
	public void setIgnoretime(Timestamp ignoretime) {
		this.ignoretime = ignoretime;
	}
	public String getAuditfilter() {
		return auditfilter;
	}
	public void setAuditfilter(String auditfilter) {
		this.auditfilter = auditfilter;
	}
	

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
	public String getOtherauthorscore() {
		return otherauthorscore;
	}
	public void setOtherauthorscore(String otherauthorscore) {
		this.otherauthorscore = otherauthorscore;
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
	
	public String getSubmitUser() {
		return submitUser;
	}
	
	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}
	
	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
}
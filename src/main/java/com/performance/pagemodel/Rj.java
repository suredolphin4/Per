package com.performance.pagemodel;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TDomain;
import com.performances.model.TLab;


/**
 * Rj entity. @author yp6124830@126.com
 */

public class Rj  extends BaseModel implements java.io.Serializable {
    // Fields
     private Integer rjid;
     private String firstauthor;
     private String fiveauthor;
     private String fourauthor;
     private Timestamp ignoretime;
     private String markid;
     private Integer numauthor;
     private String otherauthor;
     private Timestamp savetime;
     private String secauthor;
     private Timestamp submittime;
     private String threeauthor;
     private String title;
     private String year;
     private String	firstauthorcode;
     private String secauthorcode;
     private String	threeauthorcode;
     private String	fourauthorcode;
     private String	fiveauthorcode;
     private String	otherauthorcode;
     private String registernum;
     private String copyright;
     private String othercopyright;
     private String departorder;
	 private String auditdate;

    // 过滤条件
 	private String s_begin_year;
 	private String s_end_year;
 	private String s_lw_name;
 	private String s_lw_author;
	private String s_lw_authorcode;

 	// 论文附件上传下载相关
 	private String append; // 上传文件名 
	
    // 获取分数
    private String 	firstauthorscore;
    private String	secauthorscore;
    private String  threeauthorscore;
    private String	fourauthorscore;
    private String	fiveauthorscore;
    private String	otherauthorscore;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}


	public String getFirstauthorcode() {
		return firstauthorcode;
	}
	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
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
	public String getFourauthorcode() {
		return fourauthorcode;
	}
	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}
	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}
	public String getOtherauthorcode() {
		return otherauthorcode;
	}
	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}
	public String getRegisternum() {
		return registernum;
	}
	public void setRegisternum(String registernum) {
		this.registernum = registernum;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getOthercopyright() {
		return othercopyright;
	}
	public void setOthercopyright(String othercopyright) {
		this.othercopyright = othercopyright;
	}
	public String getDepartorder() {
		return departorder;
	}
	public void setDepartorder(String departorder) {
		this.departorder = departorder;
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
	public void setS_lw_name(String s_rj_name) {
		this.s_lw_name = s_rj_name;
	}
	public String getS_lw_author() {
		return s_lw_author;
	}
	public void setS_lw_author(String s_rj_author) {
		this.s_lw_author = s_rj_author;
	}
	public Integer getRjid() {
		return rjid;
	}
	public void setRjid(Integer rjid) {
		this.rjid = rjid;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
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
	public Timestamp getIgnoretime() {
		return ignoretime;
	}
	public void setIgnoretime(Timestamp ignoretime) {
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
	public Timestamp getSavetime() {
		return savetime;
	}
	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}
	public String getSecauthor() {
		return secauthor;
	}
	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}
	public Timestamp getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Timestamp submittime) {
		this.submittime = submittime;
	}
	public String getThreeauthor() {
		return threeauthor;
	}
	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
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
	public String getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}


	//绩效
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

}
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TDomain;
import com.performances.model.TLab;
/**
 * 地图
 * @author peng
 *
 */

public class Dt   extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer dtid;
     private String examinestatus;
     private Timestamp examinetime;
     private String firstauthor;
     private String	firstauthorcode;
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
     private String secauthorcode;
     private String	threeauthorcode;
     private String	fourauthorcode;
     private String	fiveauthorcode;
     private String	otherauthorcode;
     private String totalpage;
     private String maptype;
     private String firsteditor;
     private String firsteditorcode;
     private String seceditor;
     private String seceditorcode;
     private String thirdeditor;
     private String thirdeditorcode;
     private String othereditor;
     private String othereditorcode;
     private String firstsubeditor;
     private String firstsubeditorcode;
     private String secsubeditor;
     private String secsubeditorcode;
     private String thirdsubeditor;
     private String thirdsubeditorcode;
     private String othersubeditor;
     private String othersubeditorcode;
     
     private String	uploadurl;
     private String  auditopinion;

     private String append;
     
     private String submitUser;
     private java.sql.Timestamp submitTime;
     
  // 过滤条件
 	private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
 	private String s_begin_year;
 	private String s_end_year;
 	private String s_lw_name;
 	private String s_lw_author;
 	private String s_which_lab;
 	private String s_which_domain;
	private String s_lw_authorcode;
 	
	// 获取分数
	private String 	firsteditorscore;
    private String	seceditorscore;
    private String  threeeditorscore;
    private String	othereditorscore;
	private String 	firstsubeditorscore;
    private String	secsubeditorscore;
    private String  threesubeditorscore;
    private String	othersubeditorscore;
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


	public Integer getDtid() {
		return dtid;
	}
	public void setDtid(Integer dtid) {
		this.dtid = dtid;
	}
	public String getExaminestatus() {
		return examinestatus;
	}
	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}
	public Timestamp getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(Timestamp examinetime) {
		this.examinetime = examinetime;
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
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public String getMaptype() {
		return maptype;
	}
	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}
	public String getFirsteditor() {
		return firsteditor;
	}
	public void setFirsteditor(String firsteditor) {
		this.firsteditor = firsteditor;
	}
	public String getFirsteditorcode() {
		return firsteditorcode;
	}
	public void setFirsteditorcode(String firsteditorcode) {
		this.firsteditorcode = firsteditorcode;
	}
	public String getSeceditor() {
		return seceditor;
	}
	public void setSeceditor(String seceditor) {
		this.seceditor = seceditor;
	}
	public String getSeceditorcode() {
		return seceditorcode;
	}
	public void setSeceditorcode(String seceditorcode) {
		this.seceditorcode = seceditorcode;
	}
	public String getThirdeditor() {
		return thirdeditor;
	}
	public void setThirdeditor(String thirdeditor) {
		this.thirdeditor = thirdeditor;
	}
	public String getThirdeditorcode() {
		return thirdeditorcode;
	}
	public void setThirdeditorcode(String thirdeditorcode) {
		this.thirdeditorcode = thirdeditorcode;
	}
	public String getOthereditor() {
		return othereditor;
	}
	public void setOthereditor(String othereditor) {
		this.othereditor = othereditor;
	}
	public String getOthereditorcode() {
		return othereditorcode;
	}
	public void setOthereditorcode(String othereditorcode) {
		this.othereditorcode = othereditorcode;
	}
	public String getFirstsubeditor() {
		return firstsubeditor;
	}
	public void setFirstsubeditor(String firstsubeditor) {
		this.firstsubeditor = firstsubeditor;
	}
	public String getFirstsubeditorcode() {
		return firstsubeditorcode;
	}
	public void setFirstsubeditorcode(String firstsubeditorcode) {
		this.firstsubeditorcode = firstsubeditorcode;
	}
	public String getSecsubeditor() {
		return secsubeditor;
	}
	public void setSecsubeditor(String secsubeditor) {
		this.secsubeditor = secsubeditor;
	}
	public String getSecsubeditorcode() {
		return secsubeditorcode;
	}
	public void setSecsubeditorcode(String secsubeditorcode) {
		this.secsubeditorcode = secsubeditorcode;
	}
	public String getThirdsubeditor() {
		return thirdsubeditor;
	}
	public void setThirdsubeditor(String thirdsubeditor) {
		this.thirdsubeditor = thirdsubeditor;
	}
	public String getThirdsubeditorcode() {
		return thirdsubeditorcode;
	}
	public void setThirdsubeditorcode(String thirdsubeditorcode) {
		this.thirdsubeditorcode = thirdsubeditorcode;
	}
	public String getOthersubeditor() {
		return othersubeditor;
	}
	public void setOthersubeditor(String othersubeditor) {
		this.othersubeditor = othersubeditor;
	}
	public String getOthersubeditorcode() {
		return othersubeditorcode;
	}
	public void setOthersubeditorcode(String othersubeditorcode) {
		this.othersubeditorcode = othersubeditorcode;
	}
	public String getUploadurl() {
		return uploadurl;
	}
	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}
	public String getAuditopinion() {
		return auditopinion;
	}
	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
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
	public void setS_lw_name(String s_dt_name) {
		this.s_lw_name = s_dt_name;
	}
	public String getS_lw_author() {
		return s_lw_author;
	}
	public void setS_lw_author(String s_dt_author) {
		this.s_lw_author = s_dt_author;
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
	public String getFirsteditorscore() {
		return firsteditorscore;
	}
	public void setFirsteditorscore(String firsteditorscore) {
		this.firsteditorscore = firsteditorscore;
	}
	public String getSeceditorscore() {
		return seceditorscore;
	}
	public void setSeceditorscore(String seceditorscore) {
		this.seceditorscore = seceditorscore;
	}
	public String getThreeeditorscore() {
		return threeeditorscore;
	}
	public void setThreeeditorscore(String threeeditorscore) {
		this.threeeditorscore = threeeditorscore;
	}
	public String getOthereditorscore() {
		return othereditorscore;
	}
	public void setOthereditorscore(String othereditorscore) {
		this.othereditorscore = othereditorscore;
	}
	
	public String getFirstsubeditorscore() {
		return firstsubeditorscore;
	}
	public void setFirstsubeditorscore(String firstsubeditorscore) {
		this.firstsubeditorscore = firstsubeditorscore;
	}
	public String getSecsubeditorscore() {
		return secsubeditorscore;
	}
	public void setSecsubeditorscore(String secsubeditorscore) {
		this.secsubeditorscore = secsubeditorscore;
	}
	public String getThreesubeditorscore() {
		return threesubeditorscore;
	}
	public void setThreesubeditorscore(String threesubeditorscore) {
		this.threesubeditorscore = threesubeditorscore;
	}
	public String getOthersubeditorscore() {
		return othersubeditorscore;
	}
	public void setOthersubeditorscore(String othersubeditorscore) {
		this.othersubeditorscore = othersubeditorscore;
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
}
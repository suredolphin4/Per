package com.performance.pagemodel;

/**
 * 奖励
 * @author zheng
 *
 */
public class Award extends BaseModel implements java.io.Serializable {
	private Integer id;
	private String year;
	private String awardProjectName;
	private String awardName;
	private String unit;
	private String geoRank;
	private String department;
	private String level;
	private String rank;
	private Integer numOfAwardee;
	private String awardeeOne;
	private String awardeeCodeOne;
	private String awardeeTwo;
	private String awardeeCodeTwo;
	private String awardeeThree;
	private String awardeeCodeThree;
	private String awardeeFour;
	private String awardeeCodeFour;
	private String awardeeFive;
	private String awardeeCodeFive;
	private String awardeeSix;
	private String awardeeCodeSix;
	private String awardeeSeven;
	private String awardeeCodeSeven;
	private String awardeeEight;
	private String awardeeCodeEight;
	private String awardeeNine;
	private String awardeeCodeNine;
	private String awardeeTen;
	private String awardeeCodeTen;
	private String awardeeOther;
	private String awardeeCodeOther;
	private String append;
	private String auditStatus;
	private String auditOpinion;
	private java.util.Date savetime;
	private String submitUser;
	private java.sql.Timestamp submitTime;
	
	// 过滤条件
	private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
	private String s_begin_year;
	private String s_end_year;
	private String s_lw_name;
	private String s_lw_author;
	private String s_lw_authorcode;
	private String s_which_lab;
	private String s_which_domain;
	
	//分数
	private String awardee1score;
	private String awardee2score;
	private String awardee3score;
	private String awardee4score;
	private String awardee5score;
	private String awardee6score;
	private String awardee7score;
	private String awardee8score;
	private String awardee9score;
	private String awardee10score;
	private String otherawardeescore;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAwardProjectName() {
		return awardProjectName;
	}
	public void setAwardProjectName(String awardProjectName) {
		this.awardProjectName = awardProjectName;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGeoRank() {
		return geoRank;
	}
	public void setGeoRank(String geoRank) {
		this.geoRank = geoRank;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getNumOfAwardee() {
		return numOfAwardee;
	}
	public void setNumOfAwardee(Integer numOfAwardee) {
		this.numOfAwardee = numOfAwardee;
	}
	public String getAwardeeOne() {
		return awardeeOne;
	}
	public void setAwardeeOne(String awardeeOne) {
		this.awardeeOne = awardeeOne;
	}
	public String getAwardeeCodeOne() {
		return awardeeCodeOne;
	}
	public void setAwardeeCodeOne(String awardeeCodeOne) {
		this.awardeeCodeOne = awardeeCodeOne;
	}
	public String getAwardeeTwo() {
		return awardeeTwo;
	}
	public void setAwardeeTwo(String awardeeTwo) {
		this.awardeeTwo = awardeeTwo;
	}
	public String getAwardeeCodeTwo() {
		return awardeeCodeTwo;
	}
	public void setAwardeeCodeTwo(String awardeeCodeTwo) {
		this.awardeeCodeTwo = awardeeCodeTwo;
	}
	public String getAwardeeThree() {
		return awardeeThree;
	}
	public void setAwardeeThree(String awardeeThree) {
		this.awardeeThree = awardeeThree;
	}
	public String getAwardeeCodeThree() {
		return awardeeCodeThree;
	}
	public void setAwardeeCodeThree(String awardeeCodeThree) {
		this.awardeeCodeThree = awardeeCodeThree;
	}
	public String getAwardeeFour() {
		return awardeeFour;
	}
	public void setAwardeeFour(String awardeeFour) {
		this.awardeeFour = awardeeFour;
	}
	public String getAwardeeCodeFour() {
		return awardeeCodeFour;
	}
	public void setAwardeeCodeFour(String awardeeCodeFour) {
		this.awardeeCodeFour = awardeeCodeFour;
	}
	public String getAwardeeFive() {
		return awardeeFive;
	}
	public void setAwardeeFive(String awardeeFive) {
		this.awardeeFive = awardeeFive;
	}
	public String getAwardeeCodeFive() {
		return awardeeCodeFive;
	}
	public void setAwardeeCodeFive(String awardeeCodeFive) {
		this.awardeeCodeFive = awardeeCodeFive;
	}
	public String getAwardeeSix() {
		return awardeeSix;
	}
	public void setAwardeeSix(String awardeeSix) {
		this.awardeeSix = awardeeSix;
	}
	public String getAwardeeCodeSix() {
		return awardeeCodeSix;
	}
	public void setAwardeeCodeSix(String awardeeCodeSix) {
		this.awardeeCodeSix = awardeeCodeSix;
	}
	public String getAwardeeSeven() {
		return awardeeSeven;
	}
	public void setAwardeeSeven(String awardeeSeven) {
		this.awardeeSeven = awardeeSeven;
	}
	public String getAwardeeCodeSeven() {
		return awardeeCodeSeven;
	}
	public void setAwardeeCodeSeven(String awardeeCodeSeven) {
		this.awardeeCodeSeven = awardeeCodeSeven;
	}
	public String getAwardeeEight() {
		return awardeeEight;
	}
	public void setAwardeeEight(String awardeeEight) {
		this.awardeeEight = awardeeEight;
	}
	public String getAwardeeCodeEight() {
		return awardeeCodeEight;
	}
	public void setAwardeeCodeEight(String awardeeCodeEight) {
		this.awardeeCodeEight = awardeeCodeEight;
	}
	public String getAwardeeNine() {
		return awardeeNine;
	}
	public void setAwardeeNine(String awardeeNine) {
		this.awardeeNine = awardeeNine;
	}
	public String getAwardeeCodeNine() {
		return awardeeCodeNine;
	}
	public void setAwardeeCodeNine(String awardeeCodeNine) {
		this.awardeeCodeNine = awardeeCodeNine;
	}
	public String getAwardeeTen() {
		return awardeeTen;
	}
	public void setAwardeeTen(String awardeeTen) {
		this.awardeeTen = awardeeTen;
	}
	public String getAwardeeCodeTen() {
		return awardeeCodeTen;
	}
	public void setAwardeeCodeTen(String awardeeCodeTen) {
		this.awardeeCodeTen = awardeeCodeTen;
	}
	public String getAwardeeOther() {
		return awardeeOther;
	}
	public void setAwardeeOther(String awardeeOther) {
		this.awardeeOther = awardeeOther;
	}
	public String getAwardeeCodeOther() {
		return awardeeCodeOther;
	}
	public void setAwardeeCodeOther(String awardeeCodeOther) {
		this.awardeeCodeOther = awardeeCodeOther;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public java.util.Date getSavetime() {
		return savetime;
	}
	public void setSavetime(java.util.Date savetime) {
		this.savetime = savetime;
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
	
	//分数
	public String getAwardee1score() {
		return awardee1score;
	}
	public void setAwardee1score(String awardee1score) {
		this.awardee1score = awardee1score;
	}
	public String getAwardee2score() {
		return awardee2score;
	}
	public void setAwardee2score(String awardee2score) {
		this.awardee2score = awardee2score;
	}
	public String getAwardee3score() {
		return awardee3score;
	}
	public void setAwardee3score(String awardee3score) {
		this.awardee3score = awardee3score;
	}
	public String getAwardee4score() {
		return awardee4score;
	}
	public void setAwardee4score(String awardee4score) {
		this.awardee4score = awardee4score;
	}
	public String getAwardee5score() {
		return awardee5score;
	}
	public void setAwardee5score(String awardee5score) {
		this.awardee5score = awardee5score;
	}
	public String getAwardee6score() {
		return awardee6score;
	}
	public void setAwardee6score(String awardee6score) {
		this.awardee6score = awardee6score;
	}
	public String getAwardee7score() {
		return awardee7score;
	}
	public void setAwardee7score(String awardee7score) {
		this.awardee7score = awardee7score;
	}
	public String getAwardee8score() {
		return awardee8score;
	}
	public void setAwardee8score(String awardee8score) {
		this.awardee8score = awardee8score;
	}
	public String getAwardee9score() {
		return awardee9score;
	}
	public void setAwardee9score(String awardee9score) {
		this.awardee9score = awardee9score;
	}
	public String getAwardee10score() {
		return awardee10score;
	}
	public void setAwardee10score(String awardee10score) {
		this.awardee10score = awardee10score;
	}
	public String getOtherawardeescore() {
		return otherawardeescore;
	}
	public void setOtherawardeescore(String otherawardeescore) {
		this.otherawardeescore = otherawardeescore;
	}
}

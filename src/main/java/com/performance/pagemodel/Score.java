package com.performance.pagemodel;
// default package

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.performances.model.TDomain;
import com.performances.model.TLab;
/**
 * 规划
 * @author peng
 *
 */

public class Score   extends BaseModel   implements java.io.Serializable {

    // Fields    
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String domain;
	private String usercode;
	private String name;
	private double sciThesisScore = 0;
	private double inlandThesisScore = 0;
	private double otherThesisScore = 0;
	//private double thesisScore = 0;
	private double reportScore = 0;   //除了咨询报告类型为“两办采纳”的累计
	//新增咨询报告类型为“两办采纳”的个人累计不超过20分限制20171023  reportLbcnScore
	private double reportLbcnScore = 0;

	private double awardScore = 0;
	private double writingScore = 0;
	private double planScore = 0;
	private double thirdAssessScore = 0;
	private double standardScore = 0;
	private double mapScore = 0;
	private double softwareScore = 0;
	private double patentScore = 0;
	private double projectScore = 0;
	private double lessonScore = 0;
	private double graduteScore = 0;
	private double publishScore = 0;
	private double achievetransScore = 0;
	private String total = "0";
	private String level;
	
	// 过滤条件
	private String s_begin_year;
	private String s_end_year;
	private String s_lw_name;
	private String s_lw_author;
	private String s_lw_authorcode;
	private List<String> s_which_type;
	private String s_which_domain;


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
	
	public String getDomain(){
		return this.domain;
	}
	public void setDomain(String domain){
		this.domain = domain;
	}
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getSciThesisScore() {
		return sciThesisScore;
	}
	public void setSciThesisScore(Double sciThesisScore) {
		this.sciThesisScore = sciThesisScore;
	}
	
	public Double getInlandThesisScore() {
		return inlandThesisScore;
	}
	public void setInlandThesisScore(Double inlandThesisScore) {
		this.inlandThesisScore = inlandThesisScore;
	}
	
	public Double getOtherThesisScore() {
		return otherThesisScore;
	}
	public void setOtherThesisScore(Double otherThesisScore) {
		this.otherThesisScore = otherThesisScore;
	}

	public Double getReportLbcnScore() {
		return reportLbcnScore;
	}
	public void setReportLbcnScore(Double reportLbcnScore) {
		this.reportLbcnScore = reportLbcnScore;
	}


	public Double getReportScore() {
		return reportScore;
	}
	public void setReportScore(Double reportScore) {
		this.reportScore = reportScore;
	}
	
	public Double getAwardScore() {
		return awardScore;
	}
	public void setAwardScore(Double awardScore) {
		this.awardScore = awardScore;
	}

	public Double getWritingScore() {
		return writingScore;
	}
	public void setWritingScore(Double writingScore) {
		this.writingScore = writingScore;
	}
	
	public Double getPatentScore() {
		return patentScore;
	}
	public void setPatentScore(Double patentScore) {
		this.patentScore = patentScore;
	}
	
	public Double getStandardScore() {
		return standardScore;
	}
	public void setStandardScore(Double standardScore) {
		this.standardScore = standardScore;
	}
	
	public Double getMapScore() {
		return mapScore;
	}
	public void setMapScore(Double mapScore) {
		this.mapScore = mapScore;
	}
	
	public Double getSoftwareScore() {
		return softwareScore;
	}
	public void setSoftwareScore(Double softwareScore) {
		this.softwareScore = softwareScore;
	}
	
	public Double getProjectScore() {
		return projectScore;
	}
	public void setProjectScore(Double projectScore) {
		this.projectScore = projectScore;
	}
	
	public Double getPlanScore() {
		return planScore;
	}
	public void setPlanScore(Double planScore) {
		this.planScore = planScore;
	}

	public Double getThirdAssessScore() {
		return thirdAssessScore;
	}
	public void setThirdAssessScore(Double thirdAssessScore) {
		this.thirdAssessScore = thirdAssessScore;
	}


	public Double getLessonScore() {
		return lessonScore;
	}
	public void setLessonScore(Double lessonScore) {
		this.lessonScore = lessonScore;
	}

	public Double getPublishScore() {
		return publishScore;
	}
	public void setPublishScore(Double publishScore) {
		this.publishScore = publishScore;
	}

	public Double getAchievetransScore() {
		return achievetransScore;
	}
	public void setAchievetransScore(Double achievetransScore) {
		this.achievetransScore = achievetransScore;
	}
	
	public Double getGraduteScore() {
		return graduteScore;
	}
	public void setGraduteScore(Double graduteScore) {
		this.graduteScore = graduteScore;
	}
	
	public String getTotal() {
		DecimalFormat df = new DecimalFormat("####0.00");
		//判断咨询报告两办采纳类型的累计分数，超过20分按20分计算
		/*
		if(this.reportLbcnScore > 20){
			double diss = this.reportLbcnScore-20;
			this.reportScore -= diss;
		}
		//判断数据出版的累计分数，超过5分按5分计算
		if(this.publishScore > 5){
			this.publishScore = 5;
		}
		*/
		double alltotal = this.sciThesisScore + this.inlandThesisScore + this.otherThesisScore + this.reportScore + this.awardScore + this.writingScore +
				this.planScore + this.thirdAssessScore + this.standardScore + this.mapScore + softwareScore + this.patentScore + this.projectScore + this.lessonScore + this.publishScore+this.achievetransScore+
				this.graduteScore;
		return df.format(alltotal);
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getLevel(){
		return this.level;
	}
	public void setLevel(String level){
		this.level = level;
	}
	
	//1.查询条件
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

	public List<String> getS_which_type() {
		return s_which_type;
	}

	public void setS_which_type(List<String> s_which_type) {
		this.s_which_type = s_which_type;
	}

	public String getS_which_domain() {
		return s_which_domain;
	}

	public void setS_which_domain(String s_which_domain) {
		this.s_which_domain = s_which_domain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
package com.performances.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 奖励
 * @author zheng
 *
 */
@Entity
@Table(name = "t_award", catalog = "per")
public class TAward {
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
	
	@GenericGenerator(name="generator", strategy="increment")
	@Id
	@GeneratedValue(generator="generator")
	@Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name = "awardprojectname")
	public String getAwardProjectName() {
		return awardProjectName;
	}
	public void setAwardProjectName(String awardPojectName) {
		this.awardProjectName = awardPojectName;
	}
	
	@Column(name = "awardname")
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name = "georank")
	public String getGeoRank() {
		return geoRank;
	}
	public void setGeoRank(String geoRank) {
		this.geoRank = geoRank;
	}
	
	@Column(name = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name = "level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name = "rank")
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Column(name = "numofawardee")
	public Integer getNumOfAwardee() {
		return numOfAwardee;
	}
	public void setNumOfAwardee(Integer numOfAwardee) {
		this.numOfAwardee = numOfAwardee;
	}
	
	@Column(name = "awardee1")
	public String getAwardeeOne() {
		return awardeeOne;
	}
	public void setAwardeeOne(String awardeeOne) {
		this.awardeeOne = awardeeOne;
	}
	
	@Column(name = "awardeecode1")
	public String getAwardeeCodeOne() {
		return awardeeCodeOne;
	}
	public void setAwardeeCodeOne(String awardeeCodeOne) {
		this.awardeeCodeOne = awardeeCodeOne;
	}
	
	@Column(name = "awardee2")
	public String getAwardeeTwo() {
		return awardeeTwo;
	}
	public void setAwardeeTwo(String awardeeTwo) {
		this.awardeeTwo = awardeeTwo;
	}
	
	@Column(name = "awardeecode2")
	public String getAwardeeCodeTwo() {
		return awardeeCodeTwo;
	}
	public void setAwardeeCodeTwo(String awardeeCodeTwo) {
		this.awardeeCodeTwo = awardeeCodeTwo;
	}
	
	@Column(name = "awardee3")
	public String getAwardeeThree() {
		return awardeeThree;
	}
	public void setAwardeeThree(String awardeeThree) {
		this.awardeeThree = awardeeThree;
	}
	
	@Column(name = "awardeecode3")
	public String getAwardeeCodeThree() {
		return awardeeCodeThree;
	}
	public void setAwardeeCodeThree(String awardeeCodeThree) {
		this.awardeeCodeThree = awardeeCodeThree;
	}
	
	@Column(name = "awardee4")
	public String getAwardeeFour() {
		return awardeeFour;
	}
	public void setAwardeeFour(String awardeeFour) {
		this.awardeeFour = awardeeFour;
	}
	
	@Column(name = "awardeecode4")
	public String getAwardeeCodeFour() {
		return awardeeCodeFour;
	}
	public void setAwardeeCodeFour(String awardeeCodeFour) {
		this.awardeeCodeFour = awardeeCodeFour;
	}
	
	@Column(name = "awardee5")
	public String getAwardeeFive() {
		return awardeeFive;
	}
	public void setAwardeeFive(String awardeeFive) {
		this.awardeeFive = awardeeFive;
	}
	
	@Column(name = "awardeecode5")
	public String getAwardeeCodeFive() {
		return awardeeCodeFive;
	}
	public void setAwardeeCodeFive(String awardeeCodeFive) {
		this.awardeeCodeFive = awardeeCodeFive;
	}
	
	@Column(name = "awardee6")
	public String getAwardeeSix() {
		return awardeeSix;
	}
	public void setAwardeeSix(String awardeeSix) {
		this.awardeeSix = awardeeSix;
	}
	
	@Column(name = "awardeecode6")
	public String getAwardeeCodeSix() {
		return awardeeCodeSix;
	}
	public void setAwardeeCodeSix(String awardeeCodeSix) {
		this.awardeeCodeSix = awardeeCodeSix;
	}
	
	@Column(name = "awardee7")
	public String getAwardeeSeven() {
		return awardeeSeven;
	}
	public void setAwardeeSeven(String awardeeSeven) {
		this.awardeeSeven = awardeeSeven;
	}
	
	@Column(name = "awardeecode7")
	public String getAwardeeCodeSeven() {
		return awardeeCodeSeven;
	}
	public void setAwardeeCodeSeven(String awardeeCodeSeven) {
		this.awardeeCodeSeven = awardeeCodeSeven;
	}
	
	@Column(name = "awardee8")
	public String getAwardeeEight() {
		return awardeeEight;
	}
	public void setAwardeeEight(String awardeeEight) {
		this.awardeeEight = awardeeEight;
	}
	
	@Column(name = "awardeecode8")
	public String getAwardeeCodeEight() {
		return awardeeCodeEight;
	}
	public void setAwardeeCodeEight(String awardeeCodeEight) {
		this.awardeeCodeEight = awardeeCodeEight;
	}
	
	@Column(name = "awardee9")
	public String getAwardeeNine() {
		return awardeeNine;
	}
	public void setAwardeeNine(String awardeeNine) {
		this.awardeeNine = awardeeNine;
	}
	
	@Column(name = "awardeecode9")
	public String getAwardeeCodeNine() {
		return awardeeCodeNine;
	}
	public void setAwardeeCodeNine(String awardeeCodeNine) {
		this.awardeeCodeNine = awardeeCodeNine;
	}
	
	@Column(name = "awardee10")
	public String getAwardeeTen() {
		return awardeeTen;
	}
	public void setAwardeeTen(String awardeeTen) {
		this.awardeeTen = awardeeTen;
	}
	
	@Column(name = "awardeecode10")
	public String getAwardeeCodeTen() {
		return awardeeCodeTen;
	}
	public void setAwardeeCodeTen(String awardeeCodeTen) {
		this.awardeeCodeTen = awardeeCodeTen;
	}
	
	@Column(name = "awardeeother")
	public String getAwardeeOther() {
		return awardeeOther;
	}
	public void setAwardeeOther(String awardeeOther) {
		this.awardeeOther = awardeeOther;
	}
	
	@Column(name = "awardeecodeother")
	public String getAwardeeCodeOther() {
		return awardeeCodeOther;
	}
	public void setAwardeeCodeOther(String awardeeCodeOther) {
		this.awardeeCodeOther = awardeeCodeOther;
	}
	
	@Column(name = "append")
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	
	@Column(name = "auditstatus")
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Column(name = "auditopinion")
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	@Column(name = "savetime")
	public java.util.Date getSavetime() {
		return savetime;
	}
	public void setSavetime(java.util.Date savetime) {
		this.savetime = savetime;
	}
	
	@Column(name = "submituser")
	public String getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}
	
	@Column(name = "submittime")
	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
	private Collection<TAwardScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TAwardScore> getScore(){
		return score;
	}
	public void setScore(Collection<TAwardScore> score){
		this.score = score;
	}
}

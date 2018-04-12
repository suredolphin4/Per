package com.performances.model;
// default package

import java.sql.Date;
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


/**
 * 项目
 * TXm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_xm"
    ,catalog="per"
)

public class TXm  implements java.io.Serializable {

		// Fields    
		private Integer id;
		private String projectid;
		private String leader;
		private String name;
		private String provider;
		private String type;
		private String collegeCode;
		private String beginTime;
		private String endTime;
		private String subCode;
		private String subLeader;
		private String subName;
		private String level;
		private String subProjectStay;
		private String taskCode;
		private String detailType;
		private String roleCode;
		private String score;
		private String leaderCode;
		private String subLeaderCode;
		private Timestamp savetime;
		@Column(name = "savetime", length = 19)
	    public Timestamp getSavetime() {
			return savetime;
		}

		public void setSavetime(Timestamp savetime) {
			this.savetime = savetime;
		}

		// Constructors
	    public TXm(){}
	    
	    /** full constructor */
	    public TXm(Integer id, String projectid, String leader, String name, String provider, String type, String collegeCode,
	    		String begin, String end, String subCode, String subLeader, String subName, String level, String subProjectStay,
	    			String taskCode, String detailType,Timestamp savetime, String roleCode, String score, String leaderCode, String subLeaderCode) {
	    	this.id = id;
	    	this.projectid = projectid;
	    	this.leader = leader;
	    	this.name = name;
	    	this.savetime=savetime;
	    	this.provider = provider;
	    	this.type = type;
	    	this.collegeCode = collegeCode;
	    	this.beginTime = begin;
	    	this.endTime = end;
	    	this.subCode = subCode;
	    	this.subLeader = subLeader;
	    	this.subName = subName;
	    	this.level = level;
	    	this.subProjectStay = subProjectStay;
	    	this.taskCode = taskCode;
	    	this.detailType = detailType;
	    	this.roleCode = roleCode;
	    	this.score = score;
	    	this.leaderCode = leaderCode;
	    	this.subLeaderCode = subLeaderCode;

	    }

	   
	    // Property accessors
	    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
	    @Column(name="id", unique=true, nullable=false)
	    public Integer getId() {
	        return this.id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
	    @Column(name="projectid", length=32)
	    public String getProjectid() {
	        return this.projectid;
	    }
	    public void setProjectid(String projectid) {
	        this.projectid = projectid;
	    }
	    
	    @Column(name="leader", length=32)
	    public String getLeader() {
	        return this.leader;
	    }
	    public void setLeader(String leader) {
	        this.leader = leader;
	    }
	    
	    @Column(name="name", length=1024)
	    public String getName() {
	        return this.name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    @Column(name="provider", length=128)
	    public String getProvider() {
	        return this.provider;
	    }
	    public void setProvider(String provider) {
	        this.provider = provider;
	    }
	    
	    @Column(name="type", length=128)
	    public String getType() {
	        return this.type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }
	    
	    @Column(name="collegeCode", length=64)
	    public String getCollegeCode() {
	        return this.collegeCode;
	    }
	    public void setCollegeCode(String collegeCode) {
	        this.collegeCode = collegeCode;
	    }
	    
	    @Column(name="begin", length=32)
	    public String getBeginTime() {
	        return this.beginTime;
	    }
	    public void setBeginTime(String begin) {
	        this.beginTime = begin;
	    }
	    
	    @Column(name="end", length=32)
	    public String getEndTime() {
	        return this.endTime;
	    }
	    public void setEndTime(String end) {
	        this.endTime = end;
	    }

	    @Column(name="subCode", length=32)
	    public String getSubCode() {
	        return this.subCode;
	    }
	    public void setSubCode(String subCode) {
	        this.subCode = subCode;
	    }
	    
	    @Column(name="subLeader", length=32)
	    public String getSubLeader() {
	        return this.subLeader;
	    }
	    public void setSubLeader(String subLeader) {
	        this.subLeader = subLeader;
	    }
	    
	    @Column(name="subName", length=256)
	    public String getSubName() {
	        return this.subName;
	    }
	    public void setSubName(String subName) {
	        this.subName = subName;
	    }
	    
	    @Column(name="level", length=32)
	    public String getLevel() {
	        return this.level;
	    }
	    public void setLevel(String level) {
	        this.level = level;
	    }
	    
	    @Column(name="subProjectStay", length=20)
	    public String getSubProjectStay() {
	        return this.subProjectStay;
	    }
	    public void setSubProjectStay(String subProjectStay) {
	        this.subProjectStay = subProjectStay;
	    }
	    
	    @Column(name="taskCode", length=2)
	    public String getTaskCode() {
	        return this.taskCode;
	    }
	    public void setTaskCode(String taskCode) {
	        this.taskCode = taskCode;
	    }
	    
	    @Column(name="detailType", length=128)
	    public String getDetailType() {
	        return this.detailType;
	    }
	    public void setDetailType(String detailType) {
	        this.detailType = detailType;
	    }
	    
	    @Column(name="roleCode", length=16)
	    public String getRoleCode() {
	        return this.roleCode;
	    }
	    public void setRoleCode(String roleCode) {
	        this.roleCode = roleCode;
	    }
	    
	    @Column(name="score", length=64)
	    public String getScore() {
	        return this.score;
	    }
	    public void setScore(String score) {
	        this.score = score;
	    }
	    
	    @Column(name="leaderCode", length=64)
	    public String getLeaderCode() {
	        return this.leaderCode;
	    }
	    public void setLeaderCode(String leaderCode) {
	        this.leaderCode = leaderCode;
	    }
	    
	    @Column(name="subLeaderCode", length=64)
	    public String getSubLeaderCode() {
	        return this.subLeaderCode;
	    }
	    public void setSubLeaderCode(String subLeaderCode) {
	        this.subLeaderCode = subLeaderCode;
	    }
}
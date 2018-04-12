package com.performance.pagemodel;
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

import com.performances.model.TDomain;
import com.performances.model.TLab;


/**
 * 项目
 * Xm entity. @author yp6124830@126.com
 */

public class Xm  extends BaseModel  implements java.io.Serializable {
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

		     // 过滤条件
		    private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
			private String s_begin_year;
			private String s_end_year;
			private String s_lw_name;
			private String s_xm_projectID;
			private String s_lw_author;
			private String s_which_lab;
			private String s_which_domain;	
		    private Timestamp savetime;
			private String s_lw_authorcode;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}

	public String getS_xm_projectID(){
		return s_xm_projectID;
	}

	public void setS_xm_projectID(String s_xm_projectID){
		this.s_xm_projectID =s_xm_projectID;
	}

	public Timestamp getSavetime() {
				return savetime;
			}
			public void setSavetime(Timestamp savetime) {
				this.savetime = savetime;
			}
			public Integer getId() {
		        return this.id;
		    }
		    public void setId(Integer id) {
		        this.id = id;
		    }
		    

		    public String getProjectid() {
		        return this.projectid;
		    }
		    public void setProjectid(String projectid) {
		        this.projectid = projectid;
		    }
		    

		    public String getLeader() {
		        return this.leader;
		    }
		    public void setLeader(String leader) {
		        this.leader = leader;
		    }
		    

		    public String getName() {
		        return this.name;
		    }
		    public void setName(String name) {
		        this.name = name;
		    }
		    

		    public String getProvider() {
		        return this.provider;
		    }
		    public void setProvider(String provider) {
		        this.provider = provider;
		    }
		    

		    public String getType() {
		        return this.type;
		    }
		    public void setType(String type) {
		        this.type = type;
		    }
		    

		    public String getCollegeCode() {
		        return this.collegeCode;
		    }
		    public void setCollegeCode(String collegeCode) {
		        this.collegeCode = collegeCode;
		    }
		    

		    public String getBeginTime() {
		        return this.beginTime;
		    }
		    public void setBeginTime(String begin) {
		        this.beginTime = begin;
		    }
		    

		    public String getEndTime() {
		        return this.endTime;
		    }
		    public void setEndTime(String end) {
		        this.endTime = end;
		    }


		    public String getSubCode() {
		        return this.subCode;
		    }
		    public void setSubCode(String subCode) {
		        this.subCode = subCode;
		    }
		    

		    public String getSubLeader() {
		        return this.subLeader;
		    }
		    public void setSubLeader(String subLeader) {
		        this.subLeader = subLeader;
		    }
		    

		    public String getSubName() {
		        return this.subName;
		    }
		    public void setSubName(String subName) {
		        this.subName = subName;
		    }
		    

		    public String getLevel() {
		        return this.level;
		    }
		    public void setLevel(String level) {
		        this.level = level;
		    }
		    

		    public String getSubProjectStay() {
		        return this.subProjectStay;
		    }
		    public void setSubProjectStay(String subProjectStay) {
		        this.subProjectStay = subProjectStay;
		    }
		    

		    public String getTaskCode() {
		        return this.taskCode;
		    }
		    public void setTaskCode(String taskCode) {
		        this.taskCode = taskCode;
		    }
		    

		    public String getDetailType() {
		        return this.detailType;
		    }
		    public void setDetailType(String detailType) {
		        this.detailType = detailType;
		    }
		    

		    public String getRoleCode() {
		        return this.roleCode;
		    }
		    public void setRoleCode(String roleCode) {
		        this.roleCode = roleCode;
		    }
		    

		    public String getScore() {
		        return this.score;
		    }
		    public void setScore(String score) {
		        this.score = score;
		    }
		    

		    public String getLeaderCode() {
		        return this.leaderCode;
		    }
		    public void setLeaderCode(String leaderCode) {
		        this.leaderCode = leaderCode;
		    }
		    
		    
		    public String getSubLeaderCode() {
		        return this.subLeaderCode;
		    }
		    public void setSubLeaderCode(String subLeaderCode) {
		        this.subLeaderCode = subLeaderCode;
		    }
		    
		    // 过滤条件
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
}
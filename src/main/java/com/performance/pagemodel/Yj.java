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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TDomain;
import com.performances.model.TLab;

public class Yj   extends BaseModel implements java.io.Serializable {

     private Integer yjid;
     private String year;
     private String award;
     private String student;
     private String teacher;
     private Integer score;
     private String studentCode;
     private String teacherCode;
	  private Timestamp savetime;

     
     // 过滤条件
    private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
	private String s_begin_year;
	private String s_end_year;
	private String s_lw_name;
	private String s_lw_author;
	private String s_which_lab;
	private String s_which_domain;
	private String s_lw_authorcode;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}



	public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	public Integer getYjid(){
    	 return this.yjid;
     }
     
     public void setYjid(Integer yjid){
    	 this.yjid = yjid;
     }
     
     public String getYear() {
    	 return year;
     }
     
     public void setYear(String year) {
		this.year = year;
     }
     
     public String getAward() {
    	 return award;
     }
     
     public void setAward(String award) {
		this.award = award;
     }
     
     public String getStudent() {
    	 return student;
     }
     
     public void setStudent(String student) {
		this.student = student;
     }
     
     public String getTeacher() {
    	 return teacher;
     }
     
     public void setTeacher(String teacher) {
		this.teacher = teacher;
     }
     
     public Integer getScore() {
    	 return score;
     }
     
     public void setScore(Integer score) {
		this.score = score;
     }
     
     public String getStudentCode() {
    	 return studentCode;
     }
     
     public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
     }
     
     public String getTeacherCode() {
    	 return teacherCode;
     }
     
     public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
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
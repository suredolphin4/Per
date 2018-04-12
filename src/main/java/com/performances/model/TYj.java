package com.performances.model;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 研究生获奖 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_yj"
    ,catalog="per"
)

public class TYj  implements java.io.Serializable {

    // Fields    
	private Integer yjid;
    private String year;
    private String award;
    private String student;
    private String teacher;
    private Integer score;
    private String studentCode;
    private String teacherCode;
    private Timestamp savetime;
	@Column(name = "savetime", length = 19)
    public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

    // Constructors

    /** default constructor */
    public TYj() {
    }

    
    /** full constructor */
    public TYj(Integer yjid, String year,  Timestamp savetime,String award, String student, String teacher, int score, String studentCode, String teacherCode) {
    	this.yjid = yjid;
        this.year = year;
        this.award = award;
        this.student = student;
        this.savetime=savetime;
        this.teacher = teacher;
        this.score = score;
        this.studentCode = studentCode;
        this.teacherCode = teacherCode;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    @Column(name="yjid", unique=true, nullable=false)
    public Integer getYjid() {
        return this.yjid;
    }
    public void setYjid(Integer yjid) {
        this.yjid = yjid;
    }
    
    @Column(name="year", length=50)
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    @Column(name="award", length=200)
    public String getAward() {
        return this.award;
    }
    public void setAward(String award) {
        this.award = award;
    }
    
    @Column(name="student", length=64)
    public String getStudent() {
        return this.student;
    }
    public void setStudent(String student) {
        this.student = student;
    }
    
    @Column(name="teacher", length=64)
    public String getTeacher() {
        return this.teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
    @Column(name="score")
    public Integer getScore() {
        return this.score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    
    @Column(name="studentCode", length=64)
    public String getStudentCode() {
        return this.studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    
    @Column(name="teacherCode", length=64)
    public String getTeacherCode() {
        return this.teacherCode;
    }
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
}
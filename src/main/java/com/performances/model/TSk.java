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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 授课
 */
@Entity
@Table(name="t_sk"
    ,catalog="per"
)

public class TSk  implements java.io.Serializable {

	// Fields    
		private Integer id;
	    private String year;
	    private String name;
	    private String teacher;
	    private String role;
	    private String type;
	    private String period;
	    private Double score;
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
	    public TSk() {
	    }

	    
	    /** full constructor */
	    public TSk(Integer id, String year, String name, Timestamp savetime,String teacher, String role, String type, String period, double score, String teacherCode) {
	    	this.id = id;
	        this.year = year;
	        this.name = name;
	        this.savetime=savetime;
	        this.role = role;
	        this.teacher = teacher;
	        this.score = score;
	        this.type = type;
	        this.period = period;
	        this.teacherCode = teacherCode;
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
	    
	    @Column(name="year", length=50)
	    public String getYear() {
	        return this.year;
	    }
	    public void setYear(String year) {
	        this.year = year;
	    }
	    
	    @Column(name="name", length=128)
	    public String getName() {
	        return this.name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    @Column(name="role", length=32)
	    public String getRole() {
	        return this.role;
	    }
	    public void setRole(String role) {
	        this.role = role;
	    }
	    
	    @Column(name="teacher", length=64)
	    public String getTeacher() {
	        return this.teacher;
	    }
	    public void setTeacher(String teacher) {
	        this.teacher = teacher;
	    }
	    
	    @Column(name="type", length=64)
	    public String getType() {
	        return this.type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }
	    
	    @Column(name="period", length=64)
	    public String getPeriod() {
	        return this.period;
	    }
	    public void setPeriod(String period) {
	        this.period = period;
	    }
	    
	    @Column(name="score")
	    public Double getScore() {
	        return this.score;
	    }
	    public void setScore(Double score) {
	        this.score = score;
	    }
	    
	    @Column(name="teacherCode", length=64)
	    public String getTeacherCode() {
	        return this.teacherCode;
	    }
	    public void setTeacherCode(String teacherCode) {
	        this.teacherCode = teacherCode;
	    }
}
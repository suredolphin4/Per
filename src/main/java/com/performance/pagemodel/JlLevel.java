package com.performance.pagemodel;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 奖励的级别
 * JlLevel entity. @author yp6124830@126.com
 */


public class JlLevel  extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer jllevelid;
     private String name;
     private Integer score;
     private Set<Jl> jls = new HashSet<Jl>(0);
	public Integer getJllevelid() {
		return jllevelid;
	}
	public void setJllevelid(Integer jllevelid) {
		this.jllevelid = jllevelid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Set<Jl> getJls() {
		return jls;
	}
	public void setJls(Set<Jl> jls) {
		this.jls = jls;
	}

}
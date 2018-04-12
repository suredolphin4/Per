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
 * 标准的级别
 * @author peng
 *
 */
public class BzLevel  extends BaseModel  implements java.io.Serializable {


    // Fields    

     private Integer bzlevelid;
     private String name;
     private Integer score;
     private Set<Bz> bzs = new HashSet<Bz>(0);
	public Integer getBzlevelid() {
		return bzlevelid;
	}
	public void setBzlevelid(Integer bzlevelid) {
		this.bzlevelid = bzlevelid;
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
	public Set<Bz> getBzs() {
		return bzs;
	}
	public void setBzs(Set<Bz> bzs) {
		this.bzs = bzs;
	}

}
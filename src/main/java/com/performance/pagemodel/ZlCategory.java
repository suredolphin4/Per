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
 * 专利类别
 * ZlCategory entity. @author yp6124830@126.com
 */


public class ZlCategory  extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer zlcateid;
     private String name;
     private Integer score;
     private Set<Zl> zls = new HashSet<Zl>(0);
	public Integer getZlcateid() {
		return zlcateid;
	}
	public void setZlcateid(Integer zlcateid) {
		this.zlcateid = zlcateid;
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
	public Set<Zl> getZls() {
		return zls;
	}
	public void setZls(Set<Zl> zls) {
		this.zls = zls;
	}

}
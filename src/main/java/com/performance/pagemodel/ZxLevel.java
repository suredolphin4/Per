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
 * 咨询报告级别
 * ZxLevel entity. @author yp6124830@126.com
 */

public class ZxLevel  extends BaseModel   implements java.io.Serializable {


    // Fields    

     private Integer zxlevelid;
     private String name;
     private Integer score;
     private Set<Zx> zxes = new HashSet<Zx>(0);
	public Integer getZxlevelid() {
		return zxlevelid;
	}
	public void setZxlevelid(Integer zxlevelid) {
		this.zxlevelid = zxlevelid;
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
	public Set<Zx> getZxes() {
		return zxes;
	}
	public void setZxes(Set<Zx> zxes) {
		this.zxes = zxes;
	}
}
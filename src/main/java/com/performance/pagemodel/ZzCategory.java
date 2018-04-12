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
 * 著作类别 ZzCategory entity. @author yp6124830@126.com
 */

public class ZzCategory extends BaseModel implements java.io.Serializable {

	// Fields
	private Integer zzcateid;
	private String name;
	private Integer score;

	public Integer getZzcateid() {
		return zzcateid;
	}

	public void setZzcateid(Integer zzcateid) {
		this.zzcateid = zzcateid;
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
}
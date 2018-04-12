package com.performance.pagemodel;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


public class JcrType  extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer jcrtypeid;
     private Jcr jcr;
     private String name;
     private Integer score;
	public Integer getJcrtypeid() {
		return jcrtypeid;
	}
	public void setJcrtypeid(Integer jcrtypeid) {
		this.jcrtypeid = jcrtypeid;
	}
	public Jcr getJcr() {
		return jcr;
	}
	public void setJcr(Jcr jcr) {
		this.jcr = jcr;
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
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
 * 地图类别
 * @author peng
 *
 */

public class DtCate  extends BaseModel  implements java.io.Serializable {


    // Fields    

     private Integer dtcateid;
     private String name;
     private Integer score;
     private Set<Dt> dts = new HashSet<Dt>(0);
	public Integer getDtcateid() {
		return dtcateid;
	}
	public void setDtcateid(Integer dtcateid) {
		this.dtcateid = dtcateid;
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
	public Set<Dt> getDts() {
		return dts;
	}
	public void setDts(Set<Dt> dts) {
		this.dts = dts;
	}

}
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
 * 论文状态
 * LwStutas entity. @author yp6124830@126.com
 */

public class LwStutas  extends BaseModel  implements java.io.Serializable {

    // Fields    
    private Integer lwstutasid;
    private String name;
    private Set<Lw> lws = new HashSet<Lw>(0);
	public Integer getLwstutasid() {
		return lwstutasid;
	}
	public void setLwstutasid(Integer lwstutasid) {
		this.lwstutasid = lwstutasid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Lw> getLws() {
		return lws;
	}
	public void setLws(Set<Lw> lws) {
		this.lws = lws;
	}
}
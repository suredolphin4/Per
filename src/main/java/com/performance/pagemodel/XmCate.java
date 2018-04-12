package com.performance.pagemodel;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * 项目类别
 * XmCate entity. @author yp6124830@126.com
 */

public class XmCate   extends BaseModel implements java.io.Serializable {



     private Integer xmcateid;
     private String name;
     private Integer score;
     private Set<Xm> xms = new HashSet<Xm>(0);
	public Integer getXmcateid() {
		return xmcateid;
	}
	public void setXmcateid(Integer xmcateid) {
		this.xmcateid = xmcateid;
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
	public Set<Xm> getXms() {
		return xms;
	}
	public void setXms(Set<Xm> xms) {
		this.xms = xms;
	}


}
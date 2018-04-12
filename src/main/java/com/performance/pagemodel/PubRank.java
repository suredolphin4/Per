package com.performance.pagemodel;

import java.util.HashSet;
import java.util.Set;


public class PubRank {
	 private Integer trid;
     private String title;
     private String factor;
     private String rank;
     private String code;
     private Set<Lw> Lws = new HashSet<Lw>(0);
	public Integer getTrid() {
		return trid;
	}
	public void setTrid(Integer trid) {
		this.trid = trid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Set<Lw> getLws() {
		return Lws;
	}
	public void setLws(Set<Lw> lws) {
		Lws = lws;
	}
}

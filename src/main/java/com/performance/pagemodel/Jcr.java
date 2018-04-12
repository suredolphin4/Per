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

public class Jcr extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer jcrid;
	private String name;
	private String issn;
	private String factor;
	private Integer rank;
	private Integer internation;
	private Integer toplevel;
	private Integer score;
	private Set<Lw> lws = new HashSet<Lw>(0);
	private Set<JcrType> jcrTypes = new HashSet<JcrType>(0);

	public Integer getJcrid() {
		return jcrid;
	}

	public void setJcrid(Integer jcrid) {
		this.jcrid = jcrid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getInternation() {
		return internation;
	}

	public void setInternation(Integer internation) {
		this.internation = internation;
	}

	public Integer getToplevel() {
		return toplevel;
	}

	public void setToplevel(Integer toplevel) {
		this.toplevel = toplevel;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Set<Lw> getLws() {
		return lws;
	}

	public void setLws(Set<Lw> lws) {
		this.lws = lws;
	}

	public Set<JcrType> getJcrTypes() {
		return jcrTypes;
	}

	public void setJcrTypes(Set<JcrType> jcrTypes) {
		this.jcrTypes = jcrTypes;
	}

}
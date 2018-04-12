package com.performances.model;

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
 * 刊物排名 TLwStutas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_pubrank", catalog = "per")
public class TPubRank implements java.io.Serializable {

	// Fields

	private Integer trid;
	private String title;
	private String factor;
	private String rank;
	private String code;
	private Set<TLw> TLws = new HashSet<TLw>(0);

	// Constructors

	/** default constructor */
	public TPubRank() {
	}

	public TPubRank(Integer trid, String title, String factor, String rank,
			String code, Set<TLw> tLws) {
		this.trid = trid;
		this.title = title;
		this.factor = factor;
		this.rank = rank;
		this.code = code;
		TLws = tLws;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "trid", unique = true, nullable = false)
	public Integer getTrid() {
		return trid;
	}

	@Column(name = "title", length = 150)
	public String getTitle() {
		return title;
	}

	public void setTrid(Integer trid) {
		this.trid = trid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "factor", length = 150)
	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	@Column(name = "rank", length = 150)
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Column(name = "code", length = 150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TPubRank")
	public Set<TLw> getTLws() {
		return TLws;
	}

	public void setTLws(Set<TLw> tLws) {
		TLws = tLws;
	}

}
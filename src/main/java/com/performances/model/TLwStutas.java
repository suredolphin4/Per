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
 * 论文状态 TLwStutas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_lwstutas", catalog = "per")
public class TLwStutas implements java.io.Serializable {

	// Fields

	private Integer lwstutasid;
	private String name;
	//private Set<TLw> TLws = new HashSet<TLw>(0);

	// Constructors

	/** default constructor */
	public TLwStutas() {
	}

	/** full constructor */
	public TLwStutas(String name, String doi, Set<TLw> TLws) {
		this.name = name;
		//this.TLws = TLws;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lwstutasid", unique = true, nullable = false)
	public Integer getLwstutasid() {
		return this.lwstutasid;
	}

	public void setLwstutasid(Integer lwstutasid) {
		this.lwstutasid = lwstutasid;
	}

	@Column(name = "name", length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TLwStutas")
//	public Set<TLw> getTLws() {
//		return this.TLws;
//	}
//
//	public void setTLws(Set<TLw> TLws) {
//		this.TLws = TLws;
//	}

}
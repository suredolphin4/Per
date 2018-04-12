package com.performances.model;

// default package

import java.sql.Timestamp;
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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 刊物分区 TLwStutas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_pubpart", catalog = "per")
@DynamicUpdate(true) // 允许部分更新
public class TPubPart implements java.io.Serializable {

	// Fields
	private Integer tpid;
	private String title;
	private String issn;
	private String factor;
	private String zone;//小区
	private String pubkind;//类型
	private String remark;
    private String topcomm;
    private String pubranking;//排名
    
    @Column(name = "pubranking", length = 150)
	public String getPubranking() {
		return pubranking;
	}

	public void setPubranking(String pubranking) {
		this.pubranking = pubranking;
	}
	private Timestamp savetime;
	@Column(name="savetime", length=19)
	public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	private Set<TLw> TLws = new HashSet<TLw>(0);

	// Constructors

	/** default constructor */
	public TPubPart() {
	}

	public TPubPart(java.sql.Timestamp savetime,String pubranking,Integer tpid, String title, String issn, String factor, String zone, String pubkind, String remark,
			Set<TLw> tLws) {
		super();
		this.pubranking=pubranking;
		this.tpid = tpid;
		this.savetime=savetime;
		this.title = title;
		this.issn = issn;
		this.factor = factor;
		this.zone = zone;
		this.pubkind = pubkind;
		this.remark = remark;
		TLws = tLws;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "tpid", unique = true, nullable = false)
	public Integer getTpid() {
		return tpid;
	}

	@Column(name = "title", length = 150)
	public String getTitle() {
		return title;
	}

	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "issn", length = 150)
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Column(name = "factor", length = 150)
	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	@Column(name = "zone", length = 150)
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@Column(name = "pubkind", length = 150)
	public String getPubkind() {
		return pubkind;
	}

	public void setPubkind(String pubkind) {
		this.pubkind = pubkind;
	}

	@Column(name = "remark", length = 150)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TPubPart")
	public Set<TLw> getTLws() {
		return TLws;
	}

	public void setTLws(Set<TLw> tLws) {
		TLws = tLws;
	}

	@Column(name = "topcomm", length = 150)
	public String getTopcomm() {
		return topcomm;
	}

	public void setTopcomm(String topcomm) {
		this.topcomm = topcomm;
	}
		
}
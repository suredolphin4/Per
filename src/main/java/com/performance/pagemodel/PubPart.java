package com.performance.pagemodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.performances.model.TLw;

public class PubPart extends BaseModel implements Serializable {
	private Integer tpid;
	private String title;
	private String issn;
	private String factor;
	private String zone;
	private String pubkind;
	private String remark;
	private String topcomm;
	private String pubranking;
	private Timestamp savetime;
	public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}


	public String getPubranking() {
		return pubranking;
	}

	public void setPubranking(String pubranking) {
		this.pubranking = pubranking;
	}

	public Integer getTpid() {
		return tpid;
	}

	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getPubkind() {
		return pubkind;
	}

	public void setPubkind(String pubkind) {
		this.pubkind = pubkind;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTopcomm() {
		return topcomm;
	}

	public void setTopcomm(String topcomm) {
		this.topcomm = topcomm;
	}

}

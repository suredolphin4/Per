package com.performances.model;

// default package

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * TGh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gh", catalog = "per")
@DynamicUpdate(true)
// 允许部分更新
public class TGh implements java.io.Serializable {

	// Fields
	private Integer ghid;
	private String examinestatus;
	private Timestamp examinetime;
	private String firstauthor;
	private String firstauthorcode;
	private String fiveauthor;
	private String fourauthor;
	private Timestamp ignoretime;
	private Integer numauthor;
	private String otherauthor;
	private Timestamp savetime;
	private String secauthor;
	private String submitUser;
	private Timestamp submitTime;
	private String threeauthor;
	private String title;
	private String year;
	private String secauthorcode;
	private String threeauthorcode;
	private String fourauthorcode;
	private String fiveauthorcode;
	private String otherauthorcode;
	private String uploadurl;
	private String auditopinion;
	private String auditdepart;
	private String auditlevel;
	private String auditdate;

	private String append;

	// Constructors

	/** default constructor */
	public TGh() {
	}

	/** full constructor */

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ghid", unique = true, nullable = false)
	public Integer getGhid() {
		return this.ghid;
	}

	public TGh(Integer ghid, String examinestatus, Timestamp examinetime,
			String firstauthor, String firstauthorcode, String fiveauthor,
			String fourauthor, Timestamp ignoretime, Integer numauthor,
			String otherauthor, Timestamp savetime, String secauthor,
			Timestamp submittime, String threeauthor, String title,
			String year, String secauthorcode, String threeauthorcode,
			String fourauthorcode, String fiveauthorcode,
			String otherauthorcode, String uploadurl, String auditopinion,
			String auditdepart, String auditlevel, String auditdate,
			String append) {
		super();
		this.ghid = ghid;
		this.examinestatus = examinestatus;
		this.examinetime = examinetime;
		this.firstauthor = firstauthor;
		this.firstauthorcode = firstauthorcode;
		this.fiveauthor = fiveauthor;
		this.fourauthor = fourauthor;
		this.ignoretime = ignoretime;
		this.numauthor = numauthor;
		this.otherauthor = otherauthor;
		this.savetime = savetime;
		this.secauthor = secauthor;
		this.submitTime = submittime;
		this.threeauthor = threeauthor;
		this.title = title;
		this.year = year;
		this.secauthorcode = secauthorcode;
		this.threeauthorcode = threeauthorcode;
		this.fourauthorcode = fourauthorcode;
		this.fiveauthorcode = fiveauthorcode;
		this.otherauthorcode = otherauthorcode;
		this.uploadurl = uploadurl;
		this.auditopinion = auditopinion;
		this.auditdepart = auditdepart;
		this.auditlevel = auditlevel;
		this.auditdate = auditdate;
		this.append = append;
	}

	public void setGhid(Integer ghid) {
		this.ghid = ghid;
	}

	@Column(name = "append", length = 500)
	public String getAppend() {
		return this.append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	@Column(name = "examinestatus", length = 100)
	public String getExaminestatus() {
		return this.examinestatus;
	}

	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}

	@Column(name = "examinetime", length = 19)
	public Timestamp getExaminetime() {
		return this.examinetime;
	}

	public void setExaminetime(Timestamp examinetime) {
		this.examinetime = examinetime;
	}

	@Column(name = "firstauthor", length = 500)
	public String getFirstauthor() {
		return this.firstauthor;
	}

	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}

	@Column(name = "fiveauthor", length = 500)
	public String getFiveauthor() {
		return this.fiveauthor;
	}

	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}

	@Column(name = "firstauthorcode", length = 100)
	public String getFirstauthorcode() {
		return firstauthorcode;
	}

	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name = "secauthorcode", length = 100)
	public String getSecauthorcode() {
		return secauthorcode;
	}

	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	@Column(name = "threeauthorcode", length = 100)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}

	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name = "fourauthorcode", length = 100)
	public String getFourauthorcode() {
		return fourauthorcode;
	}

	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name = "fiveauthorcode", length = 100)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}

	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name = "otherauthorcode", length = 100)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}

	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	@Column(name = "uploadurl", length = 300)
	public String getUploadurl() {
		return uploadurl;
	}

	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}

	@Column(name = "auditopinion", length = 350)
	public String getAuditopinion() {
		return auditopinion;
	}

	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}

	@Column(name = "auditdepart", length = 200)
	public String getAuditdepart() {
		return auditdepart;
	}

	public void setAuditdepart(String auditdepart) {
		this.auditdepart = auditdepart;
	}

	@Column(name = "auditlevel", length = 150)
	public String getAuditlevel() {
		return auditlevel;
	}

	public void setAuditlevel(String auditlevel) {
		this.auditlevel = auditlevel;
	}

	@Column(name = "auditdate", length = 150)
	public String getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	@Column(name = "fourauthor", length = 150)
	public String getFourauthor() {
		return this.fourauthor;
	}

	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}

	@Column(name = "ignoretime", length = 19)
	public Timestamp getIgnoretime() {
		return this.ignoretime;
	}

	public void setIgnoretime(Timestamp ignoretime) {
		this.ignoretime = ignoretime;
	}

	@Column(name = "numauthor")
	public Integer getNumauthor() {
		return this.numauthor;
	}

	public void setNumauthor(Integer numauthor) {
		this.numauthor = numauthor;
	}

	@Column(name = "otherauthor", length = 350)
	public String getOtherauthor() {
		return this.otherauthor;
	}

	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}

	@Column(name = "savetime", length = 19)
	public Timestamp getSavetime() {
		return this.savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	@Column(name = "secauthor", length = 150)
	public String getSecauthor() {
		return this.secauthor;
	}

	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}

	@Column(name = "submittime", length = 19)
	public Timestamp getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
	@Column(name = "submituser")
	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	@Column(name = "threeauthor", length = 150)
	public String getThreeauthor() {
		return this.threeauthor;
	}

	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}

	@Column(name = "title", length = 255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "year", length = 50)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	private Collection<TGhScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TGhScore> getScore(){
		return score;
	}
	public void setScore(Collection<TGhScore> score){
		this.score = score;
	}

}
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
 * TRj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_rj"
		, catalog = "per"
)
@DynamicUpdate(true) // 允许部分更新
public class TRj implements java.io.Serializable {
	// Fields
	private Integer rjid;
	private String firstauthor;
	private String fiveauthor;
	private String fourauthor;
	private Timestamp ignoretime;
	private Integer numauthor;
	private String otherauthor;
	private Timestamp savetime;
	private String secauthor;
	private Timestamp submittime;
	private String threeauthor;
	private String title;
	private String year;
	private String firstauthorcode;
	private String secauthorcode;
	private String threeauthorcode;
	private String fourauthorcode;
	private String fiveauthorcode;
	private String otherauthorcode;
	private String registernum;
	private String auditdate;
	private String copyright;
	private String othercopyright;
	private String departorder;

	// Constructors

	/**
	 * default constructor
	 */
	public TRj() {
	}

	/**
	 * full constructor
	 */

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "rjid", unique = true, nullable = false)

	public Integer getRjid() {
		return this.rjid;
	}

	public TRj(Integer rjid, String append, String examinestatus,
			   Timestamp examinetime, String firstauthor, String fiveauthor,
			   String fourauthor, Timestamp ignoretime, Integer numauthor,
			   String otherauthor, Timestamp savetime, String secauthor,
			   Timestamp submittime, String threeauthor, String title,
			   String year, String firstauthorcode, String secauthorcode,
			   String threeauthorcode, String fourauthorcode,
			   String fiveauthorcode, String otherauthorcode, String registernum,
			   String auditdate, String copyright, String othercopyright,
			   String departorder, String auditopinion) {
		super();
		this.rjid = rjid;
		this.firstauthor = firstauthor;
		this.fiveauthor = fiveauthor;
		this.fourauthor = fourauthor;
		this.ignoretime = ignoretime;
		this.numauthor = numauthor;
		this.otherauthor = otherauthor;
		this.savetime = savetime;
		this.secauthor = secauthor;
		this.submittime = submittime;
		this.threeauthor = threeauthor;
		this.title = title;
		this.year = year;
		this.firstauthorcode = firstauthorcode;
		this.secauthorcode = secauthorcode;
		this.threeauthorcode = threeauthorcode;
		this.fourauthorcode = fourauthorcode;
		this.fiveauthorcode = fiveauthorcode;
		this.otherauthorcode = otherauthorcode;
		this.registernum = registernum;
		this.auditdate = auditdate;
		this.copyright = copyright;
		this.othercopyright = othercopyright;
		this.departorder = departorder;
	}


	public void setRjid(Integer rjid) {
		this.rjid = rjid;
	}

	@Column(name = "firstauthor", length = 150)

	public String getFirstauthor() {
		return this.firstauthor;
	}

	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}

	@Column(name = "fiveauthor", length = 150)

	public String getFiveauthor() {
		return this.fiveauthor;
	}

	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
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


	@Column(name = "numauthor", length = 50)

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

	public Timestamp getSubmittime() {
		return this.submittime;
	}

	public void setSubmittime(Timestamp submittime) {
		this.submittime = submittime;
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
//@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TRjs")
//
//    public Set<TUser> getTUsers() {
//        return this.TUsers;
//    }
//    
//    public void setTUsers(Set<TUser> TUsers) {
//        this.TUsers = TUsers;
//    }

	@Column(name = "firstauthorcode", length = 150)
	public String getFirstauthorcode() {
		return firstauthorcode;
	}


	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name = "secauthorcode", length = 150)
	public String getSecauthorcode() {
		return secauthorcode;
	}


	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	@Column(name = "threeauthorcode", length = 150)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}


	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name = "fourauthorcode", length = 150)
	public String getFourauthorcode() {
		return fourauthorcode;
	}


	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name = "fiveauthorcode", length = 150)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}


	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name = "otherauthorcode", length = 350)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}


	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	@Column(name = "registernum", length = 255)
	public String getRegisternum() {
		return registernum;
	}


	public void setRegisternum(String registernum) {
		this.registernum = registernum;
	}

	@Column(name = "auditdate", length = 255)
	public String getAuditdate() {
		return auditdate;
	}


	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	@Column(name = "copyright", length = 255)
	public String getCopyright() {
		return copyright;
	}


	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	@Column(name = "othercopyright", length = 255)
	public String getOthercopyright() {
		return othercopyright;
	}


	public void setOthercopyright(String othercopyright) {
		this.othercopyright = othercopyright;
	}

	@Column(name = "departorder", length = 200)
	public String getDepartorder() {
		return departorder;
	}


	public void setDepartorder(String departorder) {
		this.departorder = departorder;
	}


	private Collection<TRjScore> score;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fid", insertable = false, updatable = false)
	public Collection<TRjScore> getScore() {
		return score;
	}

	public void setScore(Collection<TRjScore> score) {
		this.score = score;
	}
}
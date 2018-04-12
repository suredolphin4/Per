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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.performance.pagemodel.User;


/**
 * TZx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_zx"
    ,catalog="per"
)
@DynamicUpdate(true) // 允许部分更新
public class TZx  implements java.io.Serializable {

    // Fields    
	private Integer zxid;
    private String year;
    private String	title;
    private String auditleader;
    private String	auditdepart;
    private String	auditlevel;
    private String	auditdate;
    private String numauthor;
    private String	firstauthor;
    private String	firstauthorcode;
    private String	secauthor;
    private String secauthorcode;
    private String	threeauthor;
    private String	threeauthorcode;
    private String	fourauthor;
    private String	fourauthorcode;
    private String	fiveauthor;
    private String	fiveauthorcode;
    private String	otherauthor;
    private String	otherauthorcode;
    private String	uploadurl;
    private String append;
    private String	examinestatus;  
    private String  auditopinion;
	private Timestamp examinetime;
	private Timestamp ignoretime;
	private Timestamp savetime;
    //private Set<User> users = new HashSet<User>(0);
	
	private String submitUser;
	private Timestamp submitTime;

    // Constructors

    /** default constructor */
    public TZx() {
    }

    
    /** full constructor */
    
   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="zxid", unique=true, nullable=false)

    public Integer getZxid() {
        return this.zxid;
    }
    
   
    @Column(name = "append", length = 255)
	public String getAppend() {
		return append;
	}


	public void setAppend(String append) {
		this.append = append;
	}


	public TZx(Integer zxid, String year, String title, String auditleader,
			String auditdepart, String auditlevel, String auditdate,
			String numauthor, String firstauthor, String firstauthorcode,
			String secauthor, String secauthorcode, String threeauthor,
			String threeauthorcode, String fourauthor, String fourauthorcode,
			String fiveauthor, String fiveauthorcode, String otherauthor,
			String otherauthorcode, String uploadurl, String append,
			String examinestatus, String auditopinion, Timestamp examinetime,
			Timestamp ignoretime, Timestamp savetime) {
		super();
		this.zxid = zxid;
		this.year = year;
		this.title = title;
		this.auditleader = auditleader;
		this.auditdepart = auditdepart;
		this.auditlevel = auditlevel;
		this.auditdate = auditdate;
		this.numauthor = numauthor;
		this.firstauthor = firstauthor;
		this.firstauthorcode = firstauthorcode;
		this.secauthor = secauthor;
		this.secauthorcode = secauthorcode;
		this.threeauthor = threeauthor;
		this.threeauthorcode = threeauthorcode;
		this.fourauthor = fourauthor;
		this.fourauthorcode = fourauthorcode;
		this.fiveauthor = fiveauthor;
		this.fiveauthorcode = fiveauthorcode;
		this.otherauthor = otherauthor;
		this.otherauthorcode = otherauthorcode;
		this.uploadurl = uploadurl;
		this.append = append;
		this.examinestatus = examinestatus;
		this.auditopinion = auditopinion;
		this.examinetime = examinetime;
		this.ignoretime = ignoretime;
		this.savetime = savetime;
	}


	@Column(name = "year", length = 50)
	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "title", length = 255)
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "auditleader", length = 100)
	public String getAuditleader() {
		return auditleader;
	}


	public void setAuditleader(String auditleader) {
		this.auditleader = auditleader;
	}

	@Column(name = "auditdepart", length = 100)
	public String getAuditdepart() {
		return auditdepart;
	}


	public void setAuditdepart(String auditdepart) {
		this.auditdepart = auditdepart;
	}

	@Column(name = "auditlevel", length = 100)
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

	@Column(name = "numauthor", length = 50)
	public String getNumauthor() {
		return numauthor;
	}


	public void setNumauthor(String numauthor) {
		this.numauthor = numauthor;
	}

	@Column(name = "firstauthor", length = 100)
	public String getFirstauthor() {
		return firstauthor;
	}

	
	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}

	@Column(name = "firstauthorcode", length = 50)
	public String getFirstauthorcode() {
		return firstauthorcode;
	}


	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name = "secauthor", length = 100)
	public String getSecauthor() {
		return secauthor;
	}


	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}

	@Column(name = "secauthorcode", length = 50)
	public String getSecauthorcode() {
		return secauthorcode;
	}


	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}


	@Column(name = "threeauthor", length =100)
	public String getThreeauthor() {
		return threeauthor;
	}


	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}

	@Column(name = "threeauthorcode", length = 50)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}


	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name = "fourauthor", length = 50)
	public String getFourauthor() {
		return fourauthor;
	}


	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}

	@Column(name = "fourauthorcode", length = 50)
	public String getFourauthorcode() {
		return fourauthorcode;
	}


	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name = "fiveauthor", length = 100)
	public String getFiveauthor() {
		return fiveauthor;
	}


	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}

	@Column(name = "fiveauthorcode", length = 50)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}


	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name = "otherauthor", length = 350)
	public String getOtherauthor() {
		return otherauthor;
	}


	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}

	@Column(name = "otherauthorcode", length = 350)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}


	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	@Column(name = "uploadurl", length = 100)
	public String getUploadurl() {
		return uploadurl;
	}

	
	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}

	@Column(name = "examinestatus", length = 50)
	public String getExaminestatus() {
		return examinestatus;
	}


	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}

	@Column(name = "auditopinion", length = 300)
	public String getAuditopinion() {
		return auditopinion;
	}


	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}

	
//	public Set<User> getUsers() {
//		return users;
//	}
//
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}


	public void setZxid(Integer zxid) {
		this.zxid = zxid;
	}

	@Column(name = "examinetime", length = 19)
	public Timestamp getExaminetime() {
		return examinetime;
	}


	public void setExaminetime(Timestamp examinetime) {
		this.examinetime = examinetime;
	}

	@Column(name = "ignoretime", length = 19)
	public Timestamp getIgnoretime() {
		return ignoretime;
	}


	public void setIgnoretime(Timestamp ignoretime) {
		this.ignoretime = ignoretime;
	}

	@Column(name = "savetime", length = 19)
	public Timestamp getSavetime() {
		return savetime;
	}

	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}

	@Column(name = "submituser", length=50)
	public String getSubmitUser() {
		return submitUser;
	}


	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	@Column(name = "submittime")
	public Timestamp getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
	private Collection<TZxScore> tzxScore;
	
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TZxScore> getTzxScore(){
		return tzxScore;
	}
	public void setTzxScore(Collection<TZxScore> tzxScore){
		this.tzxScore = tzxScore;
	}
	
}

   







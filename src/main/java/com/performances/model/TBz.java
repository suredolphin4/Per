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
 * TBz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_bz" 
    ,catalog="per"
)
@DynamicUpdate(true) // 允许部分更新
public class TBz  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bzid;
     private String examinestatus;
     private Timestamp examinetime;
     private String firstauthor;
     private String	firstauthorcode;
     private String fiveauthor;
     private String fourauthor;
     private Timestamp ignoretime;
     private String markid;
     private Integer numauthor;
     private String otherauthor;
     private Timestamp savetime;
     private String secauthor;
     private String threeauthor;
     private String title;
     private String year;
     private String secauthorcode;
     private String	threeauthorcode;
     private String	fourauthorcode;
     private String	fiveauthorcode;
     private String	otherauthorcode;
     private String	uploadurl;
     private String  auditopinion;
     private String	auditdepart;
     private String	auditlevel;
     private String	auditdate;
     private String departorder;
//     private Set<TUser> TUsers = new HashSet<TUser>();
     private String append;
     
     private String submitUser;
     private java.sql.Timestamp submitTime;

    // Constructors

    /** default constructor */
    public TBz() {
    }

    
    /** full constructor */

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="bzid", unique=true, nullable=false)

    public Integer getBzid() {
        return this.bzid;
    }
    
    public TBz(Integer bzid, String append, String examinestatus,
			Timestamp examinetime, String firstauthor, String firstauthorcode,
			String fiveauthor, String fourauthor, Timestamp ignoretime,
			String markid, Integer numauthor, String otherauthor,
			Timestamp savetime, String secauthor, Timestamp submittime,
			String threeauthor, String title, String year,
			String secauthorcode, String threeauthorcode,
			String fourauthorcode, String fiveauthorcode,
			String otherauthorcode, String uploadurl, String auditopinion,
			String auditdepart, String auditlevel,
			String auditdate, String departorder) {
		super();
		this.bzid = bzid;
		this.append = append;
		this.examinestatus = examinestatus;
		this.examinetime = examinetime;
		this.firstauthor = firstauthor;
		this.firstauthorcode = firstauthorcode;
		this.fiveauthor = fiveauthor;
		this.fourauthor = fourauthor;
		this.ignoretime = ignoretime;
		this.markid = markid;
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
		this.departorder = departorder;
	}


	public void setBzid(Integer bzid) {
        this.bzid = bzid;
    }

	 @Column(name="firstauthorcode", length=50)
    public String getFirstauthorcode() {
		return firstauthorcode;
	}


	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name="secauthorcode", length=50)
	public String getSecauthorcode() {
		return secauthorcode;
	}


	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	@Column(name="threeauthorcode", length=50)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}


	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name="fourauthorcode", length=50)
	public String getFourauthorcode() {
		return fourauthorcode;
	}


	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name="fiveauthorcode", length=50)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}


	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name="otherauthorcode", length=200)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}


	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}

	@Column(name="uploadurl", length=200)
	public String getUploadurl() {
		return uploadurl;
	}

	
	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}

	@Column(name="auditopinion", length=350)
	public String getAuditopinion() {
		return auditopinion;
	}


	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}


	@Column(name="auditdepart", length=150)
	public String getAuditdepart() {
		return auditdepart;
	}


	public void setAuditdepart(String auditdepart) {
		this.auditdepart = auditdepart;
	}

	@Column(name="auditlevel", length=150)
	public String getAuditlevel() {
		return auditlevel;
	}


	public void setAuditlevel(String auditlevel) {
		this.auditlevel = auditlevel;
	}

	@Column(name="auditdate", length=150)
	public String getAuditdate() {
		return auditdate;
	}


	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="departorder", length=50)
	public String getDepartorder() {
		return departorder;
	}


	public void setDepartorder(String departorder) {
		this.departorder = departorder;
	}


	@Column(name="append", length=500)
    public String getAppend() {
        return this.append;
    }
    
    public void setAppend(String append) {
        this.append = append;
    }
    
    @Column(name="examinestatus",length=100)
    public String getExaminestatus() {
        return this.examinestatus;
    }
    
    public void setExaminestatus(String examinestatus) {
        this.examinestatus = examinestatus;
    }
    
    @Column(name="examinetime", length=19)
    public Timestamp getExaminetime() {
        return this.examinetime;
    }
    
    public void setExaminetime(Timestamp examinetime) {
        this.examinetime = examinetime;
    }
    
    @Column(name="firstauthor", length=100)
    public String getFirstauthor() {
        return this.firstauthor;
    }
    
    public void setFirstauthor(String firstauthor) {
        this.firstauthor = firstauthor;
    }
    
    @Column(name="fiveauthor", length=100)
    public String getFiveauthor() {
        return this.fiveauthor;
    }
    
    public void setFiveauthor(String fiveauthor) {
        this.fiveauthor = fiveauthor;
    }
    
    @Column(name="fourauthor", length=100)
    public String getFourauthor() {
        return this.fourauthor;
    }
    
    public void setFourauthor(String fourauthor) {
        this.fourauthor = fourauthor;
    }
    
    @Column(name="ignoretime", length=19)
    public Timestamp getIgnoretime() {
        return this.ignoretime;
    }
    
    public void setIgnoretime(Timestamp ignoretime) {
        this.ignoretime = ignoretime;
    }
    
    @Column(name="markid", length=150)
    public String getMarkid() {
        return this.markid;
    }
    
    public void setMarkid(String markid) {
        this.markid = markid;
    }
    
    @Column(name="numauthor")
    public Integer getNumauthor() {
        return this.numauthor;
    }
    
    public void setNumauthor(Integer numauthor) {
        this.numauthor = numauthor;
    }
    
    @Column(name="otherauthor", length=300)
    public String getOtherauthor() {
        return this.otherauthor;
    }
    
    public void setOtherauthor(String otherauthor) {
        this.otherauthor = otherauthor;
    }
    
    @Column(name="savetime", length=19)
    public Timestamp getSavetime() {
        return this.savetime;
    }
    
    public void setSavetime(Timestamp savetime) {
        this.savetime = savetime;
    }
    
    @Column(name="secauthor", length=100)
    public String getSecauthor() {
        return this.secauthor;
    }
    
    public void setSecauthor(String secauthor) {
        this.secauthor = secauthor;
    }
    
    @Column(name="threeauthor", length=100)
    public String getThreeauthor() {
        return this.threeauthor;
    }
    
    public void setThreeauthor(String threeauthor) {
        this.threeauthor = threeauthor;
    }
    
    @Column(name="title", length=255)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="year", length=50)
    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
//@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TBzs")
//
//    public Set<TUser> getTUsers() {
//        return this.TUsers;
//    }
//    
//    public void setTUsers(Set<TUser> TUsers) {
//        this.TUsers = TUsers;
//    }

    @Column(name = "submituser")
	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	@Column(name = "submittime")
	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}
    
	private Collection<TBzScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TBzScore> getScore(){
		return score;
	}
	public void setScore(Collection<TBzScore> score){
		this.score = score;
	}
}
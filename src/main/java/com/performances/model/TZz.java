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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * TZz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_zz"
    ,catalog="per"
)

public class TZz  implements java.io.Serializable {


    // Fields    

     private Integer zzid;
     private TZzCategory TZzCategory;
     private String append;
     private Integer examinestatus;
     private Timestamp examinetime;
     private String firstauthor;
     private String fiveauthor;
     private String fourauthor;
     private Timestamp ignoretime;
     private String markid;
     private Integer numauthor;
     private String otherauthor;
     private Timestamp savetime;
     private String secauthor;
     private Timestamp submittime;
     private String threeauthor;
     private String title;
     private String year;
     private Set<TUser> TUsers = new HashSet<TUser>(0);
     private TDomain TDomain;
     private TLab TLab;

    // Constructors

    /** default constructor */
    public TZz() {
    }

    
    /** full constructor */
    public TZz(TLab TLab,TDomain TDomain,TZzCategory TZzCategory, String append, Integer examinestatus, Timestamp examinetime, String firstauthor, String fiveauthor, String fourauthor, Timestamp ignoretime, String markid, Integer numauthor, String otherauthor, Timestamp savetime, String secauthor, Timestamp submittime, String threeauthor, String title, String year, Set<TUser> TUsers) {
        
    	this.TDomain=TDomain;
    	this.TLab=TLab;
    	this.TZzCategory = TZzCategory;
        this.append = append;
        this.examinestatus = examinestatus;
        this.examinetime = examinetime;
        this.firstauthor = firstauthor;
        this.fiveauthor = fiveauthor;
        this.fourauthor = fourauthor;
        this.ignoretime = ignoretime;
        this.markid = markid;
        this.numauthor = numauthor;
        this.otherauthor = otherauthor;
        this.savetime = savetime;
        this.secauthor = secauthor;
        this.submittime = submittime;
        this.threeauthor = threeauthor;
        this.title = title;
        this.year = year;
        this.TUsers = TUsers;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="zzid", unique=true, nullable=false)

    public Integer getZzid() {
        return this.zzid;
    }
    
    public void setZzid(Integer zzid) {
        this.zzid = zzid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="zzcateid")

    public TZzCategory getTZzCategory() {
        return this.TZzCategory;
    }
    
    public void setTZzCategory(TZzCategory TZzCategory) {
        this.TZzCategory = TZzCategory;
    }
    
    @Column(name="append", length=500)

    public String getAppend() {
        return this.append;
    }
    
    public void setAppend(String append) {
        this.append = append;
    }
    
    @Column(name="examinestatus")

    public Integer getExaminestatus() {
        return this.examinestatus;
    }
    
    public void setExaminestatus(Integer examinestatus) {
        this.examinestatus = examinestatus;
    }
    
    @Column(name="examinetime", length=19)

    public Timestamp getExaminetime() {
        return this.examinetime;
    }
    
    public void setExaminetime(Timestamp examinetime) {
        this.examinetime = examinetime;
    }
    
    @Column(name="firstauthor", length=500)

    public String getFirstauthor() {
        return this.firstauthor;
    }
    
    public void setFirstauthor(String firstauthor) {
        this.firstauthor = firstauthor;
    }
    
    @Column(name="fiveauthor", length=500)

    public String getFiveauthor() {
        return this.fiveauthor;
    }
    
    public void setFiveauthor(String fiveauthor) {
        this.fiveauthor = fiveauthor;
    }
    
    @Column(name="fourauthor", length=500)

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
    
    @Column(name="markid", length=500)

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
    
    @Column(name="otherauthor", length=1024)

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
    
    @Column(name="secauthor", length=500)

    public String getSecauthor() {
        return this.secauthor;
    }
    
    public void setSecauthor(String secauthor) {
        this.secauthor = secauthor;
    }
    
    @Column(name="submittime", length=19)

    public Timestamp getSubmittime() {
        return this.submittime;
    }
    
    public void setSubmittime(Timestamp submittime) {
        this.submittime = submittime;
    }
    
    @Column(name="threeauthor", length=500)

    public String getThreeauthor() {
        return this.threeauthor;
    }
    
    public void setThreeauthor(String threeauthor) {
        this.threeauthor = threeauthor;
    }
    
    @Column(name="title", length=3000)

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
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="r_user_zz", catalog="per", joinColumns = { 
        @JoinColumn(name="zzid", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="userid", nullable=false, updatable=false) })

    public Set<TUser> getTUsers() {
        return this.TUsers;
    }
    
    public void setTUsers(Set<TUser> TUsers) {
        this.TUsers = TUsers;
    }
   



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="domainid")
    public TDomain getTDomain() {
		return TDomain;
	}



	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="labid")
	public TLab getTLab() {
		return TLab;
	}


	public void setTLab(TLab tLab) {
		TLab = tLab;
	}


	public void setTDomain(TDomain tDomain) {
		TDomain = tDomain;
	}






}
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * TRight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_right"
    ,catalog="per"
)

public class TRight  implements java.io.Serializable {


    // Fields    

     private Integer rightid;
     private String rightname;
     private String righturl;
     private String rightdesc;
     private Long rightcode;
     private Integer rightpos;
     private Set<TRole> TRoles = new HashSet<TRole>();
     private Integer common;

    // Constructors

 


	/** default constructor */
    public TRight() {
    }

    public TRight(Integer rightid) {
		super();
		this.rightid = rightid;
	}

	@Column(name="common")
    public Integer getCommon() {
		return common;
	}


	public void setCommon(Integer common) {
		this.common = common;
	}


	/** full constructor */
    public TRight(Integer common,String rightname, String righturl, String rightdesc, Long rightcode, Integer rightpos, Set<TRole> TRoles) {
        this.rightname = rightname;
        this.righturl = righturl;
        this.rightdesc = rightdesc;
        this.rightcode = rightcode;
        this.rightpos = rightpos;
        this.TRoles = TRoles;
        this.common=common;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="rightid", unique=true, nullable=false)

    public Integer getRightid() {
        return this.rightid;
    }
    
    public void setRightid(Integer rightid) {
        this.rightid = rightid;
    }
    
    @Column(name="rightname", length=150)

    public String getRightname() {
        return this.rightname;
    }
    
    public void setRightname(String rightname) {
        this.rightname = rightname;
    }
    
    @Column(name="righturl", length=150)

    public String getRighturl() {
        return this.righturl;
    }
    
    public void setRighturl(String righturl) {
        this.righturl = righturl;
    }
    
    @Column(name="rightdesc", length=150)

    public String getRightdesc() {
        return this.rightdesc;
    }
    
    public void setRightdesc(String rightdesc) {
        this.rightdesc = rightdesc;
    }
    
    @Column(name="rightcode")

    public Long getRightcode() {
        return this.rightcode;
    }
    
    public void setRightcode(Long rightcode) {
        this.rightcode = rightcode;
    }
    
    @Column(name="rightpos")

    public Integer getRightpos() {
        return this.rightpos;
    }
    
    public void setRightpos(Integer rightpos) {
        this.rightpos = rightpos;
    }
    @ManyToMany(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY, mappedBy="TRights")

    public Set<TRole> getTRoles() {
        return this.TRoles;
    }
    
    public void setTRoles(Set<TRole> TRoles) {
        this.TRoles = TRoles;
    }
   








}
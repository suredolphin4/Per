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
 * TJcr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_jcr"
    ,catalog="per"
)

public class TJcr  implements java.io.Serializable {


    // Fields    

     private Integer jcrid;
     private String name;
     private String issn;
     private String factor;
     private Integer rank;
     private Integer internation;
     private Integer toplevel;
     private Integer score;
     private Set<TLw> TLws = new HashSet<TLw>(0);
     private Set<TJcrType> TJcrTypes = new HashSet<TJcrType>(0);


    // Constructors

    /** default constructor */
    public TJcr() {
    }

    
    /** full constructor */
    public TJcr(String name, String issn, String factor, Integer rank, Integer internation, Integer toplevel, Integer score, Set<TLw> TLws, Set<TJcrType> TJcrTypes) {
        this.name = name;
        this.issn = issn;
        this.factor = factor;
        this.rank = rank;
        this.internation = internation;
        this.toplevel = toplevel;
        this.score = score;
        this.TLws = TLws;
        this.TJcrTypes = TJcrTypes;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="jcrid", unique=true, nullable=false)

    public Integer getJcrid() {
        return this.jcrid;
    }
    
    public void setJcrid(Integer jcrid) {
        this.jcrid = jcrid;
    }
    
    @Column(name="name", length=150)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="issn", length=150)

    public String getIssn() {
        return this.issn;
    }
    
    public void setIssn(String issn) {
        this.issn = issn;
    }
    
    @Column(name="factor", length=150)

    public String getFactor() {
        return this.factor;
    }
    
    public void setFactor(String factor) {
        this.factor = factor;
    }
    
    @Column(name="rank")

    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
    @Column(name="internation")

    public Integer getInternation() {
        return this.internation;
    }
    
    public void setInternation(Integer internation) {
        this.internation = internation;
    }
    
    @Column(name="toplevel")

    public Integer getToplevel() {
        return this.toplevel;
    }
    
    public void setToplevel(Integer toplevel) {
        this.toplevel = toplevel;
    }
    
    @Column(name="score")

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TJcr")

    public Set<TLw> getTLws() {
        return this.TLws;
    }
    
    public void setTLws(Set<TLw> TLws) {
        this.TLws = TLws;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TJcr")

    public Set<TJcrType> getTJcrTypes() {
        return this.TJcrTypes;
    }
    
    public void setTJcrTypes(Set<TJcrType> TJcrTypes) {
        this.TJcrTypes = TJcrTypes;
    }
   








}
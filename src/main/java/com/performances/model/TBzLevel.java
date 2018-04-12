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
 * TBzLevel entity. @author MyEclipse Persistence Tools
 */
//@Entity
//@Table(name="t_bzlevel"
//    ,catalog="per"
//)

public class TBzLevel  implements java.io.Serializable {


    // Fields    

     private Integer bzlevelid;
     private String name;
     private Integer score;
//     private Set<TBz> TBzs = new HashSet<TBz>(0);


    // Constructors

    /** default constructor */
    public TBzLevel() {
    }

    
    /** full constructor */
    public TBzLevel(String name, Integer score, Set<TBz> TBzs) {
        this.name = name;
        this.score = score;
//        this.TBzs = TBzs;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="bzlevelid", unique=true, nullable=false)

    public Integer getBzlevelid() {
        return this.bzlevelid;
    }
    
    public void setBzlevelid(Integer bzlevelid) {
        this.bzlevelid = bzlevelid;
    }
    
    @Column(name="name", length=150)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="score")

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TBzLevel")
//
//    public Set<TBz> getTBzs() {
//        return this.TBzs;
//    }
//    
//    public void setTBzs(Set<TBz> TBzs) {
//        this.TBzs = TBzs;
//    }
//   








}
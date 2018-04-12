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
 * TZzCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_zzcategory"
    ,catalog="per"
//    ,catalog="per"
)

public class TZzCategory  implements java.io.Serializable {


    // Fields    

     private Integer zzcateid;
     private String name;
     private Integer score;
     private Set<TZz> TZzs = new HashSet<TZz>(0);


    // Constructors

    /** default constructor */
    public TZzCategory() {
    }

    
    /** full constructor */
    public TZzCategory(String name, Integer score, Set<TZz> TZzs) {
        this.name = name;
        this.score = score;
        this.TZzs = TZzs;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="zzcateid", unique=true, nullable=false)

    public Integer getZzcateid() {
        return this.zzcateid;
    }
    
    public void setZzcateid(Integer zzcateid) {
        this.zzcateid = zzcateid;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TZzCategory")

    public Set<TZz> getTZzs() {
        return this.TZzs;
    }
    
    public void setTZzs(Set<TZz> TZzs) {
        this.TZzs = TZzs;
    }
   








}
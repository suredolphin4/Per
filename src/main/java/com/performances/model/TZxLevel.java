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
 * TZxLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_zxlevel"
    ,catalog="per"
)
public class TZxLevel  implements java.io.Serializable {


    // Fields    

     private Integer zxlevelid;
     private String name;
     private Integer score;
     //private Set<TZx> TZxes = new HashSet<TZx>(0);


    // Constructors

    /** default constructor */
    public TZxLevel() {
    }

    
    /** full constructor */
    public TZxLevel(String name, Integer score, Set<TZx> TZxes) {
        this.name = name;
        this.score = score;
        //this.TZxes = TZxes;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="zxlevelid", unique=true, nullable=false)

    public Integer getZxlevelid() {
        return this.zxlevelid;
    }
    
    public void setZxlevelid(Integer zxlevelid) {
        this.zxlevelid = zxlevelid;
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
    
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TZxLevel")
//    public Set<TZx> getTZxes() {
//        return this.TZxes;
//    }
//    
//    public void setTZxes(Set<TZx> TZxes) {
//        this.TZxes = TZxes;
//    }
   








}
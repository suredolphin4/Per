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
 * TDtCate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_dtcate"
    ,catalog="per"
)

public class TDtCate  implements java.io.Serializable {


    // Fields    

     private Integer dtcateid;
     private String name;
     private Integer score;
//     private Set<TDt> TDts = new HashSet<TDt>(0);


    // Constructors

    /** default constructor */
    public TDtCate() {
    }

    
    /** full constructor */
    public TDtCate(String name, Integer score, Set<TDt> TDts) {
        this.name = name;
        this.score = score;
//        this.TDts = TDts;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="dtcateid", unique=true, nullable=false)

    public Integer getDtcateid() {
        return this.dtcateid;
    }
    
    public void setDtcateid(Integer dtcateid) {
        this.dtcateid = dtcateid;
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
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TDtCate")
//
//    public Set<TDt> getTDts() {
//        return this.TDts;
//    }
//    
//    public void setTDts(Set<TDt> TDts) {
//        this.TDts = TDts;
//    }
//   








}
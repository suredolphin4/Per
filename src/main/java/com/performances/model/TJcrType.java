package com.performances.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * TJcrType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_jcrtype"
    ,catalog="per"
)

public class TJcrType  implements java.io.Serializable {


    // Fields    

     private Integer jcrtypeid;
     private TJcr TJcr;
     private String name;
     private Integer score;


    // Constructors

    /** default constructor */
    public TJcrType() {
    }

    
    /** full constructor */
    public TJcrType(TJcr TJcr, String name, Integer score) {
        this.TJcr = TJcr;
        this.name = name;
        this.score = score;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="jcrtypeid", unique=true, nullable=false)

    public Integer getJcrtypeid() {
        return this.jcrtypeid;
    }
    
    public void setJcrtypeid(Integer jcrtypeid) {
        this.jcrtypeid = jcrtypeid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="jcrid")

    public TJcr getTJcr() {
        return this.TJcr;
    }
    
    public void setTJcr(TJcr TJcr) {
        this.TJcr = TJcr;
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
   








}
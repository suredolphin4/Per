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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


/**
 * TUserCate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_usercate"
    ,catalog="per"
)
@DynamicUpdate(true)
public class TUserCate  implements java.io.Serializable {


    // Fields    

     private Integer usercateid;
     private String name;
     private String apply;
     private Set<TUser> TUsers = new HashSet<TUser>(0);


    // Constructors

    /** default constructor */
    public TUserCate() {
    }

    
    /** full constructor */
    public TUserCate(String name, String apply, Set<TUser> TUsers) {
        this.name = name;
        this.apply = apply;
        this.TUsers = TUsers;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="usercateid", unique=true, nullable=false)

    public Integer getUsercateid() {
        return this.usercateid;
    }
    
    public void setUsercateid(Integer usercateid) {
        this.usercateid = usercateid;
    }
    
    @Column(name="name", length=150)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="apply", length=150)

    public String getApply() {
        return this.apply;
    }
    
    public void setApply(String apply) {
        this.apply = apply;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TUserCate")

    public Set<TUser> getTUsers() {
        return this.TUsers;
    }
    
    public void setTUsers(Set<TUser> TUsers) {
        this.TUsers = TUsers;
    }
   








}
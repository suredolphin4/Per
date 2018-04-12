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
 * TUserType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_usertype"
    ,catalog="per"
)
@DynamicUpdate(true)
public class TUserType  implements java.io.Serializable {


    // Fields    

     private Integer usertypeid;
     private String name;
     private Set<TUser> TUsers = new HashSet<TUser>(0);


    // Constructors

    /** default constructor */
    public TUserType() {
    }

    
    /** full constructor */
    public TUserType(String name, Set<TUser> TUsers) {
        this.name = name;
        this.TUsers = TUsers;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="usertypeid", unique=true, nullable=false)

    public Integer getUsertypeid() {
        return this.usertypeid;
    }
    
    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }
    
    @Column(name="name", length=150)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TUserType")

    public Set<TUser> getTUsers() {
        return this.TUsers;
    }
    
    public void setTUsers(Set<TUser> TUsers) {
        this.TUsers = TUsers;
    }
   








}
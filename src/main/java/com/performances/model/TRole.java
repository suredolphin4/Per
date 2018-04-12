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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * TRole entity. @author MyEclipse Persistence Tools
 * 
 * tright 和trole的多对多的关系中   非主控方用mappedBy标示    主控方用@JoinTable标示
 */
@Entity
@Table(name="t_role"
    ,catalog="per"
)

public class TRole  implements java.io.Serializable {


    // Fields    

     private Integer roleid;
     private String rolename;
     private String rolevalue;
     private String roledesc;
     private Set<TUser> TUsers = new HashSet<TUser>();
     private Set<TRight> TRights = new HashSet<TRight>();


    // Constructors

    /** default constructor */
    public TRole() {
    }

    
    /** full constructor */
    public TRole(String rolename, String rolevalue, String roledesc, Set<TUser> TUsers, Set<TRight> TRights) {
        this.rolename = rolename;
        this.rolevalue = rolevalue;
        this.roledesc = roledesc;
        this.TUsers = TUsers;
        this.TRights = TRights;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="roleid", unique=true, nullable=false)

    public Integer getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    @Column(name="rolename", length=150)

    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    @Column(name="rolevalue", length=100)

    public String getRolevalue() {
        return this.rolevalue;
    }
    
    public void setRolevalue(String rolevalue) {
        this.rolevalue = rolevalue;
    }
    
    @Column(name="roledesc", length=150)

    public String getRoledesc() {
        return this.roledesc;
    }
    
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }
@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="TRoles")

    public Set<TUser> getTUsers() {
        return this.TUsers;
    }
    
    public void setTUsers(Set<TUser> TUsers) {
        this.TUsers = TUsers;
    }
@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
//@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="role_right", catalog="per", joinColumns = { 
        @JoinColumn(name="roleid", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="rightid", nullable=false, updatable=false) })

    public Set<TRight> getTRights() {
        return this.TRights;
    }
    
    public void setTRights(Set<TRight> TRights) {
        this.TRights = TRights;
    }
   








}
package com.performance.pagemodel;
// default package

import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TDomain;
import com.performances.model.TLab;


/**
 * 著作
 * Zz entity. @author yp6124830@126.com
 */

public class Zz   extends BaseModel implements java.io.Serializable {


    // Fields    

     private Integer zzid;
     private ZzCategory zzCategory;
     private String append;
     private Integer examinestatus;
     private Timestamp examinetime;
     private String firstauthor;
     private String fiveauthor;
     private String fourauthor;
     private Timestamp ignoretime;
     private String markid;
     private Integer numauthor;
     private String otherauthor;
     private Timestamp savetime;
     private String secauthor;
     private Timestamp submittime;
     private String threeauthor;
     private String title;
     private String year;
     private Set<User> users = new HashSet<User>(0);
     private TDomain TDomain;
     private TLab TLab;
	public TDomain getTDomain() {
		return TDomain;
	}
	public void setTDomain(TDomain tDomain) {
		TDomain = tDomain;
	}
	public TLab getTLab() {
		return TLab;
	}
	public void setTLab(TLab tLab) {
		TLab = tLab;
	}
	public Integer getZzid() {
		return zzid;
	}
	public void setZzid(Integer zzid) {
		this.zzid = zzid;
	}
	public ZzCategory getZzCategory() {
		return zzCategory;
	}
	public void setZzCategory(ZzCategory zzCategory) {
		this.zzCategory = zzCategory;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public Integer getExaminestatus() {
		return examinestatus;
	}
	public void setExaminestatus(Integer examinestatus) {
		this.examinestatus = examinestatus;
	}
	public Timestamp getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(Timestamp examinetime) {
		this.examinetime = examinetime;
	}
	public String getFirstauthor() {
		return firstauthor;
	}
	public void setFirstauthor(String firstauthor) {
		this.firstauthor = firstauthor;
	}
	public String getFiveauthor() {
		return fiveauthor;
	}
	public void setFiveauthor(String fiveauthor) {
		this.fiveauthor = fiveauthor;
	}
	public String getFourauthor() {
		return fourauthor;
	}
	public void setFourauthor(String fourauthor) {
		this.fourauthor = fourauthor;
	}
	public Timestamp getIgnoretime() {
		return ignoretime;
	}
	public void setIgnoretime(Timestamp ignoretime) {
		this.ignoretime = ignoretime;
	}
	public String getMarkid() {
		return markid;
	}
	public void setMarkid(String markid) {
		this.markid = markid;
	}
	public Integer getNumauthor() {
		return numauthor;
	}
	public void setNumauthor(Integer numauthor) {
		this.numauthor = numauthor;
	}
	public String getOtherauthor() {
		return otherauthor;
	}
	public void setOtherauthor(String otherauthor) {
		this.otherauthor = otherauthor;
	}
	public Timestamp getSavetime() {
		return savetime;
	}
	public void setSavetime(Timestamp savetime) {
		this.savetime = savetime;
	}
	public String getSecauthor() {
		return secauthor;
	}
	public void setSecauthor(String secauthor) {
		this.secauthor = secauthor;
	}
	public Timestamp getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Timestamp submittime) {
		this.submittime = submittime;
	}
	public String getThreeauthor() {
		return threeauthor;
	}
	public void setThreeauthor(String threeauthor) {
		this.threeauthor = threeauthor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
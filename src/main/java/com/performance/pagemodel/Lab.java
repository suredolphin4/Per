package com.performance.pagemodel;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.performances.model.TBz;
import com.performances.model.TDomain;
import com.performances.model.TDt;
import com.performances.model.TGh;
//import com.performances.model.TJl;
import com.performances.model.TLw;
import com.performances.model.TRj;
import com.performances.model.TSk;
import com.performances.model.TXm;
import com.performances.model.TYj;
import com.performances.model.TZl;
import com.performances.model.TZx;
import com.performances.model.TZz;
/**
 * 研究室 
 * @author peng
 *
 */
public class Lab  extends BaseModel implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer labid;
     private String name;
 	private String code;
 	private String domainname;
 	private Integer domaincode;
 	  private Set<User> users = new HashSet<User>(0);
      private Set<TDt> TDts = new HashSet<TDt>(0);
      private Set<TGh> TGhs = new HashSet<TGh>(0);
      private Set<TBz> TBzs = new HashSet<TBz>(0);
//      private Set<TJl> TJls = new HashSet<TJl>(0);
      private Set<TLw> TLws = new HashSet<TLw>(0);
      private Set<TRj> TRjs = new HashSet<TRj>(0);
      private Set<TSk> TSks = new HashSet<TSk>(0);
      private Set<TXm> TXms = new HashSet<TXm>(0);
      private Set<TYj> TYjs = new HashSet<TYj>(0);
      private Set<TZl> TZls = new HashSet<TZl>(0);
      private Set<TZx> TZxs = new HashSet<TZx>(0);
      private Set<TZz> TZzs = new HashSet<TZz>(0);
      private Domain domain;
 	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
	public Integer getDomaincode() {
		return domaincode;
	}
	public void setDomaincode(Integer domaincode) {
		this.domaincode = domaincode;
	}
	
	

   
	public Set<TDt> getTDts() {
		return TDts;
	}
	public void setTDts(Set<TDt> tDts) {
		TDts = tDts;
	}
	public Set<TGh> getTGhs() {
		return TGhs;
	}
	public void setTGhs(Set<TGh> tGhs) {
		TGhs = tGhs;
	}
	public Set<TBz> getTBzs() {
		return TBzs;
	}
	public void setTBzs(Set<TBz> tBzs) {
		TBzs = tBzs;
	}
//	public Set<TJl> getTJls() {
//		return TJls;
//	}
//	public void setTJls(Set<TJl> tJls) {
//		TJls = tJls;
//	}
	public Set<TLw> getTLws() {
		return TLws;
	}
	public void setTLws(Set<TLw> tLws) {
		TLws = tLws;
	}
	public Set<TRj> getTRjs() {
		return TRjs;
	}
	public void setTRjs(Set<TRj> tRjs) {
		TRjs = tRjs;
	}
	public Set<TSk> getTSks() {
		return TSks;
	}
	public void setTSks(Set<TSk> tSks) {
		TSks = tSks;
	}
	public Set<TXm> getTXms() {
		return TXms;
	}
	public void setTXms(Set<TXm> tXms) {
		TXms = tXms;
	}
	public Set<TYj> getTYjs() {
		return TYjs;
	}
	public void setTYjs(Set<TYj> tYjs) {
		TYjs = tYjs;
	}
	public Set<TZl> getTZls() {
		return TZls;
	}
	public void setTZls(Set<TZl> tZls) {
		TZls = tZls;
	}
	public Set<TZx> getTZxs() {
		return TZxs;
	}
	public void setTZxs(Set<TZx> tZxs) {
		TZxs = tZxs;
	}
	public Set<TZz> getTZzs() {
		return TZzs;
	}
	public void setTZzs(Set<TZz> tZzs) {
		TZzs = tZzs;
	}
	public Integer getLabid() {
		return labid;
	}
	public void setLabid(Integer labid) {
		this.labid = labid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
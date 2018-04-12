package com.performance.pagemodel;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;


/**
 * User entity. @author yp6124830@126.com
 */

public class User  extends BaseModel implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	private Integer userid;
     private UserCate userCate;
     private UserType userType;
     private String name;
     private String pwd;
     //是否公有
     private String comment;
     private long [] rightSum;
     private boolean superAdmin;
     private String department;//崔老师“不用去管”
     private String usercode;
     private String domain;
     private String domainnum;
     private String lab;
     private String userTy;
     private String userCategory;
     private Timestamp savetime;
     

	public Timestamp getSavetime() {
 		return savetime;
 	}

 	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public void setSavetime(Timestamp savetime) {
 		this.savetime = savetime;
 	}
     private Set<Zl> zls = new HashSet<Zl>();
     private Set<Xm> xms = new HashSet<Xm>();
     private Set<Zx> zxes = new HashSet<Zx>();
     private Set<Domain> domains = new HashSet<Domain>();
     private Set<Lw> lws = new HashSet<Lw>();
     private Set<Jl> jls = new HashSet<Jl>();
     private Set<Lab> labs = new HashSet<Lab>();
     private Set<Role> roles = new HashSet<Role>();
     private Set<Yj> yjs = new HashSet<Yj>();
     private Set<Rj> rjs = new HashSet<Rj>();
     private Set<Sk> sks = new HashSet<Sk>();
     private Set<Dt> dts = new HashSet<Dt>();
     private Set<Gh> ghs = new HashSet<Gh>();
     private Set<Bz> bzs = new HashSet<Bz>();
     public String getUserTy() {
		return userTy;
	}
	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getLabnum() {
		return labnum;
	}
	public void setLabnum(String labnum) {
		this.labnum = labnum;
	}
	private String labnum;
     public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public long[] getRightSum() {
		return rightSum;
	}
	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}
	public boolean isSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public UserCate getUserCate() {
		return userCate;
	}
	public void setUserCate(UserCate userCate) {
		this.userCate = userCate;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Zl> getZls() {
		return zls;
	}
	public void setZls(Set<Zl> zls) {
		this.zls = zls;
	}
	public Set<Xm> getXms() {
		return xms;
	}
	public void setXms(Set<Xm> xms) {
		this.xms = xms;
	}
	public Set<Zx> getZxes() {
		return zxes;
	}
	public void setZxes(Set<Zx> zxes) {
		this.zxes = zxes;
	}
	public Set<Domain> getDomains() {
		return domains;
	}
	public void setDomains(Set<Domain> domains) {
		this.domains = domains;
	}
	
	public String getDomainnum() {
		return domainnum;
	}
	public void setDomainnum(String domainnum) {
		this.domainnum = domainnum;
	}
	public Set<Lw> getLws() {
		return lws;
	}
	public void setLws(Set<Lw> lws) {
		this.lws = lws;
	}
	public Set<Jl> getJls() {
		return jls;
	}
	public void setJls(Set<Jl> jls) {
		this.jls = jls;
	}
	public Set<Lab> getLabs() {
		return labs;
	}
	public void setLabs(Set<Lab> labs) {
		this.labs = labs;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Yj> getYjs() {
		return yjs;
	}
	public void setYjs(Set<Yj> yjs) {
		this.yjs = yjs;
	}
	public Set<Rj> getRjs() {
		return rjs;
	}
	public void setRjs(Set<Rj> rjs) {
		this.rjs = rjs;
	}
	public Set<Sk> getSks() {
		return sks;
	}
	public void setSks(Set<Sk> sks) {
		this.sks = sks;
	}
	public Set<Dt> getDts() {
		return dts;
	}
	public void setDts(Set<Dt> dts) {
		this.dts = dts;
	}
	public Set<Gh> getGhs() {
		return ghs;
	}
	public void setGhs(Set<Gh> ghs) {
		this.ghs = ghs;
	}

	public Set<Bz> getBzs() {
		return bzs;
	}
	public void setBzs(Set<Bz> bzs) {
		this.bzs = bzs;
	}
     
     
     
     /**
 	 * 计算用户权限总和
     * @param listRoles 
 	 */
     public void calculateRightSum(List<Role> roles) {
  		int pos = 0;
  		long code = 0 ;
  		for(Role role : roles){
  			
  			//判断是否超级管理员
  			if("-1".equals(role.getRolevalue())){
  				this.superAdmin = true ;
  				//释放资源
  				roles = null ;
  				return ;
  			}
  			for(Right r : role.getRights()){
  				pos=r.getRightpos();
  				code = r.getRightcode();
  				rightSum[pos] = rightSum[pos] | code ;
  			}
  		}
  		//释放资源
  		roles = null ;
  	}

 	/**
 	 * 判断用户是否具有指定权限
 	 */
 	public boolean hasRight(Right r) {
 		int pos = r.getRightpos();
 		long code = r.getRightcode();
 		return !((rightSum[pos] & code)  == 0);
 	}








}
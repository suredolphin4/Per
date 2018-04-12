package com.performance.pagemodel;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * Right entity. @author yp6124830@126.com
 */

public class Right  extends BaseModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 原始属性
	 */
	private Integer rightid;
	private String rightname;
	private String righturl;
	private String rightdesc;
	private Long rightcode;
	private Integer rightpos;
	public Integer getRightpos() {
		return rightpos;
	}

	public void setRightpos(Integer rightpos) {
		this.rightpos = rightpos;
	}

	private Set<Role> roles = new HashSet<Role>();
	private Integer common;
	

	public Integer getCommon() {
		return common;
	}

	public void setCommon(Integer common) {
		this.common = common;
	}

	public Integer getRightid() {
		return rightid;
	}

	public void setRightid(Integer rightid) {
		this.rightid = rightid;
	}

	public String getRightname() {
		return rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public String getRighturl() {
		return righturl;
	}

	public void setRighturl(String righturl) {
		this.righturl = righturl;
	}

	public String getRightdesc() {
		return rightdesc;
	}

	public void setRightdesc(String rightdesc) {
		this.rightdesc = rightdesc;
	}


	public Long getRightcode() {
		return rightcode;
	}

	public void setRightcode(Long rightcode) {
		this.rightcode = rightcode;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
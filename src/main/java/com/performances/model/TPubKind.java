package com.performances.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

/**
 * 刊物类别
 * TPubKind Entity, @author zheng
 *
 */
@Entity
@Table(name="t_pubkind", catalog="per")
public class TPubKind implements java.io.Serializable {
	private Integer id;
	private String pubkind;
	
	public TPubKind(){
		
	}

	public TPubKind(Integer id, String pubkind) {
		this.id = id;
		this.pubkind = pubkind;
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id", unique=true, nullable=false)
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@Column(name="pubkind", length = 45)
	public String getPubkind(){
		return this.pubkind;
	}
	
	public void setPubkind(String pubkind){
		this.pubkind = pubkind;
	}
}

package com.performance.pagemodel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Research extends BaseModel implements java.io.Serializable {
	private Integer id;
	private String username;
	private String usercode;
	private String domain;
	private String search_usercode;  //用于高级检索--编号
	private int paper_total_asl;
	private int paper_total;
	private int sci_pub_total_asl;
	private int sci_pub_total;
	private int sci_accept_total_asl;
	private int sci_accept_total;
	private int inland_total_asl;
	private int inland_total;
	private int ei_total_asl;
	private int ei_total;
	private int istp_total;
	private int abroad_total;
	private int books_total;
	private int software_total;
	private int patent_total;
	private int award_total;
	private int report_total;
	private int plan_total;
	private int thirdAssess_total;
	private int standard_total;
	private int publish_total;
	private int achievetrans_total;
	private int map_total;
	private String dec_level;

	public String getSearch_usercode() {
		return search_usercode;
	}

	public void setSearch_usercode(String search_usercode) {
		this.search_usercode = search_usercode;
	}

	public Integer getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public int getPaper_total_asl() {
		return paper_total_asl;
	}
	public void setPaper_total_asl(int paper_total_asl) {
		this.paper_total_asl = paper_total_asl;
	}
	
	public int getPaper_total() {
		return paper_total;
	}
	public void setPaper_total(int paper_total) {
		this.paper_total = paper_total;
	}
	
	public int getSci_pub_total_asl() {
		return sci_pub_total_asl;
	}
	public void setSci_pub_total_asl(int sci_pub_total_asl) {
		this.sci_pub_total_asl = sci_pub_total_asl;
	}
	
	public int getSci_pub_total() {
		return sci_pub_total;
	}
	public void setSci_pub_total(int sci_pub_total) {
		this.sci_pub_total = sci_pub_total;
	}
	
	public int getSci_accept_total_asl() {
		return sci_accept_total_asl;
	}
	public void setSci_accept_total_asl(int sci_accept_total_asl) {
		this.sci_accept_total_asl = sci_accept_total_asl;
	}
	
	public int getSci_accept_total() {
		return sci_accept_total;
	}
	public void setSci_accept_total(int sci_accept_total) {
		this.sci_accept_total = sci_accept_total;
	}
	
	public int getEi_total_asl() {
		return ei_total_asl;
	}
	public void setEi_total_asl(int ei_total_asl) {
		this.ei_total_asl = ei_total_asl;
	}
	
	public int getEi_total() {
		return ei_total;
	}
	public void setEi_total(int ei_total) {
		this.ei_total = ei_total;
	}
	
	public int getInland_total_asl() {
		return inland_total_asl;
	}
	public void setInland_total_asl(int inland_total_asl) {
		this.inland_total_asl = inland_total_asl;
	}
	
	public int getInland_total() {
		return inland_total;
	}
	public void setInland_total(int inland_total) {
		this.inland_total = inland_total;
	}
	
	public int getIstp_total() {
		return istp_total;
	}
	public void setIstp_total(int istp_total) {
		this.istp_total = istp_total;
	}
	
	public int getAbroad_total() {
		return abroad_total;
	}
	public void setAbroad_total(int abroad_total) {
		this.abroad_total = abroad_total;
	}
	
	public int getBooks_total() {
		return books_total;
	}
	public void setBooks_total(int books_total) {
		this.books_total = books_total;
	}
	
	public int getSoftware_total() {
		return software_total;
	}
	public void setSoftware_total(int software_total) {
		this.software_total = software_total;
	}
	
	public int getPatent_total() {
		return patent_total;
	}
	public void setPatent_total(int patent_total) {
		this.patent_total = patent_total;
	}
	
	public int getAward_total() {
		return award_total;
	}
	public void setAward_total(int award_total) {
		this.award_total = award_total;
	}
	
	public int getReport_total() {
		return report_total;
	}
	public void setReport_total(int report_total) {
		this.report_total = report_total;
	}
	
	public int getPlan_total() {
		return plan_total;
	}
	public void setPlan_total(int plan_total) {
		this.plan_total = plan_total;
	}

	public int getThirdAssess_total() {
		return thirdAssess_total;
	}
	public void setThirdAssess_total(int thirdAssess_total) {
		this.thirdAssess_total = thirdAssess_total;
	}

	public int getStandard_total() {
		return standard_total;
	}
	public void setStandard_total(int standard_total) {
		this.standard_total = standard_total;
	}

	public int getPublish_total() {	return publish_total;}
	public void setPublish_total(int publish_total) {this.publish_total = publish_total;}

	public int getAchievetrans_total() {	return achievetrans_total;}
	public void setAchievetrans_total(int achievetrans_total) {this.achievetrans_total = achievetrans_total;}

	public int getMap_total() {	return map_total;}
	public void setMap_total(int map_total) {this.map_total = map_total;}

	public String getDec_level() {
		return dec_level;
	}
	public void setDec_level(String dec_level) {
		this.dec_level = dec_level;
	}
}

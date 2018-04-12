package com.performances.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * TResearch entity. @author Linyh
 */
@Entity
@Table(name="t_achievement"
    ,catalog="per"
)
@DynamicUpdate(true) // 允许部分更新
public class TResearch implements java.io.Serializable {
		// Fields    

		private Integer id;
		private String username;
		private String usercode;
		private String domain;
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
		
		/** default constructor */
	    public TResearch() {
	    }
	    
	    public TResearch(Integer id, String username, String usercode, String domain, int paper_total_asl,
				int paper_total, int sci_pub_total_asl, int sci_pub_total,
				int sci_accept_total_asl, int sci_accept_total, int inland_total_asl,
				int inland_total, int ei_total_asl, int ei_total,
				int istp_total, int abroad_total, int books_total,
				int software_total, int patent_total, int award_total,
				int report_total, int plan_total,int thirdAssess_total, int standard_total,int publish_total,int achievetrans_total,int map_total,
				String dec_level) {
			super();
			
			this.id=id;
			this.username=username;
			this.usercode=usercode;
			this.domain=domain;
			this.paper_total_asl=paper_total_asl;
			this.paper_total=paper_total;
			this.sci_pub_total_asl=sci_pub_total_asl;
			this.sci_pub_total=sci_pub_total;
			this.sci_accept_total_asl=sci_accept_total_asl;
			this.sci_accept_total=sci_accept_total;
			this.inland_total_asl=inland_total_asl;
			this.inland_total=inland_total;
			this.ei_total_asl=ei_total_asl;
			this.ei_total=ei_total;
			this.istp_total=istp_total;
			this.abroad_total=abroad_total;
			this.books_total=books_total;
			this.software_total=software_total;
			this.patent_total=patent_total;
			this.report_total=report_total;
			this.report_total=report_total;
			this.plan_total=plan_total;
			this.thirdAssess_total=thirdAssess_total;
			this.standard_total=standard_total;
			this.publish_total=publish_total;
			this.achievetrans_total=achievetrans_total;
			this.map_total = map_total;
			this.dec_level=dec_level;
		}
	    
	    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
	    @Column(name="id", unique=true, nullable=false)
	    public Integer getId() {
	        return this.id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    @Column(name = "username", length = 255)
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		@Column(name = "usercode", length = 150)
		public String getUsercode() {
			return usercode;
		}
		public void setUsercode(String usercode) {
			this.usercode = usercode;
		}
		
		@Column(name = "domain", length = 255)
		public String getDomain() {
			return domain;
		}
		public void setDomain(String domain) {
			this.domain = domain;
		}
		
		@Column(name = "paper_total_asl")
		public int getPaper_total_asl() {
			return paper_total_asl;
		}
		public void setPaper_total_asl(int paper_total_asl) {
			this.paper_total_asl = paper_total_asl;
		}
		
		@Column(name = "paper_total")
		public int getPaper_total() {
			return paper_total;
		}
		public void setPaper_total(int paper_total) {
			this.paper_total = paper_total;
		}
		
		@Column(name = "sci_pub_total_asl")
		public int getSci_pub_total_asl() {
			return sci_pub_total_asl;
		}
		public void setSci_pub_total_asl(int sci_pub_total_asl) {
			this.sci_pub_total_asl = sci_pub_total_asl;
		}
		
		@Column(name = "sci_pub_total")
		public int getSci_pub_total() {
			return sci_pub_total;
		}
		public void setSci_pub_total(int sci_pub_total) {
			this.sci_pub_total = sci_pub_total;
		}
		
		@Column(name = "sci_accept_total_asl")
		public int getSci_accept_total_asl() {
			return sci_accept_total_asl;
		}
		public void setSci_accept_total_asl(int sci_accept_total_asl) {
			this.sci_accept_total_asl = sci_accept_total_asl;
		}
		
		@Column(name = "sci_accept_total")
		public int getSci_accept_total() {
			return sci_accept_total;
		}
		public void setSci_accept_total(int sci_accept_total) {
			this.sci_accept_total = sci_accept_total;
		}
		
		@Column(name = "ei_total_asl")
		public int getEi_total_asl() {
			return ei_total_asl;
		}
		public void setEi_total_asl(int ei_total_asl) {
			this.ei_total_asl = ei_total_asl;
		}
		
		@Column(name = "ei_total")
		public int getEi_total() {
			return ei_total;
		}
		public void setEi_total(int ei_total) {
			this.ei_total = ei_total;
		}
		
		@Column(name = "inland_total_asl")
		public int getInland_total_asl() {
			return inland_total_asl;
		}
		public void setInland_total_asl(int inland_total_asl) {
			this.inland_total_asl = inland_total_asl;
		}
		
		@Column(name = "inland_total")
		public int getInland_total() {
			return inland_total;
		}
		public void setInland_total(int inland_total) {
			this.inland_total = inland_total;
		}
		
		@Column(name = "istp_total")
		public int getIstp_total() {
			return istp_total;
		}
		public void setIstp_total(int istp_total) {
			this.istp_total = istp_total;
		}
		
		@Column(name = "abroad_total")
		public int getAbroad_total() {
			return abroad_total;
		}
		public void setAbroad_total(int abroad_total) {
			this.abroad_total = abroad_total;
		}
		
		@Column(name = "books_total")
		public int getBooks_total() {
			return books_total;
		}
		public void setBooks_total(int books_total) {
			this.books_total = books_total;
		}
		
		@Column(name = "software_total")
		public int getSoftware_total() {
			return software_total;
		}
		public void setSoftware_total(int software_total) {
			this.software_total = software_total;
		}
		
		@Column(name = "patent_total")
		public int getPatent_total() {
			return patent_total;
		}
		public void setPatent_total(int patent_total) {
			this.patent_total = patent_total;
		}
		
		@Column(name = "award_total")
		public int getAward_total() {
			return award_total;
		}
		public void setAward_total(int award_total) {
			this.award_total = award_total;
		}
		
		@Column(name = "report_total")
		public int getReport_total() {
			return report_total;
		}
		public void setReport_total(int report_total) {
			this.report_total = report_total;
		}
		
		@Column(name = "plan_total")
		public int getPlan_total() {
			return plan_total;
		}
		public void setPlan_total(int plan_total) {
			this.plan_total = plan_total;
		}

 	@Column(name = "thirdAssess_total")
	public int getThirdAssess_total() {
		return thirdAssess_total;
	}
	public void setThirdAssess_total(int thirdAssess_total) {
		this.thirdAssess_total = thirdAssess_total;
	}


	@Column(name = "standard_total")
		public int getStandard_total() {
			return standard_total;
		}
		public void setStandard_total(int standard_total) {
			this.standard_total = standard_total;
		}

		@Column(name = "publish_total")
		public int getPublish_total() {	return publish_total;}
		public void setPublish_total(int publish_total) {this.publish_total = publish_total;}

	    @Column(name = "achievetrans_total")
	    public int getAchievetrans_total() {	return achievetrans_total;}
	    public void setAchievetrans_total(int achievetrans_total) {this.achievetrans_total = achievetrans_total;}

	    @Column(name = "map_total")
	    public int getMap_total() {	return map_total;}
	    public void setMap_total(int map_total) {this.map_total = map_total;}


	    @Column(name = "dec_level", length=255)
		public String getDec_level() {
			return dec_level;
		}
		public void setDec_level(String dec_level) {
			this.dec_level = dec_level;
		}
}

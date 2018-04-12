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
 * 专利
 * Zl entity. @author yp6124830@126.com
 */


public class Zl  extends BaseModel  implements java.io.Serializable {


	// Fields    
			private Integer id;
			private String name;
		    private Integer year;
		    private String code;
		    private String date;
		    private String type;
		    private String owner;
		    private String other;
		    private String rank;
		    private String firstInvector;
		    private String secondInvector;
		    private String thirdInvector;
		    private String fourthInvector;
		    private String fifthInvector;
		    private String otherInvector;
		    private String audit;
		    private Timestamp savetime;
		    private String firstInvectorcode;
		    private String secondInvectorcode;
		    private String thirdInvectorcode;
		    private String fourthInvectorcode;
		    private String fifthInvectorcode;
		    private String otherInvectorcode;
		    
		    // 过滤条件
			private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
			private String s_begin_year;
			private String s_end_year;
			private String s_lw_name;
			private String s_lw_author;
			private String s_which_lab;
			private String s_which_domain;
			private String s_lw_authorcode;
			
		    // 获取分数
		    private String 	firstauthorscore;
		    private String	secauthorscore;
		    private String  threeauthorscore;
		    private String	fourauthorscore;
		    private String	fiveauthorscore;
		    private String	otherauthorscore;

	public String getS_lw_authorcode() {
		return s_lw_authorcode;
	}

	public void setS_lw_authorcode(String s_lw_authorcode) {
		this.s_lw_authorcode = s_lw_authorcode;
	}


	// Property accessors
		    public Integer getId() {
		        return this.id;
		    }
		    public void setId(Integer id) {
		        this.id = id;
		    }
		    
		    public String getName() {
		        return this.name;
		    }
		    public void setName(String name) {
		        this.name = name;
		    }
		    
		    public Integer getYear() {
		        return this.year;
		    }
		    public void setYear(Integer year) {
		        this.year = year;
		    }
		    
		    public String getCode() {
		        return this.code;
		    }
		    public void setCode(String code) {
		        this.code = code;
		    }
		    
		    public String getDate() {
		        return this.date;
		    }
		    public void setDate(String date) {
		        this.date = date;
		    }
		    
		    public String getType() {
		        return this.type;
		    }
		    public void setType(String type) {
		        this.type = type;
		    }
		    
		    public String getOwner() {
		        return this.owner;
		    }
		    public void setOwner(String owner) {
		        this.owner = owner;
		    }
		    
		    public String getOther() {
		        return this.other;
		    }
		    public void setOther(String other) {
		        this.other = other;
		    }
		    
		    public String getRank() {
		        return this.rank;
		    }
		    public void setRank(String rank) {
		        this.rank = rank;
		    }
		    
		    public String getFirstInvector() {
		        return this.firstInvector;
		    }
		    public void setFirstInvector(String firstInvector) {
		        this.firstInvector = firstInvector;
		    }
		    
		    public String getSecondInvector() {
		        return this.secondInvector;
		    }
		    public void setSecondInvector(String secondInvector) {
		        this.secondInvector = secondInvector;
		    }
		    
		    public String getThirdInvector() {
		        return this.thirdInvector;
		    }
		    public void setThirdInvector(String thirdInvector) {
		        this.thirdInvector = thirdInvector;
		    }
		    
		    public String getFourthInvector() {
		        return this.fourthInvector;
		    }
		    public void setFourthInvector(String fourthInvector) {
		        this.fourthInvector = fourthInvector;
		    }
		    
		    public String getFifthInvector() {
		        return this.fifthInvector;
		    }
		    public void setFifthInvector(String fifthInvector) {
		        this.fifthInvector = fifthInvector;
		    }
		    
		    public String getOtherInvector() {
		        return this.otherInvector;
		    }
		    public void setOtherInvector(String otherInvector) {
		        this.otherInvector = otherInvector;
		    }
		    
		    public String getAudit() {
		        return this.audit;
		    }
		    public void setAudit(String audit) {
		        this.audit = audit;
		    }
		    
		    public Timestamp getSavetime() {
		        return this.savetime;
		    }
		    public void setSavetime(Timestamp savetime) {
		        this.savetime = savetime;
		    }
		    
		    public String getFirstInvectorcode() {
		        return this.firstInvectorcode;
		    }
		    public void setFirstInvectorcode(String firstInvectorcode) {
		        this.firstInvectorcode = firstInvectorcode;
		    }
		    
		    public String getSecondInvectorcode() {
		        return this.secondInvectorcode;
		    }
		    public void setSecondInvectorcode(String secondInvectorcode) {
		        this.secondInvectorcode = secondInvectorcode;
		    }
		    
		    public String getThirdInvectorcode() {
		        return this.thirdInvectorcode;
		    }
		    public void setThirdInvectorcode(String thirdInvectorcode) {
		        this.thirdInvectorcode = thirdInvectorcode;
		    }
		    
		    public String getFourthInvectorcode() {
		        return this.fourthInvectorcode;
		    }
		    public void setFourthInvectorcode(String fourthInvectorcode) {
		        this.fourthInvectorcode = fourthInvectorcode;
		    }
		    
		    public String getFifthInvectorcode() {
		        return this.fifthInvectorcode;
		    }
		    public void setFifthInvectorcode(String fifthInvectorcode) {
		        this.fifthInvectorcode = fifthInvectorcode;
		    }
		    
		    public String getOtherInvectorcode() {
		        return this.otherInvectorcode;
		    }
		    public void setOtherInvectorcode(String otherInvectorcode) {
		        this.otherInvectorcode = otherInvectorcode;
		    }
		    
		    // 过滤条件
		    public String getS_begin_year() {
				return s_begin_year;
			}

			public void setS_begin_year(String s_begin_year) {
				this.s_begin_year = s_begin_year;
			}

			public String getS_end_year() {
				return s_end_year;
			}

			public void setS_end_year(String s_end_year) {
				this.s_end_year = s_end_year;
			}

			public String getS_lw_name() {
				return s_lw_name;
			}

			public void setS_lw_name(String s_lw_name) {
				this.s_lw_name = s_lw_name;
			}

			public String getS_lw_author() {
				return s_lw_author;
			}

			public void setS_lw_author(String s_lw_author) {
				this.s_lw_author = s_lw_author;
			}

			public String getS_which_lab() {
				return s_which_lab;
			}

			public void setS_which_lab(String s_which_lab) {
				this.s_which_lab = s_which_lab;
			}

			public String getS_which_domain() {
				return s_which_domain;
			}

			public void setS_which_domain(String s_which_domain) {
				this.s_which_domain = s_which_domain;
			}
			
			//绩效
			public String getFirstauthorscore() {
				return firstauthorscore;
			}
			public void setFirstauthorscore(String firstauthorscore) {
				this.firstauthorscore = firstauthorscore;
			}
			public String getSecauthorscore() {
				return secauthorscore;
			}
			public void setSecauthorscore(String secauthorscore) {
				this.secauthorscore = secauthorscore;
			}
			public String getThreeauthorscore() {
				return threeauthorscore;
			}
			public void setThreeauthorscore(String threeauthorscore) {
				this.threeauthorscore = threeauthorscore;
			}
			public String getFourauthorscore() {
				return fourauthorscore;
			}
			public void setFourauthorscore(String fourauthorscore) {
				this.fourauthorscore = fourauthorscore;
			}
			public String getFiveauthorscore() {
				return fiveauthorscore;
			}
			public void setFiveauthorscore(String fiveauthorscore) {
				this.fiveauthorscore = fiveauthorscore;
			}
			public String getOtherauthorscore() {
				return otherauthorscore;
			}
			public void setOtherauthorscore(String otherauthorscore) {
				this.otherauthorscore = otherauthorscore;
			}
			
}

package com.performances.model;
// default package

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 * TAchievetrans entity. @author zhangshuo
 */
@Entity
@Table(name="t_achievetrans"
    ,catalog="per"
)

public class TAchievetrans implements java.io.Serializable {

	// Fields
		private Integer id;
		private String name;
	    private Integer year;
	    private String code;
	    private String date;
	    private String type;
	    private String owner;
	    private Double achieveMoney;
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

	    // Constructors

	    /** default constructor */
	    public TAchievetrans() {
	    }


	    /** full constructor */
	    public TAchievetrans(Integer id, String name, Integer year, String code, String date,
							 String type, String owner, Double achieveMoney, String rank, String firstInvector,
							 String secondInvector, String thirdInvector, String fourthInvector, String fifthInvector,
							 String otherInvector, String audit, Timestamp savetime, String firstInvectorcode,
							 String secondInvectorcode, String thirdInvectorcode, String fourthInvectorcode, String fifthInvectorcode,
							 String otherInvectorcode) {
	        this.id = id;
	        this.name = name;
	        this.year = year;
	        this.code = code;
	        this.date = date;
	        this.type = type;
	        this.owner = owner;
	        this.achieveMoney = achieveMoney;
	        this.rank = rank;
	        this.firstInvector = firstInvector;
	        this.secondInvector = secondInvector;
	        this.thirdInvector = thirdInvector;
	        this.fourthInvector = fourthInvector;
	        this.fifthInvector = fifthInvector;
	        this.otherInvector = otherInvector;
	        this.audit = audit;
	        this.savetime = savetime;
	        this.firstInvectorcode = firstInvectorcode;
	        this.secondInvectorcode = secondInvectorcode;
	        this.thirdInvectorcode = thirdInvectorcode;
	        this.fourthInvectorcode = fourthInvectorcode;
	        this.fifthInvectorcode = fifthInvectorcode;
	        this.otherInvectorcode = otherInvectorcode;
	    }

	   
	    // Property accessors
	    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
	    @Column(name="id", unique=true, nullable=false)
	    public Integer getId() {
	        return this.id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
	    @Column(name="name")
	    public String getName() {
	        return this.name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    @Column(name="year")
	    public Integer getYear() {
	        return this.year;
	    }
	    public void setYear(Integer year) {
	        this.year = year;
	    }
	    
	    @Column(name="code", length=128)
	    public String getCode() {
	        return this.code;
	    }
	    public void setCode(String code) {
	        this.code = code;
	    }
	    
	    @Column(name="date", length=64)
	    public String getDate() {
	        return this.date;
	    }
	    public void setDate(String date) {
	        this.date = date;
	    }
	    
	    @Column(name="type", length=128)
	    public String getType() {
	        return this.type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }
	    
	    @Column(name="owner", length=128)
	    public String getOwner() {
	        return this.owner;
	    }
	    public void setOwner(String owner) {
	        this.owner = owner;
	    }
	    
	    @Column(name="achieveMoney",length=12)
	    public Double getAchieveMoney() {
	        return this.achieveMoney;
	    }
	    public void setAchieveMoney(Double achieveMoney) {
	        this.achieveMoney = achieveMoney;
	    }
	    
	    @Column(name="rank", length=32)
	    public String getRank() {
	        return this.rank;
	    }
	    public void setRank(String rank) {
	        this.rank = rank;
	    }
	    
	    @Column(name="firstInvector", length=32)
	    public String getFirstInvector() {
	        return this.firstInvector;
	    }
	    public void setFirstInvector(String firstInvector) {
	        this.firstInvector = firstInvector;
	    }
	    
	    @Column(name="secondInvector", length=32)
	    public String getSecondInvector() {
	        return this.secondInvector;
	    }
	    public void setSecondInvector(String secondInvector) {
	        this.secondInvector = secondInvector;
	    }
	    
	    @Column(name="thirdInvector", length=32)
	    public String getThirdInvector() {
	        return this.thirdInvector;
	    }
	    public void setThirdInvector(String thirdInvector) {
	        this.thirdInvector = thirdInvector;
	    }
	    
	    @Column(name="fourthInvector", length=32)
	    public String getFourthInvector() {
	        return this.fourthInvector;
	    }
	    public void setFourthInvector(String fourthInvector) {
	        this.fourthInvector = fourthInvector;
	    }
	    
	    @Column(name="fifthInvector", length=32)
	    public String getFifthInvector() {
	        return this.fifthInvector;
	    }
	    public void setFifthInvector(String fifthInvector) {
	        this.fifthInvector = fifthInvector;
	    }
	    
	    @Column(name="otherInvector", length=1024)
	    public String getOtherInvector() {
	        return this.otherInvector;
	    }
	    public void setOtherInvector(String otherInvector) {
	        this.otherInvector = otherInvector;
	    }
	    
	    @Column(name="audit", length=32)
	    public String getAudit() {
	        return this.audit;
	    }
	    public void setAudit(String audit) {
	        this.audit = audit;
	    }
	    
	    @Column(name="savetime", length = 19)
	    public Timestamp getSavetime() {
	        return this.savetime;
	    }
	    public void setSavetime(Timestamp savetime) {
	        this.savetime = savetime;
	    }
	    
	    @Column(name="firstInvectorcode", length=32)
	    public String getFirstInvectorcode() {
	        return this.firstInvectorcode;
	    }
	    public void setFirstInvectorcode(String firstInvectorcode) {
	        this.firstInvectorcode = firstInvectorcode;
	    }
	    
	    @Column(name="secondInvectorcode", length=32)
	    public String getSecondInvectorcode() {
	        return this.secondInvectorcode;
	    }
	    public void setSecondInvectorcode(String secondInvectorcode) {
	        this.secondInvectorcode = secondInvectorcode;
	    }
	    
	    @Column(name="thirdInvectorcode", length=32)
	    public String getThirdInvectorcode() {
	        return this.thirdInvectorcode;
	    }
	    public void setThirdInvectorcode(String thirdInvectorcode) {
	        this.thirdInvectorcode = thirdInvectorcode;
	    }
	    
	    @Column(name="fourthInvectorcode", length=32)
	    public String getFourthInvectorcode() {
	        return this.fourthInvectorcode;
	    }
	    public void setFourthInvectorcode(String fourthInvectorcode) {
	        this.fourthInvectorcode = fourthInvectorcode;
	    }
	    
	    @Column(name="fifthInvectorcode", length=32)
	    public String getFifthInvectorcode() {
	        return this.fifthInvectorcode;
	    }
	    public void setFifthInvectorcode(String fifthInvectorcode) {
	        this.fifthInvectorcode = fifthInvectorcode;
	    }
	    
	    @Column(name="otherInvectorcode", length=1024)
	    public String getOtherInvectorcode() {
	        return this.otherInvectorcode;
	    }
	    public void setOtherInvectorcode(String otherInvectorcode) {
	        this.otherInvectorcode = otherInvectorcode;
	    }
	    
		private Collection<TAchievetransScore> score;
		@OneToMany(cascade={CascadeType.ALL}) 
		@JoinColumn(name="fid", insertable = false,updatable = false)
		public Collection<TAchievetransScore> getScore(){
			return score;
		}
		public void setScore(Collection<TAchievetransScore> score){
			this.score = score;
		}
}
package com.performances.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 */
@Entity
@Table(name="t_publish"
    ,catalog="per"
)
@DynamicUpdate(true) // 允许部分更新
public class TPublish implements java.io.Serializable {


    // Fields

     private Integer publishid;
     private String examinestatus;
     private Timestamp examinetime;
     private String firstauthor;
     private String	firstauthorcode;
     private String fiveauthor;
     private String fourauthor;
     private Timestamp ignoretime;
     private String isbn;
     private Integer numauthor;
     private String otherauthor;
     private Timestamp savetime;
     private String secauthor;
     private String threeauthor;
     private String title;
     private String year;
     private String secauthorcode;
     private String	threeauthorcode;
     private String	fourauthorcode;
     private String	fiveauthorcode;
     private String	otherauthorcode;
     private String publishtype;
     private String firsteditor;
     private String firsteditorcode;
     private String seceditor;
     private String seceditorcode;
     private String thirdeditor;
     private String thirdeditorcode;
     private String othereditor;
     private String othereditorcode;
     private String firstsubeditor;
     private String firstsubeditorcode;
     private String secsubeditor;
     private String secsubeditorcode;
     private String thirdsubeditor;
     private String thirdsubeditorcode;
     private String othersubeditor;
     private String othersubeditorcode;

     private String	uploadurl;
     private String  auditopinion;

     private String append;

     private String submitUser;
     private Timestamp submitTime;
    // Constructors

    /** default constructor */
    public TPublish() {
    }


    /** full constructor */


    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")

    @Column(name="publishid", unique=true, nullable=false)

    public Integer getPublishid() {
        return this.publishid;
    }

    public TPublish(Integer publishid, String examinestatus, Timestamp examinetime,
					String firstauthor, String firstauthorcode, String fiveauthor,
					String fourauthor, Timestamp ignoretime, String isbn,
					Integer numauthor, String otherauthor, Timestamp savetime,
					String secauthor, Timestamp submittime, String threeauthor,
					String title, String year, String secauthorcode,
					String threeauthorcode, String fourauthorcode,
					String fiveauthorcode, String otherauthorcode,
					String publishtype, String firsteditor, String firsteditorcode,
					String seceditor, String seceditorcode, String thirdeditor,
					String thirdeditorcode, String othereditor, String othereditorcode,
					String firstsubeditor, String firstsubeditorcode,
					String secsubeditor, String secsubeditorcode,
					String thirdsubeditor, String thirdsubeditorcode,
					String othersubeditor, String othersubeditorcode, String uploadurl,
					String auditopinion, String append) {
		super();
		this.publishid = publishid;
		this.examinestatus = examinestatus;
		this.examinetime = examinetime;
		this.firstauthor = firstauthor;
		this.firstauthorcode = firstauthorcode;
		this.fiveauthor = fiveauthor;
		this.fourauthor = fourauthor;
		this.ignoretime = ignoretime;
		this.isbn = isbn;
		this.numauthor = numauthor;
		this.otherauthor = otherauthor;
		this.savetime = savetime;
		this.secauthor = secauthor;
		this.submitTime = submittime;
		this.threeauthor = threeauthor;
		this.title = title;
		this.year = year;
		this.secauthorcode = secauthorcode;
		this.threeauthorcode = threeauthorcode;
		this.fourauthorcode = fourauthorcode;
		this.fiveauthorcode = fiveauthorcode;
		this.otherauthorcode = otherauthorcode;
		this.publishtype = publishtype;
		this.firsteditor = firsteditor;
		this.firsteditorcode = firsteditorcode;
		this.seceditor = seceditor;
		this.seceditorcode = seceditorcode;
		this.thirdeditor = thirdeditor;
		this.thirdeditorcode = thirdeditorcode;
		this.othereditor = othereditor;
		this.othereditorcode = othereditorcode;
		this.firstsubeditor = firstsubeditor;
		this.firstsubeditorcode = firstsubeditorcode;
		this.secsubeditor = secsubeditor;
		this.secsubeditorcode = secsubeditorcode;
		this.thirdsubeditor = thirdsubeditor;
		this.thirdsubeditorcode = thirdsubeditorcode;
		this.othersubeditor = othersubeditor;
		this.othersubeditorcode = othersubeditorcode;
		this.uploadurl = uploadurl;
		this.auditopinion = auditopinion;
		this.append = append;
	}


	public void setPublishid(Integer publishid) {
        this.publishid = publishid;
    }


	@Column(name="firstauthorcode", length=100)
    public String getFirstauthorcode() {
		return firstauthorcode;
	}


	public void setFirstauthorcode(String firstauthorcode) {
		this.firstauthorcode = firstauthorcode;
	}

	@Column(name="secauthorcode", length=100)
	public String getSecauthorcode() {
		return secauthorcode;
	}


	public void setSecauthorcode(String secauthorcode) {
		this.secauthorcode = secauthorcode;
	}

	@Column(name="threeauthorcode", length=100)
	public String getThreeauthorcode() {
		return threeauthorcode;
	}


	public void setThreeauthorcode(String threeauthorcode) {
		this.threeauthorcode = threeauthorcode;
	}

	@Column(name="fourauthorcode", length=100)
	public String getFourauthorcode() {
		return fourauthorcode;
	}


	public void setFourauthorcode(String fourauthorcode) {
		this.fourauthorcode = fourauthorcode;
	}

	@Column(name="fiveauthorcode", length=100)
	public String getFiveauthorcode() {
		return fiveauthorcode;
	}


	public void setFiveauthorcode(String fiveauthorcode) {
		this.fiveauthorcode = fiveauthorcode;
	}

	@Column(name="otherauthorcode", length=100)
	public String getOtherauthorcode() {
		return otherauthorcode;
	}


	public void setOtherauthorcode(String otherauthorcode) {
		this.otherauthorcode = otherauthorcode;
	}




	@Column(name="publishtype", length=150)
	public String getPublishtype() {
		return publishtype;
	}


	public void setPublishtype(String publishtype) {
		this.publishtype = publishtype;
	}

	@Column(name="firsteditor", length=150)
	public String getFirsteditor() {
		return firsteditor;
	}


	public void setFirsteditor(String firsteditor) {
		this.firsteditor = firsteditor;
	}

	@Column(name="firsteditorcode", length=100)
	public String getFirsteditorcode() {
		return firsteditorcode;
	}


	public void setFirsteditorcode(String firsteditorcode) {
		this.firsteditorcode = firsteditorcode;
	}

	@Column(name="seceditor", length=150)
	public String getSeceditor() {
		return seceditor;
	}


	public void setSeceditor(String seceditor) {
		this.seceditor = seceditor;
	}

	@Column(name="seceditorcode", length=100)
	public String getSeceditorcode() {
		return seceditorcode;
	}


	public void setSeceditorcode(String seceditorcode) {
		this.seceditorcode = seceditorcode;
	}

	@Column(name="thirdeditor", length=150)
	public String getThirdeditor() {
		return thirdeditor;
	}


	public void setThirdeditor(String thirdeditor) {
		this.thirdeditor = thirdeditor;
	}

	@Column(name="thirdeditorcode", length=100)
	public String getThirdeditorcode() {
		return thirdeditorcode;
	}


	public void setThirdeditorcode(String thirdeditorcode) {
		this.thirdeditorcode = thirdeditorcode;
	}

	@Column(name="othereditor", length=350)
	public String getOthereditor() {
		return othereditor;
	}


	public void setOthereditor(String othereditor) {
		this.othereditor = othereditor;
	}

	@Column(name="othereditorcode", length=300)
	public String getOthereditorcode() {
		return othereditorcode;
	}


	public void setOthereditorcode(String othereditorcode) {
		this.othereditorcode = othereditorcode;
	}

	@Column(name="firstsubeditor", length=150)
	public String getFirstsubeditor() {
		return firstsubeditor;
	}


	public void setFirstsubeditor(String firstsubeditor) {
		this.firstsubeditor = firstsubeditor;
	}

	@Column(name="firstsubeditorcode", length=100)
	public String getFirstsubeditorcode() {
		return firstsubeditorcode;
	}


	public void setFirstsubeditorcode(String firstsubeditorcode) {
		this.firstsubeditorcode = firstsubeditorcode;
	}

	@Column(name="secsubeditor", length=150)
	public String getSecsubeditor() {
		return secsubeditor;
	}


	public void setSecsubeditor(String secsubeditor) {
		this.secsubeditor = secsubeditor;
	}

	@Column(name="secsubeditorcode", length=100)
	public String getSecsubeditorcode() {
		return secsubeditorcode;
	}


	public void setSecsubeditorcode(String secsubeditorcode) {
		this.secsubeditorcode = secsubeditorcode;
	}

	@Column(name="thirdsubeditor", length=150)
	public String getThirdsubeditor() {
		return thirdsubeditor;
	}


	public void setThirdsubeditor(String thirdsubeditor) {
		this.thirdsubeditor = thirdsubeditor;
	}

	@Column(name="thirdsubeditorcode", length=100)
	public String getThirdsubeditorcode() {
		return thirdsubeditorcode;
	}


	public void setThirdsubeditorcode(String thirdsubeditorcode) {
		this.thirdsubeditorcode = thirdsubeditorcode;
	}

	@Column(name="othersubeditor", length=350)
	public String getOthersubeditor() {
		return othersubeditor;
	}


	public void setOthersubeditor(String othersubeditor) {
		this.othersubeditor = othersubeditor;
	}

	@Column(name="othersubeditorcode", length=300)
	public String getOthersubeditorcode() {
		return othersubeditorcode;
	}


	public void setOthersubeditorcode(String othersubeditorcode) {
		this.othersubeditorcode = othersubeditorcode;
	}

	@Column(name="uploadurl", length=350)
	public String getUploadurl() {
		return uploadurl;
	}


	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}

	@Column(name="auditopinion", length=350)
	public String getAuditopinion() {
		return auditopinion;
	}


	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}


	@Column(name="append", length=500)

    public String getAppend() {
        return this.append;
    }

    public void setAppend(String append) {
        this.append = append;
    }

    @Column(name="examinestatus",length=100)

    public String getExaminestatus() {
        return this.examinestatus;
    }

    public void setExaminestatus(String examinestatus) {
        this.examinestatus = examinestatus;
    }

    @Column(name="examinetime", length=19)

    public Timestamp getExaminetime() {
        return this.examinetime;
    }

    public void setExaminetime(Timestamp examinetime) {
        this.examinetime = examinetime;
    }

    @Column(name="firstauthor", length=150)

    public String getFirstauthor() {
        return this.firstauthor;
    }

    public void setFirstauthor(String firstauthor) {
        this.firstauthor = firstauthor;
    }

    @Column(name="fiveauthor", length=150)

    public String getFiveauthor() {
        return this.fiveauthor;
    }

    public void setFiveauthor(String fiveauthor) {
        this.fiveauthor = fiveauthor;
    }

    @Column(name="fourauthor", length=150)

    public String getFourauthor() {
        return this.fourauthor;
    }

    public void setFourauthor(String fourauthor) {
        this.fourauthor = fourauthor;
    }

    @Column(name="ignoretime", length=19)

    public Timestamp getIgnoretime() {
        return this.ignoretime;
    }

    public void setIgnoretime(Timestamp ignoretime) {
        this.ignoretime = ignoretime;
    }

    @Column(name="isbn", length=100)

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name="numauthor")

    public Integer getNumauthor() {
        return this.numauthor;
    }

    public void setNumauthor(Integer numauthor) {
        this.numauthor = numauthor;
    }

    @Column(name="otherauthor", length=350)

    public String getOtherauthor() {
        return this.otherauthor;
    }

    public void setOtherauthor(String otherauthor) {
        this.otherauthor = otherauthor;
    }

    @Column(name="savetime", length=19)

    public Timestamp getSavetime() {
        return this.savetime;
    }

    public void setSavetime(Timestamp savetime) {
        this.savetime = savetime;
    }

    @Column(name="secauthor", length=150)

    public String getSecauthor() {
        return this.secauthor;
    }

    public void setSecauthor(String secauthor) {
        this.secauthor = secauthor;
    }

    @Column(name="threeauthor", length=150)

    public String getThreeauthor() {
        return this.threeauthor;
    }

    public void setThreeauthor(String threeauthor) {
        this.threeauthor = threeauthor;
    }

    @Column(name="title", length=255)

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="year", length=50)

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "submituser")
	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	@Column(name = "submittime")
	public Timestamp getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	private Collection<TPublishScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TPublishScore> getScore(){
		return score;
	}
	public void setScore(Collection<TPublishScore> score){
		this.score = score;
	}
}
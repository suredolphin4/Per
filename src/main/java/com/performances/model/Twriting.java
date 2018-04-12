package com.performances.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_writing", catalog="per")
public class Twriting{
	private Integer id;
	private String year;
	private String title;
	private String isbn;
	private String append;
	private String audit_status;
	private String auditOpinion;
	private String pubKind;
	private String writingKind;
	private String press;
	private String interCooperate;
	private java.util.Date savetime;
	private Double wordCount;
	private String first_editor;
	private String first_editor_code;
	private Double first_editor_wc;
	private String second_editor;
	private String second_editor_code;
	private Double second_editor_wc;
	private String third_editor;
	private String third_editor_code;
	private Double third_editor_wc;
	private String other_editor;
	private String other_editor_code;
	private String other_editor_wc;
	private String first_subeditor;
	private String first_subeditor_code;
	private String second_subeditor;
	private String second_subeditor_code;
	private String third_subeditor;
	private String third_subeditor_code;
	private String other_subeditor;
	private String other_subeditor_code;
	private String first_author;
	private String first_author_code;
	private Double first_author_wc;
	private String second_author;
	private String second_author_code;
	private Double second_author_wc;
	private String third_author;
	private String third_author_code;
	private Double third_author_wc;
	private String fourth_author;
	private String fourth_author_code;
	private Double fourth_author_wc;
	private String fifth_author;
	private String fifth_author_code;
	private Double fifth_author_wc;
	private String other_author;
	private String other_author_code;
	private String other_author_wc;
	
	private String submitUser;
	private java.sql.Timestamp submitTime;
	
	@GenericGenerator(name="generator", strategy="increment")
	@Id
	@GeneratedValue(generator="generator")
	@Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="year", length=4)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name="title", length=255)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="isbn", length=50)
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Column(name="append", length=255)
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	
	@Column(name="audit_status", length=20)
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	
	@Column(name="audit_opinion", length=255)
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	@Column(name="pub_kind", length=30)
	public String getPubKind() {
		return pubKind;
	}
	public void setPubKind(String pubKind) {
		this.pubKind = pubKind;
	}
	
	@Column(name="writing_kind", length=10)
	public String getWritingKind() {
		return writingKind;
	}
	public void setWritingKind(String writingKind) {
		this.writingKind = writingKind;
	}
	
	@Column(name="inter_cooperate", length=10)
	public String getInterCooperate() {
		return interCooperate;
	}
	public void setInterCooperate(String interCooperate) {
		this.interCooperate = interCooperate;
	}
	
	@Column(name="savetime")
	public java.util.Date getSavetime() {
		return savetime;
	}
	public void setSavetime(java.util.Date savetime) {
		this.savetime = savetime;
	}
	
	@Column(name="word_count", length=20)
	public Double getWordCount() {
		return wordCount;
	}
	public void setWordCount(Double wordCount) {
		this.wordCount = wordCount;
	}
	
	@Column(name="first_editor", length=50)
	public String getFirst_editor() {
		return first_editor;
	}
	public void setFirst_editor(String first_editor) {
		this.first_editor = first_editor;
	}
	
	@Column(name="first_editor_code", length=20)
	public String getFirst_editor_code() {
		return first_editor_code;
	}
	public void setFirst_editor_code(String first_editor_code) {
		this.first_editor_code = first_editor_code;
	}
	
	@Column(name="first_editor_wc", length=12)
	public Double getFirst_editor_wc() {
		return first_editor_wc;
	}
	public void setFirst_editor_wc(Double first_editor_wc) {
		this.first_editor_wc = first_editor_wc;
	}
	
	@Column(name="second_editor", length=50)
	public String getSecond_editor() {
		return second_editor;
	}
	public void setSecond_editor(String second_editor) {
		this.second_editor = second_editor;
	}
	
	@Column(name="second_editor_code", length=20)
	public String getSecond_editor_code() {
		return second_editor_code;
	}
	public void setSecond_editor_code(String second_editor_code) {
		this.second_editor_code = second_editor_code;
	}
	
	@Column(name="second_editor_wc", length=12)
	public Double getSecond_editor_wc() {
		return second_editor_wc;
	}
	public void setSecond_editor_wc(Double second_editor_wc) {
		this.second_editor_wc = second_editor_wc;
	}
	
	@Column(name="third_editor", length=50)
	public String getThird_editor() {
		return third_editor;
	}
	public void setThird_editor(String third_editor) {
		this.third_editor = third_editor;
	}
	
	@Column(name="third_editor_code", length=20)
	public String getThird_editor_code() {
		return third_editor_code;
	}
	public void setThird_editor_code(String third_editor_code) {
		this.third_editor_code = third_editor_code;
	}
	
	@Column(name="third_editor_wc", length=12)
	public Double getThird_editor_wc() {
		return third_editor_wc;
	}
	public void setThird_editor_wc(Double third_editor_wc) {
		this.third_editor_wc = third_editor_wc;
	}
	
	@Column(name="other_editor", length=50)
	public String getOther_editor() {
		return other_editor;
	}
	public void setOther_editor(String other_editor) {
		this.other_editor = other_editor;
	}
	
	@Column(name="other_editor_code", length=255)
	public String getOther_editor_code() {
		return other_editor_code;
	}
	public void setOther_editor_code(String other_editor_code) {
		this.other_editor_code = other_editor_code;
	}
	
	@Column(name="other_editor_wc", length=255)
	public String getOther_editor_wc() {
		return other_editor_wc;
	}
	public void setOther_editor_wc(String other_editor_wc) {
		this.other_editor_wc = other_editor_wc;
	}
	
	@Column(name="first_author", length=50)
	public String getFirst_author() {
		return first_author;
	}
	public void setFirst_author(String first_author) {
		this.first_author = first_author;
	}
	
	@Column(name="first_author_code", length=20)
	public String getFirst_author_code() {
		return first_author_code;
	}
	public void setFirst_author_code(String first_author_code) {
		this.first_author_code = first_author_code;
	}
	
	@Column(name="first_author_wc", length=12)
	public Double getFirst_author_wc() {
		return first_author_wc;
	}
	public void setFirst_author_wc(Double first_author_wc) {
		this.first_author_wc = first_author_wc;
	}
	
	@Column(name="second_author", length=50)
	public String getSecond_author() {
		return second_author;
	}
	public void setSecond_author(String second_author) {
		this.second_author = second_author;
	}
	
	@Column(name="second_author_code", length=20)
	public String getSecond_author_code() {
		return second_author_code;
	}
	public void setSecond_author_code(String second_author_code) {
		this.second_author_code = second_author_code;
	}
	
	@Column(name="second_author_wc", length=12)
	public Double getSecond_author_wc() {
		return second_author_wc;
	}
	public void setSecond_author_wc(Double second_author_wc) {
		this.second_author_wc = second_author_wc;
	}
	
	@Column(name="third_author", length=50)
	public String getThird_author() {
		return third_author;
	}
	public void setThird_author(String third_author) {
		this.third_author = third_author;
	}
	
	@Column(name="third_author_code", length=20)
	public String getThird_author_code() {
		return third_author_code;
	}
	public void setThird_author_code(String third_author_code) {
		this.third_author_code = third_author_code;
	}
	
	@Column(name="third_author_wc", length=12)
	public Double getThird_author_wc() {
		return third_author_wc;
	}
	public void setThird_author_wc(Double third_author_wc) {
		this.third_author_wc = third_author_wc;
	}
	
	@Column(name="fourth_author", length=50)
	public String getFourth_author() {
		return fourth_author;
	}
	public void setFourth_author(String fourth_author) {
		this.fourth_author = fourth_author;
	}
	
	@Column(name="fourth_author_code", length=20)
	public String getFourth_author_code() {
		return fourth_author_code;
	}
	public void setFourth_author_code(String fourth_author_code) {
		this.fourth_author_code = fourth_author_code;
	}
	
	@Column(name="fourth_author_wc", length=12)
	public Double getFourth_author_wc() {
		return fourth_author_wc;
	}
	public void setFourth_author_wc(Double fourth_author_wc) {
		this.fourth_author_wc = fourth_author_wc;
	}
	
	@Column(name="fifth_author", length=50)
	public String getFifth_author() {
		return fifth_author;
	}
	public void setFifth_author(String fifth_author) {
		this.fifth_author = fifth_author;
	}
	
	@Column(name="fifth_author_code", length=20)
	public String getFifth_author_code() {
		return fifth_author_code;
	}
	public void setFifth_author_code(String fifth_author_code) {
		this.fifth_author_code = fifth_author_code;
	}
	
	@Column(name="fifth_author_wc", length=12)
	public Double getFifth_author_wc() {
		return fifth_author_wc;
	}
	public void setFifth_author_wc(Double fifth_author_wc) {
		this.fifth_author_wc = fifth_author_wc;
	}
	
	@Column(name="other_author", length=50)
	public String getOther_author() {
		return other_author;
	}
	public void setOther_author(String other_author) {
		this.other_author = other_author;
	}
	
	@Column(name="other_author_code", length=255)
	public String getOther_author_code() {
		return other_author_code;
	}
	public void setOther_author_code(String other_author_code) {
		this.other_author_code = other_author_code;
	}
	
	@Column(name="other_author_wc", length=255)
	public String getOther_author_wc() {
		return other_author_wc;
	}
	public void setOther_author_wc(String other_author_wc) {
		this.other_author_wc = other_author_wc;
	}
	
	@Column(name="press", length=255)
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	
	@Column(name="submituser", length=50)
	public String getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}
	
	@Column(name="submittime")
	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	@Column(name="first_subeditor", length=50)
	public String getFirst_subeditor() {
		return first_subeditor;
	}

	public void setFirst_subeditor(String first_subeditor) {
		this.first_subeditor = first_subeditor;
	}

	@Column(name="first_subeditor_code", length=20)
	public String getFirst_subeditor_code() {
		return first_subeditor_code;
	}

	public void setFirst_subeditor_code(String first_subeditor_code) {
		this.first_subeditor_code = first_subeditor_code;
	}

	@Column(name="second_subeditor", length=50)
	public String getSecond_subeditor() {
		return second_subeditor;
	}

	public void setSecond_subeditor(String second_subeditor) {
		this.second_subeditor = second_subeditor;
	}

	@Column(name="second_subeditor_code", length=20)
	public String getSecond_subeditor_code() {
		return second_subeditor_code;
	}

	public void setSecond_subeditor_code(String second_subeditor_code) {
		this.second_subeditor_code = second_subeditor_code;
	}

	@Column(name="third_subeditor", length=50)
	public String getThird_subeditor() {
		return third_subeditor;
	}

	public void setThird_subeditor(String third_subeditor) {
		this.third_subeditor = third_subeditor;
	}

	@Column(name="third_subeditor_code", length=20)
	public String getThird_subeditor_code() {
		return third_subeditor_code;
	}

	public void setThird_subeditor_code(String third_subeditor_code) {
		this.third_subeditor_code = third_subeditor_code;
	}

	@Column(name="other_subeditor", length=50)
	public String getOther_subeditor() {
		return other_subeditor;
	}

	public void setOther_subeditor(String other_subeditor) {
		this.other_subeditor = other_subeditor;
	}

	@Column(name="other_subeditor_code", length=20)
	public String getOther_subeditor_code() {
		return other_subeditor_code;
	}

	public void setOther_subeditor_code(String other_subeditor_code) {
		this.other_subeditor_code = other_subeditor_code;
	}

	private Collection<TWritingScore> score;
	@OneToMany(cascade={CascadeType.ALL}) 
	@JoinColumn(name="fid", insertable = false,updatable = false)
	public Collection<TWritingScore> getScore(){
		return score;
	}
	public void setScore(Collection<TWritingScore> score){
		this.score = score;
	}
}

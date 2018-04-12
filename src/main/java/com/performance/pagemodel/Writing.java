package com.performance.pagemodel;

/*
 * 著作
 * @author zxbing
 */
public class Writing extends BaseModel {
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

	private String first_subeditor;
	private String first_subeditor_code;
	private String second_subeditor;
	private String second_subeditor_code;
	private String third_subeditor;
	private String third_subeditor_code;
	private String other_subeditor;
	private String other_subeditor_code;

	private String other_editor_wc;
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
	
	// 过滤条件
	private String auditfilter; // 根据审核状态过滤，用于接收过滤下拉框选中参数 @zxbing
	private String s_begin_year;
	private String s_end_year;
	private String s_lw_name;
	private String s_lw_author;
	private String s_lw_authorcode;
	private String s_which_lab;
	private String s_which_domain;
	
	private String submitUser;
	private java.sql.Timestamp submitTime;

	// 获取分数
	private String 	firsteditorscore;
    private String	seceditorscore;
    private String  threeeditorscore;
    private String	othereditorscore;
	private String 	firstsubeditorscore;
	private String	secsubeditorscore;
	private String  threesubeditorscore;
	private String	othersubeditorscore;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getPubKind() {
		return pubKind;
	}

	public void setPubKind(String pubKind) {
		this.pubKind = pubKind;
	}

	public String getWritingKind() {
		return writingKind;
	}

	public void setWritingKind(String writingKind) {
		this.writingKind = writingKind;
	}

	public String getInterCooperate() {
		return interCooperate;
	}

	public void setInterCooperate(String interCooperate) {
		this.interCooperate = interCooperate;
	}

	public java.util.Date getSavetime() {
		return savetime;
	}

	public void setSavetime(java.util.Date savetime) {
		this.savetime = savetime;
	}

	public Double getWordCount() {
		return wordCount;
	}

	public void setWordCount(Double wordCount) {
		this.wordCount = wordCount;
	}

	public String getFirst_editor() {
		return first_editor;
	}

	public void setFirst_editor(String first_editor) {
		this.first_editor = first_editor;
	}

	public String getFirst_editor_code() {
		return first_editor_code;
	}

	public void setFirst_editor_code(String first_editor_code) {
		this.first_editor_code = first_editor_code;
	}

	public Double getFirst_editor_wc() {
		return first_editor_wc;
	}

	public void setFirst_editor_wc(Double first_editor_wc) {
		this.first_editor_wc = first_editor_wc;
	}

	public String getSecond_editor() {
		return second_editor;
	}

	public void setSecond_editor(String second_editor) {
		this.second_editor = second_editor;
	}

	public String getSecond_editor_code() {
		return second_editor_code;
	}

	public void setSecond_editor_code(String second_editor_code) {
		this.second_editor_code = second_editor_code;
	}

	public Double getSecond_editor_wc() {
		return second_editor_wc;
	}

	public void setSecond_editor_wc(Double second_editor_wc) {
		this.second_editor_wc = second_editor_wc;
	}

	public String getThird_editor() {
		return third_editor;
	}

	public void setThird_editor(String third_editor) {
		this.third_editor = third_editor;
	}

	public String getThird_editor_code() {
		return third_editor_code;
	}

	public void setThird_editor_code(String third_editor_code) {
		this.third_editor_code = third_editor_code;
	}

	public Double getThird_editor_wc() {
		return third_editor_wc;
	}

	public void setThird_editor_wc(Double third_editor_wc) {
		this.third_editor_wc = third_editor_wc;
	}

	public String getOther_editor() {
		return other_editor;
	}

	public void setOther_editor(String other_editor) {
		this.other_editor = other_editor;
	}

	public String getOther_editor_code() {
		return other_editor_code;
	}

	public void setOther_editor_code(String other_editor_code) {
		this.other_editor_code = other_editor_code;
	}

	public String getOther_editor_wc() {
		return other_editor_wc;
	}

	public void setOther_editor_wc(String other_editor_wc) {
		this.other_editor_wc = other_editor_wc;
	}

	public String getFirst_author() {
		return first_author;
	}

	public void setFirst_author(String first_author) {
		this.first_author = first_author;
	}

	public String getFirst_author_code() {
		return first_author_code;
	}

	public void setFirst_author_code(String first_author_code) {
		this.first_author_code = first_author_code;
	}

	public Double getFirst_author_wc() {
		return first_author_wc;
	}

	public void setFirst_author_wc(Double first_author_wc) {
		this.first_author_wc = first_author_wc;
	}

	public String getSecond_author() {
		return second_author;
	}

	public void setSecond_author(String second_author) {
		this.second_author = second_author;
	}

	public String getSecond_author_code() {
		return second_author_code;
	}

	public void setSecond_author_code(String second_author_code) {
		this.second_author_code = second_author_code;
	}

	public Double getSecond_author_wc() {
		return second_author_wc;
	}

	public void setSecond_author_wc(Double second_author_wc) {
		this.second_author_wc = second_author_wc;
	}

	public String getThird_author() {
		return third_author;
	}

	public void setThird_author(String third_author) {
		this.third_author = third_author;
	}

	public String getThird_author_code() {
		return third_author_code;
	}

	public void setThird_author_code(String third_author_code) {
		this.third_author_code = third_author_code;
	}

	public Double getThird_author_wc() {
		return third_author_wc;
	}

	public void setThird_author_wc(Double third_author_wc) {
		this.third_author_wc = third_author_wc;
	}

	public String getFourth_author() {
		return fourth_author;
	}

	public void setFourth_author(String fourth_author) {
		this.fourth_author = fourth_author;
	}

	public String getFourth_author_code() {
		return fourth_author_code;
	}

	public void setFourth_author_code(String fourth_author_code) {
		this.fourth_author_code = fourth_author_code;
	}

	public Double getFourth_author_wc() {
		return fourth_author_wc;
	}

	public void setFourth_author_wc(Double fourth_author_wc) {
		this.fourth_author_wc = fourth_author_wc;
	}

	public String getFifth_author() {
		return fifth_author;
	}

	public void setFifth_author(String fifth_author) {
		this.fifth_author = fifth_author;
	}

	public String getFifth_author_code() {
		return fifth_author_code;
	}

	public void setFifth_author_code(String fifth_author_code) {
		this.fifth_author_code = fifth_author_code;
	}

	public Double getFifth_author_wc() {
		return fifth_author_wc;
	}

	public void setFifth_author_wc(Double fifth_author_wc) {
		this.fifth_author_wc = fifth_author_wc;
	}

	public String getOther_author() {
		return other_author;
	}

	public void setOther_author(String other_author) {
		this.other_author = other_author;
	}

	public String getOther_author_code() {
		return other_author_code;
	}

	public void setOther_author_code(String other_author_code) {
		this.other_author_code = other_author_code;
	}

	public String getOther_author_wc() {
		return other_author_wc;
	}

	public void setOther_author_wc(String other_author_wc) {
		this.other_author_wc = other_author_wc;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getAuditfilter() {
		return auditfilter;
	}

	public void setAuditfilter(String auditfilter) {
		this.auditfilter = auditfilter;
	}
	
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

	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	public java.sql.Timestamp getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(java.sql.Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public String getFirst_subeditor() {
		return first_subeditor;
	}

	public void setFirst_subeditor(String first_subeditor) {
		this.first_subeditor = first_subeditor;
	}

	public String getFirst_subeditor_code() {
		return first_subeditor_code;
	}

	public void setFirst_subeditor_code(String first_subeditor_code) {
		this.first_subeditor_code = first_subeditor_code;
	}

	public String getSecond_subeditor() {
		return second_subeditor;
	}

	public void setSecond_subeditor(String second_subeditor) {
		this.second_subeditor = second_subeditor;
	}

	public String getSecond_subeditor_code() {
		return second_subeditor_code;
	}

	public void setSecond_subeditor_code(String second_subeditor_code) {
		this.second_subeditor_code = second_subeditor_code;
	}

	public String getThird_subeditor() {
		return third_subeditor;
	}

	public void setThird_subeditor(String third_subeditor) {
		this.third_subeditor = third_subeditor;
	}

	public String getThird_subeditor_code() {
		return third_subeditor_code;
	}

	public void setThird_subeditor_code(String third_subeditor_code) {
		this.third_subeditor_code = third_subeditor_code;
	}

	public String getOther_subeditor() {
		return other_subeditor;
	}

	public void setOther_subeditor(String other_subeditor) {
		this.other_subeditor = other_subeditor;
	}

	public String getOther_subeditor_code() {
		return other_subeditor_code;
	}

	public void setOther_subeditor_code(String other_subeditor_code) {
		this.other_subeditor_code = other_subeditor_code;
	}

	public String getFirstsubeditorscore() {
		return firstsubeditorscore;
	}

	public void setFirstsubeditorscore(String firstsubeditorscore) {
		this.firstsubeditorscore = firstsubeditorscore;
	}

	public String getSecsubeditorscore() {
		return secsubeditorscore;
	}

	public void setSecsubeditorscore(String secsubeditorscore) {
		this.secsubeditorscore = secsubeditorscore;
	}

	public String getThreesubeditorscore() {
		return threesubeditorscore;
	}

	public void setThreesubeditorscore(String threesubeditorscore) {
		this.threesubeditorscore = threesubeditorscore;
	}

	public String getOthersubeditorscore() {
		return othersubeditorscore;
	}

	public void setOthersubeditorscore(String othersubeditorscore) {
		this.othersubeditorscore = othersubeditorscore;
	}

	//分数
	public String getFirsteditorscore() {
		return firsteditorscore;
	}
	public void setFirsteditorscore(String firsteditorscore) {
		this.firsteditorscore = firsteditorscore;
	}
	public String getSeceditorscore() {
		return seceditorscore;
	}
	public void setSeceditorscore(String seceditorscore) {
		this.seceditorscore = seceditorscore;
	}
	public String getThreeeditorscore() {
		return threeeditorscore;
	}
	public void setThreeeditorscore(String threeeditorscore) {
		this.threeeditorscore = threeeditorscore;
	}
	public String getOthereditorscore() {
		return othereditorscore;
	}
	public void setOthereditorscore(String othereditorscore) {
		this.othereditorscore = othereditorscore;
	}
	
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

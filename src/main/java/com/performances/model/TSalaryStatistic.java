package com.performances.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by zyh on 2016/4/3.
 */
@Entity
@Table(name = "salary_statistic", catalog = "per")
public class TSalaryStatistic {
    private String writing_type;
    private Integer writing_first_count;
    private Integer writing_second_count;
    private Integer writing_third_count;
    private BigDecimal writing_salary;
    private String national_map_type;
    private Integer national_map_first_count;
    private Integer national_map_second_count;
    private Integer national_map_third_count;
    private BigDecimal national_map_salary;
    private String region_map_type;
    private Integer region_map_first_count;
    private Integer region_map_second_count;
    private Integer region_map_third_count;
    private BigDecimal region_map_salary;
    private String n_plan_type;
    private Integer n_plan_first_count;
    private Integer n_plan_second_count;
    private Integer n_plan_third_count;
    private BigDecimal n_plan_salary;
    private String sci_thesis_type;
    private Integer sci_thesis_first_count;
    private Integer sci_thesis_second_count;
    private Integer sci_thesis_third_count;
    private BigDecimal sci_thesis_salary;
    private String patent_type;
    private Integer patent_first_count;
    private Integer patent_second_count;
    private Integer patent_third_count;
    private BigDecimal patent_salary;
    private String report_type;
    private Integer report_first_count;
    private Integer report_second_count;
    private Integer report_third_count;
    private BigDecimal report_salary;
    private BigDecimal writing_map_sumup;
    private String p_plan_type;
    private Integer p_plan_first_count;
    private Integer p_plan_second_count;
    private Integer p_plan_third_count;
    private BigDecimal p_plan_salary;
    private String n_standard_type;
    private Integer n_standard_first_count;
    private Integer n_standard_second_count;
    private Integer n_standard_third_count;
    private BigDecimal n_standard_salary;
    private BigDecimal plan_standard_sumup;
    private String nsci_thesis_type;
    private Integer nsci_thesis_first_count;
    private Integer nsci_thesis_second_count;
    private Integer nsci_thesis_third_count;
    private BigDecimal nsci_thesis_salary;
    private BigDecimal thesis_patent_report_sumup;
    private BigDecimal all_sumup;
    private String username;
    private String usercode;
//    private String domain;
//    private String declare_level;
    public TSalaryStatistic(){
    }

    public TSalaryStatistic(String writing_type,Integer writing_first_count,Integer writing_second_count,Integer writing_third_count,
                            BigDecimal writing_salary,String national_map_type,Integer national_map_first_count,
                            Integer national_map_second_count,Integer national_map_third_count,BigDecimal national_map_salary,String region_map_type,
                            Integer region_map_first_count,Integer region_map_second_count,Integer region_map_third_count,BigDecimal region_map_salary,
                            String username, String usercode,String n_plan_type,Integer n_plan_first_count,Integer n_plan_second_count,Integer n_plan_third_count,
                            BigDecimal n_plan_salary,String sci_thesis_type,Integer sci_thesis_first_count,Integer sci_thesis_second_count,Integer sci_thesis_third_count,
                            BigDecimal sci_thesis_salary,String patent_type,Integer patent_first_count,Integer patent_second_count,Integer patent_third_count,
                            BigDecimal patent_salary,String report_type,Integer report_first_count,Integer report_second_count,Integer report_third_count,BigDecimal report_salary,
                            BigDecimal writing_map_sumup,String p_plan_type,Integer p_plan_first_count,Integer p_plan_second_count,Integer p_plan_third_count,BigDecimal p_plan_salary,
                            String n_standard_type,Integer n_standard_first_count,Integer n_standard_second_count,Integer n_standard_third_count,BigDecimal n_standard_salary,
                            BigDecimal plan_standard_sumup,String nsci_thesis_type,Integer nsci_thesis_first_count,Integer nsci_thesis_second_count,Integer nsci_thesis_third_count,
                            BigDecimal nsci_thesis_salary,BigDecimal thesis_patent_report_sumup,BigDecimal all_sumup){
        this.writing_type=writing_type;
        this.writing_first_count=writing_first_count;
        this.writing_second_count=writing_second_count;
        this.writing_third_count=writing_third_count;
        this.writing_salary=writing_salary;
        this.national_map_type=national_map_type;
        this.national_map_first_count=national_map_first_count;
        this.national_map_second_count=national_map_second_count;
        this.national_map_third_count=national_map_third_count;
        this.national_map_salary=national_map_salary;
        this.region_map_type=region_map_type;
        this.region_map_first_count=region_map_first_count;
        this.region_map_second_count=region_map_second_count;
        this.region_map_third_count=region_map_third_count;
        this.region_map_salary=region_map_salary;
        this.n_plan_type=n_plan_type;
        this.n_plan_first_count=n_plan_first_count;
        this.n_plan_second_count=n_plan_second_count;
        this.n_plan_third_count=n_plan_third_count;
        this.n_plan_salary=n_plan_salary;
        this.sci_thesis_type=sci_thesis_type;
        this.sci_thesis_first_count=sci_thesis_first_count;
        this.sci_thesis_second_count=sci_thesis_second_count;
        this.sci_thesis_third_count=sci_thesis_third_count;
        this.sci_thesis_salary=sci_thesis_salary;
        this.patent_type=patent_type;
        this.patent_first_count=patent_first_count;
        this.patent_second_count=patent_second_count;
        this.patent_third_count=patent_third_count;
        this.patent_salary=patent_salary;
        this.report_type=report_type;
        this.report_first_count=report_first_count;
        this.report_second_count=report_second_count;
        this.report_third_count=report_third_count;
        this.report_salary=report_salary;
        this.writing_map_sumup=writing_map_sumup;
        this.p_plan_type=p_plan_type;
        this.p_plan_first_count=p_plan_first_count;
        this.p_plan_second_count=p_plan_second_count;
        this.p_plan_third_count=p_plan_third_count;
        this.p_plan_salary=p_plan_salary;
        this.n_standard_type=n_standard_type;
        this.n_standard_first_count=n_standard_first_count;
        this.n_standard_second_count=n_standard_second_count;
        this.n_standard_third_count=n_standard_third_count;
        this.n_standard_salary=n_standard_salary;
        this.plan_standard_sumup=plan_standard_sumup;
        this.nsci_thesis_type=nsci_thesis_type;
        this.nsci_thesis_first_count=nsci_thesis_first_count;
        this.nsci_thesis_second_count=nsci_thesis_second_count;
        this.nsci_thesis_third_count=nsci_thesis_third_count;
        this.nsci_thesis_salary=nsci_thesis_salary;
        this.thesis_patent_report_sumup=thesis_patent_report_sumup;
        this.all_sumup=all_sumup;
        this.username=username;
        this.usercode=usercode;
//        this.declare_level=declare_level;

    }

    /**
     * @return
     */
    @Column(name = "writing_type")
    public String getWriting_type() {
        return writing_type;
    }

    public void setWriting_type(String writing_type) {
        this.writing_type = writing_type;
    }

    @Column(name = "writing_first_count")
    public Integer getWriting_first_count() {
        return writing_first_count;
    }

    public void setWriting_first_count(Integer writing_first_count) {
        this.writing_first_count = writing_first_count;
    }

    @Column(name = "writing_second_count")
    public Integer getWriting_second_count() {
        return writing_second_count;
    }

    public void setWriting_second_count(Integer writing_second_count) { this.writing_second_count = writing_second_count;}

    @Column(name = "writing_third_count")
    public Integer getWriting_third_count() {
        return writing_third_count;
    }

    public void setWriting_third_count(Integer writing_third_count) {
        this.writing_third_count = writing_third_count;
    }

    @Column(name = "writing_salary")
    public BigDecimal getWriting_salary() {
        return writing_salary;
    }

    public void setWriting_salary(BigDecimal writing_salary) {
        this.writing_salary = writing_salary;
    }

    @Column(name = "national_map_type")
    public String getNational_map_type() {
        return national_map_type;
    }

    public void setNational_map_type(String nation_map_type) {
        this.national_map_type = nation_map_type;
    }

    @Column(name = "national_map_first_count")
    public Integer getNational_map_first_count() {
        return national_map_first_count;
    }

    public void setNational_map_first_count(Integer national_map_first_count){
        this.national_map_first_count=national_map_first_count;
    }

    @Column(name = "national_map_second_count")
    public Integer getNational_map_second_count() {
        return national_map_second_count;
    }

    public void setNational_map_second_count(Integer national_map_second_count) {
        this.national_map_second_count = national_map_second_count;
    }

    @Column(name = "national_map_third_count")
    public Integer getNational_map_third_count() {
        return national_map_third_count;
    }

    public void setNational_map_third_count(Integer national_map_third_count) {
        this.national_map_third_count = national_map_third_count;
    }

    @Column(name = "national_map_salary")
    public BigDecimal getNational_map_salary() {
        return national_map_salary;
    }

    public void setNational_map_salary(BigDecimal national_map_salary) {
        this.national_map_salary = national_map_salary;
    }

    @Column(name = "region_map_type")
    public String getRegion_map_type() {
        return region_map_type;
    }

    public void setRegion_map_type(String region_map_type) {
        this.region_map_type = region_map_type;
    }

    @Column(name = "region_map_first_count")
    public Integer getRegion_map_first_count() {
        return region_map_first_count;
    }

    public void setRegion_map_first_count(Integer region_map_first_count) {
        this.region_map_first_count = region_map_first_count;
    }

    @Column(name = "region_map_second_count")
    public Integer getRegion_map_second_count() {
        return region_map_second_count;
    }

    public void setRegion_map_second_count(Integer region_map_second_count) {
        this.region_map_second_count = region_map_second_count;
    }

    @Column(name = "region_map_third_count")
    public Integer getRegion_map_third_count() {
        return region_map_third_count;
    }

    public void setRegion_map_third_count(Integer region_map_third_count) {
        this.region_map_third_count = region_map_third_count;
    }

    @Column(name = "region_map_salary")
    public BigDecimal getRegion_map_salary() {
        return region_map_salary;
    }

    public void setRegion_map_salary(BigDecimal region_map_salary) {
        this.region_map_salary = region_map_salary;
    }

    @Column(name="writing_map_sumup")
    public void setWriting_map_sumup(BigDecimal writing_map_sumup) {
        this.writing_map_sumup = writing_map_sumup;
    }

    public BigDecimal getWriting_map_sumup() {
        return writing_map_sumup;
    }

    @Column(name="thesis_patent_report_sumup")
    public void setThesis_patent_report_sumup(BigDecimal thesis_patent_report_sumup) {
        this.thesis_patent_report_sumup = thesis_patent_report_sumup;
    }

    public BigDecimal getThesis_patent_report_sumup() {
        return thesis_patent_report_sumup;
    }

    @Column(name = "sci_thesis_type")
    public void setSci_thesis_type(String sci_thesis_type) {
        this.sci_thesis_type = sci_thesis_type;
    }

    public String getSci_thesis_type() {
        return sci_thesis_type;
    }

    @Column(name="sci_thesis_first_count")
    public void setSci_thesis_first_count(Integer sci_thesis_first_count) {
        this.sci_thesis_first_count = sci_thesis_first_count;
    }

    public Integer getSci_thesis_first_count() {
        return sci_thesis_first_count;
    }

    @Column(name = "n_plan_first_count")
    public void setN_plan_first_count(Integer n_plan_first_count) {
        this.n_plan_first_count = n_plan_first_count;
    }

    public Integer getN_plan_first_count() {
        return n_plan_first_count;
    }

    @Column(name = "n_plan_salary")
    public void setN_plan_salary(BigDecimal n_plan_salary) {
        this.n_plan_salary = n_plan_salary;
    }

    public BigDecimal getN_plan_salary() {
        return n_plan_salary;
    }

    @Column(name="n_plan_second_count")
    public void setN_plan_second_count(Integer n_plan_second_count) {
        this.n_plan_second_count = n_plan_second_count;
    }

    public Integer getN_plan_second_count() {
        return n_plan_second_count;
    }

    @Column(name = "n_plan_third_count")
    public void setN_plan_third_count(Integer n_plan_third_count) {
        this.n_plan_third_count = n_plan_third_count;
    }

    public Integer getN_plan_third_count() {
        return n_plan_third_count;
    }

    @Column(name="n_plan_type")
    public void setN_plan_type(String n_plan_type) {
        this.n_plan_type = n_plan_type;
    }

    public String getN_plan_type() {
        return n_plan_type;
    }

    @Column(name="n_standard_first_count")
    public void setN_standard_first_count(Integer n_standard_first_count) {
        this.n_standard_first_count = n_standard_first_count;
    }

    public Integer getN_standard_first_count() {
        return n_standard_first_count;
    }

    @Column(name = "n_standard_salary")
    public void setN_standard_salary(BigDecimal n_standard_salary) {
        this.n_standard_salary = n_standard_salary;
    }

    public BigDecimal getN_standard_salary() {
        return n_standard_salary;
    }

    @Column(name = "n_standard_count")
    public void setN_standard_second_count(Integer n_standard_second_count) {
        this.n_standard_second_count = n_standard_second_count;
    }

    public Integer getN_standard_second_count() {
        return n_standard_second_count;
    }

    @Column(name = "n_standard_third_count")
    public void setN_standard_third_count(Integer n_standard_third_count) {
        this.n_standard_third_count = n_standard_third_count;
    }

    public Integer getN_standard_third_count() {
        return n_standard_third_count;
    }

    @Column(name = "n_standard_type")
    public void setN_standard_type(String n_standard_type) {
        this.n_standard_type = n_standard_type;
    }

    public String getN_standard_type() {
        return n_standard_type;
    }

    @Column(name = "nsci_thesis_first_count")
    public void setNsci_thesis_first_count(Integer nsci_thesis_first_count) {
        this.nsci_thesis_first_count = nsci_thesis_first_count;
    }

    public Integer getNsci_thesis_first_count() {
        return nsci_thesis_first_count;
    }

    @Column(name = "nsci_thesis_salary")
    public void setNsci_thesis_salary(BigDecimal nsci_thesis_salary) {
        this.nsci_thesis_salary = nsci_thesis_salary;
    }

    public BigDecimal getNsci_thesis_salary() {
        return nsci_thesis_salary;
    }

    @Column(name="nsci_thesis_second_count")
    public void setNsci_thesis_second_count(Integer nsci_thesis_second_count) {
        this.nsci_thesis_second_count = nsci_thesis_second_count;
    }

    public Integer getNsci_thesis_second_count() {
        return nsci_thesis_second_count;
    }

    @Column(name = "nsci_thesis_third_count")
    public void setNsci_thesis_third_count(Integer nsci_thesis_third_count) {
        this.nsci_thesis_third_count = nsci_thesis_third_count;
    }

    public Integer getNsci_thesis_third_count() {
        return nsci_thesis_third_count;
    }

    @Column(name = "nsci_thesis_type")
    public void setNsci_thesis_type(String nsci_thesis_type) {
        this.nsci_thesis_type = nsci_thesis_type;
    }

    public String getNsci_thesis_type() {
        return nsci_thesis_type;
    }

    @Column(name = "p_plan_first_count")
    public void setP_plan_first_count(Integer p_plan_first_count) {
        this.p_plan_first_count = p_plan_first_count;
    }

    public Integer getP_plan_first_count() {
        return p_plan_first_count;
    }

    @Column(name = "p_plan_salary")
    public void setP_plan_salary(BigDecimal p_plan_salary) {
        this.p_plan_salary = p_plan_salary;
    }

    public BigDecimal getP_plan_salary() {
        return p_plan_salary;
    }

    @Column(name = "p_plan_second_count")
    public void setP_plan_second_count(Integer p_plan_second_count) {
        this.p_plan_second_count = p_plan_second_count;
    }

    public Integer getP_plan_second_count() {
        return p_plan_second_count;
    }

    @Column(name="p_plan_third_count")
    public void setP_plan_third_count(Integer p_plan_third_count) {
        this.p_plan_third_count = p_plan_third_count;
    }

    public Integer getP_plan_third_count() {
        return p_plan_third_count;
    }

    @Column(name = "p_plan_type")
    public void setP_plan_type(String p_plan_type) {
        this.p_plan_type = p_plan_type;
    }

    public String getP_plan_type() {
        return p_plan_type;
    }

    @Column(name = "patent_first_count")
    public void setPatent_first_count(Integer patent_first_count) {
        this.patent_first_count = patent_first_count;
    }

    public Integer getPatent_first_count() {
        return patent_first_count;
    }

    @Column(name = "patent_salary")
    public void setPatent_salary(BigDecimal patent_salary) {
        this.patent_salary = patent_salary;
    }

    public BigDecimal getPatent_salary() {
        return patent_salary;
    }

    @Column(name = "patent_second_count")
    public void setPatent_second_count(Integer patent_second_count) {
        this.patent_second_count = patent_second_count;
    }

    public Integer getPatent_second_count() {
        return patent_second_count;
    }

    @Column(name="patent_third_count")
    public void setPatent_third_count(Integer patent_third_count) {
        this.patent_third_count = patent_third_count;
    }

    public Integer getPatent_third_count() {
        return patent_third_count;
    }

    @Column(name = "patent_type")
    public void setPatent_type(String patent_type) {
        this.patent_type = patent_type;
    }

    public String getPatent_type() {
        return patent_type;
    }

    @Column(name = "plan_standard_sumup")
    public void setPlan_standard_sumup(BigDecimal plan_standard_sumup) {
        this.plan_standard_sumup = plan_standard_sumup;
    }

    public BigDecimal getPlan_standard_sumup() {
        return plan_standard_sumup;
    }

    @Column(name = "report_first_count")
    public void setReport_first_count(Integer report_first_count) {
        this.report_first_count = report_first_count;
    }

    public Integer getReport_first_count() {
        return report_first_count;
    }

    @Column(name = "report_salary")
    public void setReport_salary(BigDecimal report_salary) {
        this.report_salary = report_salary;
    }

    public BigDecimal getReport_salary() {
        return report_salary;
    }

    @Column(name = "report_second_count")
    public void setReport_second_count(Integer report_second_count) {
        this.report_second_count = report_second_count;
    }

    public Integer getReport_second_count() {
        return report_second_count;
    }

    @Column(name = "report_third_count")
    public void setReport_third_count(Integer report_third_count) {
        this.report_third_count = report_third_count;
    }

    public Integer getReport_third_count() {
        return report_third_count;
    }

    @Column(name = "report_type")
    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReport_type() {
        return report_type;
    }

    @Column(name = "sci_thesis_salary")
    public void setSci_thesis_salary(BigDecimal sci_thesis_salary) {
        this.sci_thesis_salary = sci_thesis_salary;
    }

    public BigDecimal getSci_thesis_salary() {
        return sci_thesis_salary;
    }

    @Column(name = "sci_thesis_count")
    public void setSci_thesis_second_count(Integer sci_thesis_second_count) {
        this.sci_thesis_second_count = sci_thesis_second_count;
    }

    public Integer getSci_thesis_second_count() {
        return sci_thesis_second_count;
    }

    @Column(name = "sci_thesis_third_count")
    public void setSci_thesis_third_count(Integer sci_thesis_third_count) {
        this.sci_thesis_third_count = sci_thesis_third_count;
    }

    public Integer getSci_thesis_third_count() {
        return sci_thesis_third_count;
    }

    @Column(name = "all_sumup")
    public void setAll_sumup(BigDecimal all_sumup) {
        this.all_sumup = all_sumup;
    }

    public BigDecimal getAll_sumup() {
        return all_sumup;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @GenericGenerator(name="generator", strategy="increment")
    @Id
    @GeneratedValue(generator="generator")
    @Column(name="usercode", unique=true, nullable=false)
    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }


//    @Column(name = "domain")
//    public String getDomain() {
//        return domain;
//    }
//
//    public void setDomain(String domain) {
//        this.domain = domain;
//    }
//
//    @Column(name = "declare_level")
//    public String getDeclare_level() {
//        return declare_level;
//    }
//
//    public void setDeclare_level(String declare_level) {
//        this.declare_level = declare_level;
//    }
}


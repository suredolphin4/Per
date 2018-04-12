package com.performances.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zxbing on 15-11-10.
 * 项目统计表
 */
@Entity
@Table(name = "project_statistic", catalog = "per")
public class TProjectStatistic {
	private Integer zdfound02;
	private Integer cjzdfound0201;
	private Integer jcqn07;
	private Integer zxfound06;
	private Integer cxqt0a;
	private Integer mission973;
	private Integer gjhz21;
	private Integer jctjpt14;
	private Integer jcxgzzx16;
	private Integer mission19;
	private Integer mission22;
	private Integer mission23;
	private Integer yzd31;
	private Integer yfx32;
	private Integer notcount;
	private Integer yqt39;
	private Integer other99;
	private Integer gjhz41;
	private Integer cjzdyjjh0301;
	private Integer msfound04;
	private Integer cjmsfound0401;
	private Integer mission86311;
	private Integer kjzc13;
	private Integer jg21;
	private Integer qywt25;
	private Integer yrc37;
	private Integer slyqy51;
	private Integer szfound52;
	private Integer qtyth09;
	private Integer zdfound01;
	private Integer cjzdfound0101;
	private Integer zdyjjh03;
	private Integer qnfound05;
	private Integer skfound0c;
	private Integer gyxhyzx17;
	private Integer hxwt24;
	private Integer cjjqrw0701;
	private Integer yqtzdsys36;
	private Integer kjzdzx15;
	private Integer cjzxfound0601;
	private Integer yyd33;
	private Integer yzdbs34;
	private Integer cjqnfound0501;
	private Integer yq08;
	private Integer dfrw12;
	private Integer ysts35;
	private Integer rkx18;
	private Integer yqzz0b;
	private String username;
	private String usercode;
	private String domain;
	private String declare_level;

	public TProjectStatistic(){

	}

	public TProjectStatistic(Integer zdfound02, Integer cjzdfound0201, Integer jcqn07, Integer zxfound06, Integer cxqt0a, Integer mission973, Integer gjhz21, Integer jctjpt14, Integer jcxgzzx16, Integer mission19, Integer mission22, Integer mission23, Integer yzd31, Integer yfx32, Integer notcount, Integer yqt39, Integer other99, Integer gjhz41, Integer cjzdyjjh0301, Integer msfound04, Integer cjmsfound0401, Integer mission86311, Integer kjzc13, Integer jg21, Integer qywt25, Integer yrc37, Integer slyqy51, Integer szfound52, Integer qtyth09, Integer zdfound01, Integer cjzdfound0101, Integer zdyjjh03, Integer qnfound05, Integer skfound0c, Integer gyxhyzx17, Integer hxwt24, Integer cjjqrw0701, Integer yqtzdsys36, Integer kjzdzx15, Integer cjzxfound0601, Integer yyd33, Integer yzdbs34, Integer cjqnfound0501, Integer yq08, Integer dfrw12, Integer ysts35, Integer rkx18, Integer yqzz0b, String username, String usercode, String domain, String declare_level) {
		this.zdfound02 = zdfound02;
		this.cjzdfound0201 = cjzdfound0201;
		this.jcqn07 = jcqn07;
		this.zxfound06 = zxfound06;
		this.cxqt0a = cxqt0a;
		this.mission973 = mission973;
		this.gjhz21 = gjhz21;
		this.jctjpt14 = jctjpt14;
		this.jcxgzzx16 = jcxgzzx16;
		this.mission19 = mission19;
		this.mission22 = mission22;
		this.mission23 = mission23;
		this.yzd31 = yzd31;
		this.yfx32 = yfx32;
		this.notcount = notcount;
		this.yqt39 = yqt39;
		this.other99 = other99;
		this.gjhz41 = gjhz41;
		this.cjzdyjjh0301 = cjzdyjjh0301;
		this.msfound04 = msfound04;
		this.cjmsfound0401 = cjmsfound0401;
		this.mission86311 = mission86311;
		this.kjzc13 = kjzc13;
		this.jg21 = jg21;
		this.qywt25 = qywt25;
		this.yrc37 = yrc37;
		this.slyqy51 = slyqy51;
		this.szfound52 = szfound52;
		this.qtyth09 = qtyth09;
		this.zdfound01 = zdfound01;
		this.cjzdfound0101 = cjzdfound0101;
		this.zdyjjh03 = zdyjjh03;
		this.qnfound05 = qnfound05;
		this.skfound0c = skfound0c;
		this.gyxhyzx17 = gyxhyzx17;
		this.hxwt24 = hxwt24;
		this.cjjqrw0701 = cjjqrw0701;
		this.yqtzdsys36 = yqtzdsys36;
		this.kjzdzx15 = kjzdzx15;
		this.cjzxfound0601 = cjzxfound0601;
		this.yyd33 = yyd33;
		this.yzdbs34 = yzdbs34;
		this.cjqnfound0501 = cjqnfound0501;
		this.yq08 = yq08;
		this.dfrw12 = dfrw12;
		this.ysts35 = ysts35;
		this.rkx18 = rkx18;
		this.yqzz0b = yqzz0b;
		this.username = username;
		this.usercode = usercode;
		this.domain = domain;
		this.declare_level = declare_level;
	}

	/**
	 * 重点基金
	 * @return
	 */
	@Column(name = "zdfound02")
	public Integer getZdfound02() {
		return zdfound02;
	}

	public void setZdfound02(Integer zdfound02) {
		this.zdfound02 = zdfound02;
	}

	@Column(name = "cjzdfound0201")
	public Integer getCjzdfound0201() {
		return cjzdfound0201;
	}

	public void setCjzdfound0201(Integer cjzdfound0201) {
		this.cjzdfound0201 = cjzdfound0201;
	}

	@Column(name = "jcqn07")
	public Integer getJcqn07() {
		return jcqn07;
	}

	public void setJcqn07(Integer jcqn07) {
		this.jcqn07 = jcqn07;
	}

	@Column(name = "zxfound06")
	public Integer getZxfound06() {
		return zxfound06;
	}

	public void setZxfound06(Integer zxfound06) {
		this.zxfound06 = zxfound06;
	}

	@Column(name = "cxqt0a")
	public Integer getCxqt0a() {
		return cxqt0a;
	}

	public void setCxqt0a(Integer cxqt0a) {
		this.cxqt0a = cxqt0a;
	}

	@Column(name = "mission973")
	public Integer getMission973() {
		return mission973;
	}

	public void setMission973(Integer mission973) {
		this.mission973 = mission973;
	}

	@Column(name = "gjhz21")
	public Integer getGjhz21() {
		return gjhz21;
	}

	public void setGjhz21(Integer gjhz21) {
		this.gjhz21 = gjhz21;
	}

	@Column(name = "jctjpt14")
	public Integer getJctjpt14() {
		return jctjpt14;
	}

	public void setJctjpt14(Integer jctjpt14) {
		this.jctjpt14 = jctjpt14;
	}

	@Column(name = "jcxgzzx16")
	public Integer getJcxgzzx16() {
		return jcxgzzx16;
	}

	public void setJcxgzzx16(Integer jcxgzzx16) {
		this.jcxgzzx16 = jcxgzzx16;
	}

	@Column(name = "mission19")
	public Integer getMission19() {
		return mission19;
	}

	public void setMission19(Integer mission19) {
		this.mission19 = mission19;
	}

	@Column(name = "mission22")
	public Integer getMission22() {
		return mission22;
	}

	public void setMission22(Integer mission22) {
		this.mission22 = mission22;
	}

	@Column(name = "mission23")
	public Integer getMission23() {
		return mission23;
	}

	public void setMission23(Integer mission23) {
		this.mission23 = mission23;
	}

	@Column(name = "yzd31")
	public Integer getYzd31() {
		return yzd31;
	}

	public void setYzd31(Integer yzd31) {
		this.yzd31 = yzd31;
	}

	@Column(name = "yfx32")
	public Integer getYfx32() {
		return yfx32;
	}

	public void setYfx32(Integer yfx32) {
		this.yfx32 = yfx32;
	}

	@Column(name = "notcount")
	public Integer getNotcount() {
		return notcount;
	}

	public void setNotcount(Integer notcount) {
		this.notcount = notcount;
	}

	@Column(name = "yqt39")
	public Integer getYqt39() {
		return yqt39;
	}

	public void setYqt39(Integer yqt39) {
		this.yqt39 = yqt39;
	}

	@Column(name = "other99")
	public Integer getOther99() {
		return other99;
	}

	public void setOther99(Integer other99) {
		this.other99 = other99;
	}

	@Column(name = "gjhz41")
	public Integer getGjhz41() {
		return gjhz41;
	}

	public void setGjhz41(Integer gjhz41) {
		this.gjhz41 = gjhz41;
	}

	@Column(name = "cjzdyjjh0301")
	public Integer getCjzdyjjh0301() {
		return cjzdyjjh0301;
	}

	public void setCjzdyjjh0301(Integer cjzdyjjh0301) {
		this.cjzdyjjh0301 = cjzdyjjh0301;
	}

	@Column(name = "msfound04")
	public Integer getMsfound04() {
		return msfound04;
	}

	public void setMsfound04(Integer msfound04) {
		this.msfound04 = msfound04;
	}

	@Column(name = "cjmsfound0401")
	public Integer getCjmsfound0401() {
		return cjmsfound0401;
	}

	public void setCjmsfound0401(Integer cjmsfound0401) {
		this.cjmsfound0401 = cjmsfound0401;
	}

	@Column(name = "mission86311")
	public Integer getMission86311() {
		return mission86311;
	}

	public void setMission86311(Integer mission86311) {
		this.mission86311 = mission86311;
	}

	@Column(name = "kjzc13")
	public Integer getKjzc13() {
		return kjzc13;
	}

	public void setKjzc13(Integer kjzc13) {
		this.kjzc13 = kjzc13;
	}

	@Column(name = "jg21")
	public Integer getJg21() {
		return jg21;
	}

	public void setJg21(Integer jg21) {
		this.jg21 = jg21;
	}

	@Column(name = "qywt25")
	public Integer getQywt25() {
		return qywt25;
	}

	public void setQywt25(Integer qywt25) {
		this.qywt25 = qywt25;
	}

	@Column(name = "yrc37")
	public Integer getYrc37() {
		return yrc37;
	}

	public void setYrc37(Integer yrc37) {
		this.yrc37 = yrc37;
	}

	@Column(name = "slyqy51")
	public Integer getSlyqy51() {
		return slyqy51;
	}

	public void setSlyqy51(Integer slyqy51) {
		this.slyqy51 = slyqy51;
	}

	@Column(name = "szfound52")
	public Integer getSzfound52() {
		return szfound52;
	}

	public void setSzfound52(Integer szfound52) {
		this.szfound52 = szfound52;
	}

	@Column(name = "qtyth09")
	public Integer getQtyth09() {
		return qtyth09;
	}

	public void setQtyth09(Integer qtyth09) {
		this.qtyth09 = qtyth09;
	}

	@Column(name = "zdfound01")
	public Integer getZdfound01() {
		return zdfound01;
	}

	public void setZdfound01(Integer zdfound01) {
		this.zdfound01 = zdfound01;
	}

	@Column(name = "cjzdfound0101")
	public Integer getCjzdfound0101() {
		return cjzdfound0101;
	}

	public void setCjzdfound0101(Integer cjzdfound0101) {
		this.cjzdfound0101 = cjzdfound0101;
	}

	@Column(name = "zdyjjh03")
	public Integer getZdyjjh03() {
		return zdyjjh03;
	}

	public void setZdyjjh03(Integer zdyjjh03) {
		this.zdyjjh03 = zdyjjh03;
	}

	@Column(name = "qnfound05")
	public Integer getQnfound05() {
		return qnfound05;
	}

	public void setQnfound05(Integer qnfound05) {
		this.qnfound05 = qnfound05;
	}

	@Column(name = "skfound0c")
	public Integer getSkfound0c() {
		return skfound0c;
	}

	public void setSkfound0c(Integer skfound0c) {
		this.skfound0c = skfound0c;
	}

	@Column(name = "gyxhyzx17")
	public Integer getGyxhyzx17() {
		return gyxhyzx17;
	}

	public void setGyxhyzx17(Integer gyxhyzx17) {
		this.gyxhyzx17 = gyxhyzx17;
	}

	@Column(name = "hxwt24")
	public Integer getHxwt24() {
		return hxwt24;
	}

	public void setHxwt24(Integer hxwt24) {
		this.hxwt24 = hxwt24;
	}

	@Column(name = "cjjqrw0701")
	public Integer getCjjqrw0701() {
		return cjjqrw0701;
	}

	public void setCjjqrw0701(Integer cjjqrw0701) {
		this.cjjqrw0701 = cjjqrw0701;
	}

	@Column(name = "yqtzdsys36")
	public Integer getYqtzdsys36() {
		return yqtzdsys36;
	}

	public void setYqtzdsys36(Integer yqtzdsys36) {
		this.yqtzdsys36 = yqtzdsys36;
	}

	@Column(name = "kjzdzx15")
	public Integer getKjzdzx15() {
		return kjzdzx15;
	}

	public void setKjzdzx15(Integer kjzdzx15) {
		this.kjzdzx15 = kjzdzx15;
	}

	@Column(name = "cjzxfound0601")
	public Integer getCjzxfound0601() {
		return cjzxfound0601;
	}

	public void setCjzxfound0601(Integer cjzxfound0601) {
		this.cjzxfound0601 = cjzxfound0601;
	}

	@Column(name = "yyd33")
	public Integer getYyd33() {
		return yyd33;
	}

	public void setYyd33(Integer yyd33) {
		this.yyd33 = yyd33;
	}

	@Column(name = "yzdbs34")
	public Integer getYzdbs34() {
		return yzdbs34;
	}

	public void setYzdbs34(Integer yzdbs34) {
		this.yzdbs34 = yzdbs34;
	}

	@Column(name = "cjqnfound0501")
	public Integer getCjqnfound0501() {
		return cjqnfound0501;
	}

	public void setCjqnfound0501(Integer cjqnfound0501) {
		this.cjqnfound0501 = cjqnfound0501;
	}

	@Column(name = "yq08")
	public Integer getYq08() {
		return yq08;
	}

	public void setYq08(Integer yq08) {
		this.yq08 = yq08;
	}

	@Column(name = "dfrw12")
	public Integer getDfrw12() {
		return dfrw12;
	}

	public void setDfrw12(Integer dfrw12) {
		this.dfrw12 = dfrw12;
	}

	@Column(name = "ysts35")
	public Integer getYsts35() {
		return ysts35;
	}

	public void setYsts35(Integer ysts35) {
		this.ysts35 = ysts35;
	}

	@Column(name = "rkx18")
	public Integer getRkx18() {
		return rkx18;
	}

	public void setRkx18(Integer rkx18) {
		this.rkx18 = rkx18;
	}

	@Column(name = "yqzz0b")
	public Integer getYqzz0b() {
		return yqzz0b;
	}

	public void setYqzz0b(Integer yqzz0b) {
		this.yqzz0b = yqzz0b;
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

	@Column(name = "domain")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "declare_level")
	public String getDeclare_level() {
		return declare_level;
	}

	public void setDeclare_level(String declare_level) {
		this.declare_level = declare_level;
	}
}

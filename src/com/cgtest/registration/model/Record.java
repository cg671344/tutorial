package com.cgtest.registration.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "record")
public class Record {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator  = "generator")
	private String id;
	
	@Column(name = "serial_no")
	private int serialNo;//序号
			  
	@Column(name = "zhizao_no")
	private int zhizaoNo;//制造号
	
	@Column(name = "hankou_no")
	private String hankouNo;//焊口编号
	
	@Column(name = "chart_no")
	private String chartNo;//等轴图号
	
	@Column(name = "report_no")			  
	private int reportNo;//报告编号
	
	@Column(name = "pianzi_number")
	private int pianziNumber;//片子数量
	
	@Column(name = "fanxiu_number")
	private int fanxiuNumber;//返修数量
	
	@Column(name = "jianyan_date")
	private String jianyanDate;//校验日期

	@Column(name = "jianyan_result")
	private String jianyanResult;//检验结果
	
	@Column(name = "guige1")
	private String guige1;//规格1
	
	@Column(name = "guige2")
	private String guige2;//规格2
	
	@Column(name = "rccm_level")
	private String rccmLevel;//RCCM级别
	
	@Column(name = "hanfeng_type")
	String hanfengType;//焊缝类型
	
	@Column(name = "weituo_unit")
	String weituoUnit;//委托单位
	
	@Column(name = "hangong_no")
	private String hangongNo;//焊工号
	
	@Column(name = "zone")
	private String zone;//区域
	
	@Column(name = "zone_class")
	private String zoneClass;//大区分类
	
	@Column(name = "create_time")
	private Date createTime;//记录生成时间
	
	@Column(name = "hankou_squence")
	@Enumerated(EnumType.STRING)
	private HankouSequence  hankouSequence;//记录生成时间
	
	public enum HankouSequence{
		FIRST,SECOND
	}
	
	//焊口编号以此结尾为二次焊接
	public enum SecondHanKouSuffix{
		R1, R2, X
	}
	
	public String getId() {
		return id;
	}

	public String getChartNo() {
		return chartNo;
	}

	public void setChartNo(String chartNo) {
		this.chartNo = chartNo;
	}

	public String getHankouNo() {
		return hankouNo;
	}

	public void setHankouNo(String hankouNo) {
		this.hankouNo = hankouNo;
	}

	public String getHangongNo() {
		return hangongNo;
	}

	public void setHangongNo(String hangongNo) {
		this.hangongNo = hangongNo;
	}

	public String getGuige1() {
		return guige1;
	}

	public void setGuige1(String guige1) {
		this.guige1 = guige1;
	}

	public String getGuige2() {
		return guige2;
	}

	public void setGuige2(String guige2) {
		this.guige2 = guige2;
	}

	public String getRccmLevel() {
		return rccmLevel;
	}

	public void setRccmLevel(String rccmLevel) {
		this.rccmLevel = rccmLevel;
	}

	public String getJianyanResult() {
		return jianyanResult;
	}

	public void setJianyanResult(String jianyanResult) {
		this.jianyanResult = jianyanResult;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZoneClass() {
		return zoneClass;
	}

	public void setZoneClass(String zoneClass) {
		this.zoneClass = zoneClass;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPianziNumber() {
		return pianziNumber;
	}

	public void setPianziNumber(int pianziNumber) {
		this.pianziNumber = pianziNumber;
	}

	public int getFanxiuNumber() {
		return fanxiuNumber;
	}

	public void setFanxiuNumber(int fanxiuNumber) {
		this.fanxiuNumber = fanxiuNumber;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getZhizaoNo() {
		return zhizaoNo;
	}

	public void setZhizaoNo(int zhizaoNo) {
		this.zhizaoNo = zhizaoNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJianyanDate() {
		return jianyanDate;
	}

	public void setJianyanDate(String jianyanDate) {
		this.jianyanDate = jianyanDate;
	}

	public String getHanfengType() {
		return hanfengType;
	}

	public void setHanfengType(String hanfengType) {
		this.hanfengType = hanfengType;
	}

	public String getWeituoUnit() {
		return weituoUnit;
	}

	public void setWeituoUnit(String weituoUnit) {
		this.weituoUnit = weituoUnit;
	}

	public HankouSequence getHankouSequence() {
		return hankouSequence;
	}

	public void setHankouSequence(HankouSequence hankouSequence) {
		this.hankouSequence = hankouSequence;
	}
}

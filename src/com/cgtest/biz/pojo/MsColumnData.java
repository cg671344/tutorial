package com.cgtest.biz.pojo;

public class MsColumnData{
	
	private String dataType;
	private String hangongName;
	private long hangkouNumber;
	private long unqualifiedhangkouNumber;
	private long dipianNumber;
	private long unqualifiedPianziNum;
	private double unqualifiedDistributeRate;
	private double zoneQualifiedRate;
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String zone) {
		this.dataType = zone;
	}
	public long getDipianNumber() {
		return dipianNumber;
	}
	public void setDipianNumber(long dipianNumber) {
		this.dipianNumber = dipianNumber;
	}
	public long getUnqualifiedPianziNum() {
		return unqualifiedPianziNum;
	}
	public void setUnqualifiedPianziNum(long unqualifiedPianziNum) {
		this.unqualifiedPianziNum = unqualifiedPianziNum;
	}
	public double getUnqualifiedDistributeRate() {
		return unqualifiedDistributeRate;
	}
	public void setUnqualifiedDistributeRate(double unqualifiedDistributeRate) {
		this.unqualifiedDistributeRate = unqualifiedDistributeRate;
	}
	public double getZoneQualifiedRate() {
		return zoneQualifiedRate;
	}
	public void setZoneQualifiedRate(double zoneQualifiedRate) {
		this.zoneQualifiedRate = zoneQualifiedRate;
	}
	public long getHangkouNumber() {
		return hangkouNumber;
	}
	public void setHangkouNumber(long hangkouNumber) {
		this.hangkouNumber = hangkouNumber;
	}
	public String getHangongName() {
		return hangongName;
	}
	public void setHangongName(String hangongName) {
		this.hangongName = hangongName;
	}
	public long getUnqualifiedhangkouNumber() {
		return unqualifiedhangkouNumber;
	}
	public void setUnqualifiedhangkouNumber(long unqualifiedhangkouNumber) {
		this.unqualifiedhangkouNumber = unqualifiedhangkouNumber;
	}
	
}
package com.cgtest.biz.pojo;

public class WeekData{
	private int weekSeq;	//��������
	private int month;	//�·�
	private long dipianNumber;//Ƭ������
	private long fanxiuNumber;//��������
	private long hangkouNumber;//������
	private String startDate;
	private String endDate;
	double  qulifiedRate;	//�ϸ���
	public int getWeekSeq() {
		return weekSeq;
	}
	public void setWeekSeq(int weekSeq) {
		this.weekSeq = weekSeq;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getDipianNumber() {
		return dipianNumber;
	}
	public void setDipianNumber(long dipianNumber) {
		this.dipianNumber = dipianNumber;
	}
	public long getFanxiuNumber() {
		return fanxiuNumber;
	}
	public void setFanxiuNumber(long fanxiuNumber) {
		this.fanxiuNumber = fanxiuNumber;
	}
	public double getQulifiedRate() {
		return qulifiedRate;
	}
	public void setQulifiedRate(double qulifiedRate) {
		this.qulifiedRate = qulifiedRate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public long getHangkouNumber() {
		return hangkouNumber;
	}
	public void setHangkouNumber(long hangkouNumber) {
		this.hangkouNumber = hangkouNumber;
	}
}
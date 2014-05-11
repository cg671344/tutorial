package com.cgtest.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//贫血模型 充血模型
@Entity
@Table(name = "setting")
public class Setting {
	private String id;
	
	@Column(name = "name",updatable=false)

	private SettingType name;
	
	@Column(name = "value")
	private String value;
	
	public enum SettingType{
		WeekAlertLimit("周合格率警戒值"),
		WeekAlertLimitSecondRT("周二次合格率警戒值"),
		AlertWeekNumber("与当期相聚几周报警"),
		BasePianziNumber("基准片子数量"),
		BaseFanxiuNumber("基准返修数量"),
		BasePianziNumberSecondRT("基准片子数量"),
		BaseFanxiuNumberSecondRT("基准返修数量"),
		BaseRecordDate("基准片子数据日期"),
		CumulativeRateStartDate("累计合格率其实时间");
		private String description;
		SettingType(String description){
			this.description = description;
		}
	}
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator  = "generator")
	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	public SettingType getName() {
		return name;
	}

	public void setName(SettingType name) {
		this.name = name;
	}
}

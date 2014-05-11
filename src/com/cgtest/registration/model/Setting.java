package com.cgtest.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//ƶѪģ�� ��Ѫģ��
@Entity
@Table(name = "setting")
public class Setting {
	private String id;
	
	@Column(name = "name",updatable=false)

	private SettingType name;
	
	@Column(name = "value")
	private String value;
	
	public enum SettingType{
		WeekAlertLimit("�ܺϸ��ʾ���ֵ"),
		WeekAlertLimitSecondRT("�ܶ��κϸ��ʾ���ֵ"),
		AlertWeekNumber("�뵱����ۼ��ܱ���"),
		BasePianziNumber("��׼Ƭ������"),
		BaseFanxiuNumber("��׼��������"),
		BasePianziNumberSecondRT("��׼Ƭ������"),
		BaseFanxiuNumberSecondRT("��׼��������"),
		BaseRecordDate("��׼Ƭ����������"),
		CumulativeRateStartDate("�ۼƺϸ�����ʵʱ��");
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

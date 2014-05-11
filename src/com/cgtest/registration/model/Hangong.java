package com.cgtest.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "hangong")
public class Hangong {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(generator  = "generator")
	@Column(name = "hangongNo")
	private String hangongNo;
	
	@Column(name = "hangongName")
	private String hangongName;

	public String getHangongNo() {
		return hangongNo;
	}

	public void setHangongNo(String hangongNo) {
		this.hangongNo = hangongNo;
	}

	public String getHangongName() {
		return hangongName;
	}

	public void setHangongName(String hangongName) {
		this.hangongName = hangongName;
	}
}

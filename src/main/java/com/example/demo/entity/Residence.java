package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_info")
public class Residence {
	@Id
	@Column(name = "employee_number")
	private int employee_number;
	@Column(name = "name")
	private String name;
	@Column(name = "furigana")
	private String furigana;
	@Column(name = "address")
	private String address;
	@Column(name = "address_furigana")
	private String address_furigana;
	@Column(name = "commute_route")
	private String commute_route;
	@Column(name = "bicycle_use")
	private String bicycle_use;
	@Column(name = "bus_route")
	private String bus_route;
	public int getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(int employee_number) {
		this.employee_number = employee_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFurigana() {
		return furigana;
	}
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_furigana() {
		return address_furigana;
	}
	public void setAddress_furigana(String address_furigana) {
		this.address_furigana = address_furigana;
	}
	public String getCommute_route() {
		return commute_route;
	}
	public void setCommute_route(String commute_route) {
		this.commute_route = commute_route;
	}
	public String getBicycle_use() {
		return bicycle_use;
	}
	public void setBicycle_use(String bicycle_use) {
		this.bicycle_use = bicycle_use;
	}
	public String getBus_route() {
		return bus_route;
	}
	public void setBus_route(String bus_route) {
		this.bus_route = bus_route;
	}
	
	
	

	
}

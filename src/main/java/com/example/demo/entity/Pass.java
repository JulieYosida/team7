package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "pass")
public class Pass {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
@Column(name = "application_date")
private String application_date;
@Column(name = "name")
private String name;
@Column(name = "furigana")
private String furigana;
@Column(name = "address")
private String address;
@Column(name = "bus_route")
private String bus_route;
@Column(name = "bus_period")
private String bus_period;
@Column(name = "bus_fare")
private String bus_fare;
@Column(name = "train_route")
private String train_route;
@Column(name = "train_period")
private String train_period;
@Column(name = "train_fare")
private String train_fare;
@Column(name = "total_fare")
private String total_fare;
@Column(name = "employee_number")
private int employee_number;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getApplicationDate() {
	return application_date;
}
public void setApplicationDate(String application_date) {
	this.application_date = application_date;
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
public String getBusRoute() {
	return bus_route;
}
public void setBusRoute(String busRoute) {
	this.bus_route = busRoute;
}
public String getBusPeriod() {
	return bus_period;
}
public void setBusPeriod(String busPeriod) {
	this.bus_period = busPeriod;
}
public String getBusFare() {
	return bus_fare;
}
public void setBusFare(String busFare) {
	this.bus_fare = busFare;
}
public String getTrainRoute() {
	return train_route;
}
public void setTrainRoute(String trainRoute) {
	this.train_route = trainRoute;
}
public String getTrainPeriod() {
	return train_period;
}
public void setTrainPeriod(String trainPeriod) {
	this.train_period = trainPeriod;
}
public String getTrainFare() {
	return train_fare;
}
public void setTrainFare(String trainFare) {
	this.train_fare = trainFare;
}
public String getTotalFare() {
	return total_fare;
}
public void setTotalFare(String totalFare) {
	this.total_fare = totalFare;
}
public int getEmployeeNumber() {
    return employee_number;
}
public void setEmployeeNumber(int employeeNumber) {
	this.employee_number = employeeNumber;
}



}
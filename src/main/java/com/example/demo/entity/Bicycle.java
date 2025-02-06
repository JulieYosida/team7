package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name = "bicycle")
public class Bicycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "application_date")
	private String ApplicationDate;
	
	@Column(name = "employee_number")
	private String EmployeeNumber;
	
	@Column(name = "name")
	private String Name;
	
	@Column(name = "name_furigana")
	private String NameFurigana;
	
	@Column(name = "department")
	private String Department;
	@Column(name = "address")
	private String Address;
	@Column(name = "subsidy_amount")
	private String SubsidyAmount;
	
	@Lob
	@Column(name = "parking_proof_path")
	private byte[] ParkingProofPath;
	
	@Lob
	@Column(name = "insurance_proof_path")
    private byte[] InsuranceProofPath;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getApplicationDate() {
		return ApplicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		ApplicationDate = applicationDate;
	}
	public String getEmployeeNumber() {
		return EmployeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		EmployeeNumber = employeeNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNameFurigana() {
		return NameFurigana;
	}
	public void setNameFurigana(String nameFurigana) {
		NameFurigana = nameFurigana;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSubsidyAmount() {
		return SubsidyAmount;
	}
	public void setSubsidyAmount(String subsidyAmount) {
		SubsidyAmount = subsidyAmount;
	}
	public byte[] getParkingProofPath() {
		return ParkingProofPath;
	}
	public void setParkingProofPath(byte[] parkingProofPath) {
		ParkingProofPath = parkingProofPath;
	}
	public byte[] getInsuranceProofPath() {
		return InsuranceProofPath;
	}
	public void setInsuranceProofPath(byte[] insuranceProofPath) {
		InsuranceProofPath = insuranceProofPath;
	}
}

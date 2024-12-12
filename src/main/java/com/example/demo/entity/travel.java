package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "travel")
public class travel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "application_date")
    private String applicationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "furigana")
    private String furigana;

    @Column(name = "departure_date")
    private String departureDate;

    @Column(name = "departure_station")
    private String departureStation;

    @Column(name = "destination_station")
    private String destinationStation;

    @ElementCollection
    @CollectionTable(name = "transportation_methods")
    @Column(name = "transportation")
    private List<String> transportation;

    @Column(name = "other_text")
    private String otherText;

    @Column(name = "total_fare")
    private Double totalFare;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public List<String> getTransportation() {
		return transportation;
	}

	public void setTransportation(List<String> transportation) {
		this.transportation = transportation;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}
    
    
	
}

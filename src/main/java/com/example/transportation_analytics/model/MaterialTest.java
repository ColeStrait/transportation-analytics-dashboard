package com.example.transportation_analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MaterialTest {

	@Id
	@GeneratedValue
	private Long id;

	private String sampleId;
	private double density;
	private double airVoids;

	public MaterialTest() {
	}

	public MaterialTest(String sampleId, double density, double airVoids) {

		this.sampleId = sampleId;
		this.density = density;
		this.airVoids = airVoids;
	}

	public Long getId() {
		return id;
	}

	public String getSampleId() {
		return sampleId;
	}

	public double getDensity() {
		return density;
	}

	public double getAirVoids() {
		return airVoids;

	}
}

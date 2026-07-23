package com.example.transportation_analytics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.transportation_analytics.model.MaterialTest;
import com.example.transportation_analytics.repository.MaterialTestRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class MaterialTestLoader implements CommandLineRunner {

	private final MaterialTestRepository repository;

	public MaterialTestLoader(MaterialTestRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/sample-material-tests.csv")));
		String line;
		reader.readLine();

		while ((line = reader.readLine()) != null) {
			String[] values = line.split(",");

			String sampleId = values[0];
			double density = Double.parseDouble(values[1]);
			double airVoids = Double.parseDouble(values[2]);

			MaterialTest test = new MaterialTest(sampleId, density, airVoids);
			repository.save(test);
		}

		System.out.println("All material tests ");
		System.out.println("CSV data");

		for (MaterialTest materialTest : repository.findAll()) {
			System.out.println(materialTest.getSampleId() + " - " + materialTest.getDensity() + " - "
					+ materialTest.getAirVoids());

		}
		System.out.println();

		double totalDensity = 0;

		for (MaterialTest materialTest : repository.findAll()) {
			totalDensity += materialTest.getDensity();
		}

		double averageDensity = totalDensity / repository.findAll().size();

		double totalAirVoids = 0;

		for (MaterialTest materialTest : repository.findAll()) {
			totalAirVoids += materialTest.getAirVoids();
		}
		double averageAirVoids = totalAirVoids / repository.findAll().size();

		MaterialTest highestDensitySample = null;

		for (MaterialTest materialTest : repository.findAll()) {
			if (highestDensitySample == null || materialTest.getDensity() > highestDensitySample.getDensity()) {
				highestDensitySample = materialTest;
			}
		}

		MaterialTest lowestDensitySample = null;

		for (MaterialTest materialTest : repository.findAll()) {
			if (lowestDensitySample == null || materialTest.getDensity() < lowestDensitySample.getDensity()) {
				lowestDensitySample = materialTest;
			}
		}

		MaterialTest highestAirVoidSample = null;

		for (MaterialTest materialTest : repository.findAll()) {
			if (highestAirVoidSample == null || materialTest.getAirVoids() > highestAirVoidSample.getAirVoids()) {
				highestAirVoidSample = materialTest;
			}
		}

		MaterialTest lowestAirVoidSample = null;

		for (MaterialTest materialTest : repository.findAll()) {
			if (lowestAirVoidSample == null || materialTest.getAirVoids() < lowestAirVoidSample.getAirVoids()) {
				lowestAirVoidSample = materialTest;
			}
		}
		System.out.println("===============================");
		System.out.println("Transportation Materials Analytics Report");
		System.out.println("===============================");

		System.out.println();

		System.out.println("Samples Tested: " + repository.findAll().size());
		System.out.printf("Average Density: %.2f%n", averageDensity);
		System.out.printf("Average Air Voids: %.2f%n", averageAirVoids);

		System.out.println();

		System.out.println("Highest Density Sample: " + highestDensitySample.getSampleId() + " (Density = "
				+ highestDensitySample.getDensity() + ")");
		System.out.println("Lowest Density Sample: " + lowestDensitySample.getSampleId() + " (Density = "
				+ lowestDensitySample.getDensity() + ")");

		System.out.println();

		System.out.println("Highest Air Voids Sample: " + highestAirVoidSample.getSampleId() + " (Air Voids = "
				+ highestAirVoidSample.getAirVoids() + ")");
		System.out.println("Lowest Air Voids Sample: " + lowestAirVoidSample.getSampleId() + " (Air Voids = "
				+ lowestAirVoidSample.getAirVoids() + ")");

	}
}

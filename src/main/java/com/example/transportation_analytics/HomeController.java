package com.example.transportation_analytics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.transportation_analytics.repository.MaterialTestRepository;
import com.example.transportation_analytics.model.MaterialTest;

@Controller
public class HomeController {

	private final MaterialTestRepository repository;

	public HomeController(MaterialTestRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("materialTests", repository.findAll());
		model.addAttribute("sampleCount", repository.findAll().size());

		double totalDensity = 0;

		for (MaterialTest test : repository.findAll()) {
			totalDensity += test.getDensity();
		}

		double averageDensity = totalDensity / repository.findAll().size();
		model.addAttribute("averageDensity", averageDensity);

		double totalAirVoids = 0;

		for (MaterialTest test : repository.findAll()) {
			totalAirVoids += test.getAirVoids();
		}
		double averageAirVoids = totalAirVoids / repository.findAll().size();
		model.addAttribute("averageAirVoids", averageAirVoids);

		MaterialTest highestDensitySample = null;

		for (MaterialTest test : repository.findAll()) {
			if (highestDensitySample == null || test.getDensity() > highestDensitySample.getDensity()) {
				highestDensitySample = test;
			}
		}
		model.addAttribute("highestDensitySample", highestDensitySample);

		MaterialTest lowestDensitySample = null;

		for (MaterialTest test : repository.findAll()) {
			if (lowestDensitySample == null || test.getDensity() < lowestDensitySample.getDensity()) {
				lowestDensitySample = test;
			}
		}
		model.addAttribute("lowestDensitySample", lowestDensitySample);

		MaterialTest highestAirVoidSample = null;

		for (MaterialTest test : repository.findAll()) {
			if (highestAirVoidSample == null || test.getAirVoids() > highestAirVoidSample.getAirVoids()) {
				highestAirVoidSample = test;
			}
		}

		model.addAttribute("highestAirVoidSample", highestAirVoidSample);

		MaterialTest lowestAirVoidSample = null;

		for (MaterialTest test : repository.findAll()) {
			if (lowestAirVoidSample == null || test.getAirVoids() < lowestAirVoidSample.getAirVoids()) {
				lowestAirVoidSample = test;
			}
		}
		model.addAttribute("lowestAirVoidSample", lowestAirVoidSample);

		return "index";
	}
}
package com.nefertiti.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.nefertiti.model.LocationStats;
import com.nefertiti.service.CoronVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	private CoronVirusDataService coronaVirusDataService;
	

	@GetMapping("/")
	public String home(
			Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStatsSortState();
		allStats.sort((p1, p2) -> p2.getState().compareToIgnoreCase(p1.getState()));
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}
	
//	@GetMapping("/country")
//	public String country(Model model) {
//		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
//		allStats.sort((p1, p2) -> p1.getCountry().compareToIgnoreCase(p2.getCountry()));
//		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
//		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
//		
//		model.addAttribute("locationStats", allStats);
//		model.addAttribute("totalReportedCases", totalReportedCases);
//		model.addAttribute("totalNewCases", totalNewCases);
//		return "home";
//	}
//	@GetMapping("/latestTotalCases")
//	public String latestTotalCases(Model model) {
//		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
//		allStats.sort((p1, p2) -> p1.getLatestTotalCases()-p2.getLatestTotalCases());
//		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
//		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
//		
//		model.addAttribute("locationStats", allStats);
//		model.addAttribute("totalReportedCases", totalReportedCases);
//		model.addAttribute("totalNewCases", totalNewCases);
//		return "home";
//	}
//	@GetMapping("/diffFromPrevDay")
//	public String diffFromPrevDay(Model model) {
//		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
//		allStats.sort((p1, p2) -> p1.getDiffFromPrevDay() -p2.getDiffFromPrevDay());
//		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
//		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
//		
//		model.addAttribute("locationStats", allStats);
//		model.addAttribute("totalReportedCases", totalReportedCases);
//		model.addAttribute("totalNewCases", totalNewCases);
//		return "home";
//	}
}

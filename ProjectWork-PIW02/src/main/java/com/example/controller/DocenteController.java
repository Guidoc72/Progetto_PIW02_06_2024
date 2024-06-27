package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocenteController {
	
	 @GetMapping("/landingPageDocente")
	    public String showLandingPageDocente() {
	        return "landingPageDocente";  // Questo restituidce il template landingPageDocente.html
	    }

}

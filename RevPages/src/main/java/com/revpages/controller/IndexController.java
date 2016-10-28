package com.revpages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/revPages")
	public String Homepage() {
		return "index.html";
	}
}

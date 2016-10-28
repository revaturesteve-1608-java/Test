package com.revpages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.revpages.service.BusinessLogic;

@RestController
public class CreateCategoryController {
	
	@Autowired
	BusinessLogic service;
	
//	/**
//	 * URL mapping for creating a new category
//	 * @param categoryName - name of the category
//	 */
//	@RequestMapping(value="/createCategory")
//	public void createCategory(@RequestBody String categoryName){
//		service.createForumCategory(categoryName);
//	}
}

package com.techolution.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techolution.food.service.FoodRatingService;

@RestController
public class FoodRatingController {
	
	@Autowired
	FoodRatingService ratingService;
	
	/**
	 * This is uri endpoint for getting total satisfaction
	 * @return Integer
	 */
	@RequestMapping(value="/satisfaction",method=RequestMethod.GET)
	public Integer getSatisfaction(){
		
		return ratingService.getSatisfaction();
	}

}

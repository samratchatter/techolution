package com.techolution.food.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.techolution.food.config.FoodRatingConfig;
import com.techolution.food.dto.FoodRatingDto;


@Service
public class FoodRatingService {

	Logger logger= LoggerFactory.getLogger(FoodRatingService.class);
	
	/**
	 * calculates the maximum achievable satisfaction
	 * 
	 * @return Integer
	 */
	public Integer getSatisfaction(){
		
		Set<Integer> satisfactionIndexSet=FoodRatingConfig.foodRatingMap.keySet();
		Integer accumulator = 0;
		Integer totalSatisfaction=0;
		for(Integer satisfactionIndex:satisfactionIndexSet){
			
			List<FoodRatingDto> ratingList = FoodRatingConfig.foodRatingMap.get(satisfactionIndex);
			for(FoodRatingDto rating:ratingList){
				if(rating.getTime()<FoodRatingConfig.maxTime-accumulator){
					totalSatisfaction+=rating.getSatisfaction();
					accumulator+=rating.getTime();
					if(accumulator==10000){
						return totalSatisfaction;
					}
				}
				else{
					logger.info(rating.getTime().toString());
				}
			}
		}
		
		logger.info("accumulator is"+accumulator);
		
		return totalSatisfaction;
	}
}

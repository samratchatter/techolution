package com.techolution.food.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.techolution.food.dto.FoodRatingDto;
import com.techolution.food.service.FoodRatingService;

@Configuration
public class FoodRatingConfig {
	
	Logger logger= LoggerFactory.getLogger(FoodRatingConfig.class);
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public static Map<Integer,List<FoodRatingDto>> foodRatingMap;
	
	public static Integer maxTime;
	
	/**
	 * @throws IOException
	 * loads data from file to maps during startup of application
	 */
	@PostConstruct
	private void loadConfig() throws IOException{
		List<FoodRatingDto> ratingList=new ArrayList<FoodRatingDto>();
		//loading data from file
		Resource resource=resourceLoader.getResource("classpath:/data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		String header = br.readLine();
		String[] headers=header.split(" ");
		Integer menuCount =Integer.valueOf(headers[1]);
		maxTime=Integer.valueOf(headers[0]);
		for(int count=0;count<menuCount;count++){
			String[] rows=br.readLine().split(" ");
			FoodRatingDto ratingDto=new FoodRatingDto();
			ratingDto.setSatisfaction(Integer.valueOf(rows[0]));
			ratingDto.setTime(Integer.valueOf(rows[1]));
			ratingList.add(ratingDto);
		}
		
		logger.info("total number of foodRating"+ ratingList.size());
	/*loading data into a map based on satisfaction index */
	Map<Integer,List<FoodRatingDto>> ratingMap= ratingList.stream().collect(Collectors.groupingBy(rating->rating.getSatisfaction()/rating.getTime(),Collectors.toList()));
		 foodRatingMap=new TreeMap<Integer, List<FoodRatingDto>>(Collections.reverseOrder());
		foodRatingMap.putAll(ratingMap);
		
		logger.info("total number of foodRating"+ foodRatingMap.size());
		
	}

}

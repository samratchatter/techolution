package com.techolution.food.dto;

import java.io.Serializable;

public class FoodRatingDto implements Serializable {
	
	private Integer satisfaction;
	private Integer time;
	public Integer getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
}

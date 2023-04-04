package com.boot.bookingrestaurantapi.jsons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRest {

	@JsonProperty("restaurantId")
	private Long restaurantId;
	
	@JsonProperty("locator")
	private String locator;
	
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("person")
	private Long person;
	
	
	@JsonProperty("turn")
	private String turn;


	public Long getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getLocator() {
		return locator;
	}


	public void setLocator(String locator) {
		this.locator = locator;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Long getPerson() {
		return person;
	}


	public void setPerson(Long person) {
		this.person = person;
	}


	public String getTurn() {
		return turn;
	}


	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	
	
	
}

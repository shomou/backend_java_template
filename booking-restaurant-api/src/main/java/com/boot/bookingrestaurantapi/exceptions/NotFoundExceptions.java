package com.boot.bookingrestaurantapi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.boot.bookingrestaurantapi.dtos.ErrorDto;

public class NotFoundExceptions extends BookingException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundExceptions(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}
	
	
	public NotFoundExceptions(String code, int responseCode, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}
	
	public NotFoundExceptions(String code, int responseCode, String message, ErrorDto data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}

}

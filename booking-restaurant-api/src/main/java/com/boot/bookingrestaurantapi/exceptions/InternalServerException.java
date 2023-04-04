package com.boot.bookingrestaurantapi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.boot.bookingrestaurantapi.dtos.ErrorDto;

public class InternalServerException extends BookingException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

	public InternalServerException(String code, int responseCode, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServerException(String code, int responseCode, String message, ErrorDto data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}

}

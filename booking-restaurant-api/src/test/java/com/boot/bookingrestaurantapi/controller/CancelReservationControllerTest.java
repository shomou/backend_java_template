package com.boot.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.controllers.CancelReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

public class CancelReservationControllerTest {
	
	private static final  String LOCATOR = "Burger 7";
	
	private static final String SUCCESS_STATUS = "Succes";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String SUCCESS_MESSAGE = "OK";
	private static final String RESERVATION_DELETE= "LOCATOR_DELETE";
	
		
	@Mock		
	CancelReservationService cancelReservationService;
	
	@InjectMocks
	CancelReservationController cancelReservationController;
	
	@Before
	public void init() throws BookingException {		
		MockitoAnnotations.initMocks(this);
		
		
	
	}
	
	@Test
	public void deleteReservationTest()throws BookingException {
		
		Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETE);
		
		final BookingResponse<String> response =  cancelReservationController.deleteReservation(LOCATOR);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), RESERVATION_DELETE);
		
	}
	

}

package com.boot.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.controllers.ReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationControllerTest {

	private static final Long RESERVATION_ID = 1L;

	private static final String SUCCESS_STATUS = "Succes";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String SUCCESS_MESSAGE = "OK";
	
	private static final Long RESTAURAN_ID = 1L;
	private static final Long PERSON = 1L;
	private static final Long TURN = 1L;
	private static final Date DATE = new Date();
	private static final String LOCATOR = "Burger 2";
	

	public static final ReservationRest RESERVATION_REST = new ReservationRest();

	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	
	
	@Mock
	ReservationService reservationService;

	@InjectMocks
	ReservationController reservationController;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);

		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURAN_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN);
		
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		
		Mockito.when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
	}
	
	@Test
	public void getReservationByIdTest() throws BookingException {
		final BookingResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);

		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), RESERVATION_REST);
	}
	
	
	@Test
	public void createReservation() throws BookingException{
		final BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), LOCATOR);
	}
	

}

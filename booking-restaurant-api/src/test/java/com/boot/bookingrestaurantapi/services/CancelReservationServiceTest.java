package com.boot.bookingrestaurantapi.services;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {

	private static final String LOCATOR = "Burger 7";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";

	private static final Reservation RESERVATION = new Reservation();
	
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private CancelReservationServiceImpl cancelReservationServiceImpl;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteReservationOK() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));

		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		
		assertEquals(response , RESERVATION_DELETED);
		
	}
	
	
	
	@Test(expected = BookingException.class)
	public void deleteReservationNotFoundError() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		

		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		
		assertEquals(response , RESERVATION_DELETED);
		fail();	
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationErrorTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		
		Mockito.doThrow(Exception.class).when(reservationRepository).deleteByLocator(LOCATOR);

		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		
		assertEquals(response , RESERVATION_DELETED);
		fail();
	}

	

}

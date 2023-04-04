package com.boot.bookingrestaurantapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerException;
import com.boot.bookingrestaurantapi.exceptions.NotFoundExceptions;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	public String deleteReservation(String locator) throws BookingException {

		// Validar que la reserva exista
		reservationRepository.findByLocator(locator)
				.orElseThrow(() -> new NotFoundExceptions("LOCATOR_NOT__FOUND", "LOCATOR_NOT_FOUND"));

		try {
			reservationRepository.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "LOCATOR_DELETED";
	}

}

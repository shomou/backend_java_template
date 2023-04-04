package com.boot.bookingrestaurantapi.services.impl;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerException;
import com.boot.bookingrestaurantapi.exceptions.NotFoundExceptions;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private TurnRepository turnRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(Long reservationId) throws BookingException {

		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {

		// Validar que el restaurante exista
		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundExceptions("RESTAURANT_NOT__FOUND", "RESTAURANT_NOT_FOUND"));

		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundExceptions("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		
		if(reservationRepository.findByTurnAndRestaurantId(turn.getName(), restaurantId.getId()).isPresent()){
			throw new NotFoundExceptions("RESERVATION_ALREADY_EXISTS", "RESERVATION_ALREADY_EXISTS");
		}
		

		String locator = generateLocator(restaurantId, createReservationRest);

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());

		try {
			reservationRepository.save(reservation);
		}catch(final Exception e ) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
		
		return locator;
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundExceptions("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest)
			throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}

}

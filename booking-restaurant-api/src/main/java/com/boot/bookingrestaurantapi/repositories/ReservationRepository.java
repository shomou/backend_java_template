package com.boot.bookingrestaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.boot.bookingrestaurantapi.entities.Reservation;

import javax.transaction.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	Optional<Reservation> findById(Long id);
	
	Optional<Reservation> findByLocator(String locator);
	
	@Modifying
	@Transactional
	Optional<Reservation> deleteByLocator(String locator);

	Optional<Reservation> findByTurnAndRestaurantId (String turn, Long restaurantId);
	
}

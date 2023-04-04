package com.boot.bookingrestaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.bookingrestaurantapi.entities.Turn;

public interface TurnRepository extends JpaRepository<Turn, Long> {
	
	Optional<Turn> findById(Long id);
	
	

}

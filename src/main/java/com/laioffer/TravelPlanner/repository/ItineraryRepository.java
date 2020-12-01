package com.laioffer.TravelPlanner.repository;

import com.laioffer.TravelPlanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, String> {
}

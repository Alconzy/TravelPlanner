package com.laioffer.TravelPlanner.repository;

import com.laioffer.TravelPlanner.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {
    Attraction findByPlaceId(String placeId);
}

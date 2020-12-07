package com.laioffer.TravelPlanner.repository;

import com.laioffer.TravelPlanner.entity.ItineraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryItemRepository extends JpaRepository<ItineraryItem, Integer> {

}

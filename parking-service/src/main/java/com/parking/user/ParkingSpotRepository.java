package com.parking.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    
    // This replaces findByIsOccupiedFalse
    List<ParkingSpot> findByStatus(String status);
}
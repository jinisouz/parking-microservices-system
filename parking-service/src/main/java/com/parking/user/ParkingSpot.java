package com.parking.user;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotNumber; // e.g., "A-101"
    
    private String slotType; // e.g., "CAR", "BIKE", "TRUCK"
    
    // Changed from boolean to String to store "AVAILABLE" or "OCCUPIED"
    private String status; 

    // Default Constructor
    public ParkingSpot() {
        this.status = "AVAILABLE"; // Default value for new slots
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    
    public String getSlotType() { return slotType; }
    public void setSlotType(String slotType) { this.slotType = slotType; }
    
    // This is the method your Controller was looking for!
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
}
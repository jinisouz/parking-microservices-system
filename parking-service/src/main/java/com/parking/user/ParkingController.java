package com.parking.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingSpotRepository repository;

    public ParkingController(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    // 1. Add a new parking slot
    @PostMapping("/slots")
    public ParkingSpot addSlot(@RequestBody ParkingSpot spot) {
        return repository.save(spot);
    }

    // 2. Get all slots
    @GetMapping("/slots")
    public List<ParkingSpot> getAllSlots() {
        return repository.findAll();
    }

    // 3. Get only available (free) slots
    @GetMapping("/slots/available")
    public List<ParkingSpot> getAvailableSlots() {
        // We now search for the String "AVAILABLE" instead of a boolean
        return repository.findByStatus("AVAILABLE");
    }

    // 4. Update status (Used by Booking Service)
    @PutMapping("/updateStatus/{id}")
    public void updateStatus(@PathVariable Long id, @RequestParam String status) {
        repository.findById(id).ifPresent(slot -> {
            slot.setStatus(status);
            repository.save(slot);
        });
    }
}
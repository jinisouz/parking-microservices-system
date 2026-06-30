package com.booking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/doBooking")
    public String bookSpot(@RequestParam Long userId, @RequestParam Long slotId) {
        double price = 20.0; 

        // 1. Call User Service to deduct money
        String paymentUrl = "http://USER-SERVICE/users/" + userId + "/deduct?amount=" + price;
        
        try {
            // Using postForObject because we updated UserController to @PostMapping
            Boolean paymentOk = restTemplate.postForObject(paymentUrl, null, Boolean.class);

            if (Boolean.TRUE.equals(paymentOk)) {
                
                // 2. Update Parking Status
                String parkingUrl = "http://PARKING-SERVICE/parking/updateStatus/" + slotId + "?status=OCCUPIED";
                restTemplate.put(parkingUrl, null);

                // 3. Save Booking Record
                Booking booking = new Booking();
                booking.setUserId(userId);
                booking.setParkingSpotId(slotId);
                booking.setBookingTime(LocalDateTime.now());
                booking.setStatus("PAID & CONFIRMED");
                bookingRepository.save(booking);

                return "Success! Payment of $" + price + " received. Slot " + slotId + " is confirmed.";
            
            } else {
                return "Booking Failed: Insufficient balance or User not found.";
            }
        } catch (Exception e) {
            return "Booking Failed: Service Communication Error. " + e.getMessage();
        }
    }
}
package cabbooking.controller;

import cabbooking.Booking;
import cabbooking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("/create")
    public Booking createNewBooking(@RequestBody Booking booking) {
        return service.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Optional<Booking> fetchBooking(@PathVariable String id) {
        return service.fetchBookingById(id);
    }

    @GetMapping("/all")
    public List<Booking> retrieveAllBookings() {
        return service.fetchAllBookings();
    }

    @GetMapping("/user/{userId}")
    public List<Booking> retrieveBookingsByUser(@PathVariable String userId) {
        return service.fetchBookingsByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<Booking> retrieveBookingsByStatus(@PathVariable int status) {
        return service.fetchBookingsByStatus(status);
    }

    @GetMapping("/driver/{driverId}")
    public List<Booking> retrieveBookingsByDriver(@PathVariable String driverId) {
        return service.fetchBookingsByDriver(driverId);
    }

    @PutMapping("/update/{id}/status1")
    public Booking modifyStatusToOne(@PathVariable String id) {
        return service.modifyBookingStatus(id, 1);
    }

    @PutMapping("/update/{id}/status2")
    public Booking modifyStatusToTwo(@PathVariable String id) {
        return service.modifyBookingStatus(id, 2);
    }

    @PutMapping("/update/{id}/paymentstatus")
    public ResponseEntity<Booking> changePayment(@PathVariable String id) {
        try {
            Booking updatedBooking = service.changePaymentStatus(id, 1);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}/totalfee")
    public ResponseEntity<?> modifyTotalFee(@PathVariable String id, @RequestBody Map<String, Double> request) {
        if (!request.containsKey("totalfee")) {
            return ResponseEntity.badRequest().body("Missing 'totalfee' field in request body");
        }
        double totalFee = request.get("totalfee");
        Booking updatedBooking = service.updateFee(id, totalFee);
        if (updatedBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking ID not found");
        }
        return ResponseEntity.ok(updatedBooking);
    }

    @PutMapping("/update/{id}/driver")
    public Booking allocateDriver(@PathVariable String id, @RequestBody Booking booking) {
        return service.assignDriverToBooking(id, booking.getDriverid());
    }

    @PutMapping("/update/{id}/traveldistance")
    public ResponseEntity<?> modifyDistance(@PathVariable String id, @RequestBody Map<String, Integer> request) {
        if (!request.containsKey("travelDistance")) {
            return ResponseEntity.badRequest().body("Missing 'travelDistance' field in request body");
        }
        int travelDistance = request.get("travelDistance");
        Booking updatedBooking = service.modifyTravelDistance(id, travelDistance);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/delete/{id}")
    public String removeBookingById(@PathVariable String id) {
        return service.removeBooking(id);
    }
}




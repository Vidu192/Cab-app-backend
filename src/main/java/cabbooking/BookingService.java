package cabbooking;

import cabbooking.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public Booking createBooking(Booking newBooking) {
        return repo.save(newBooking);
    }

    public Optional<Booking> fetchBookingById(String bookingId) {
        return repo.findById(bookingId);
    }

    public List<Booking> fetchAllBookings() {
        return repo.findAll();
    }

    public List<Booking> fetchBookingsByUser(String userId) {
        return repo.findByUserid(userId);
    }

    public List<Booking> fetchBookingsByStatus(int status) {
        return repo.findByBookstatus(status);
    }

    public List<Booking> fetchBookingsByDriver(String driverId) {
        return repo.findByDriverid(driverId);
    }

    public Booking modifyBookingStatus(String bookingId, int newStatus) {
        return repo.findById(bookingId).map(booking -> {
            booking.setBookstatus(newStatus);
            return repo.save(booking);
        }).orElseThrow(() -> new RuntimeException("No booking found with ID: " + bookingId));
    }

    public Booking changePaymentStatus(String bookingId, int status) {
        return repo.findById(bookingId).map(booking -> {
            booking.setPaymentstatus(status);
            return repo.save(booking);
        }).orElseThrow(() -> new RuntimeException("No booking found with ID: " + bookingId));
    }

    public Booking updateFee(String bookingId, double fee) {
        return repo.findById(bookingId).map(booking -> {
            booking.setTotalfee(fee);
            return repo.save(booking);
        }).orElse(null);
    }

    public Booking assignDriverToBooking(String bookingId, String driverId) {
        return repo.findById(bookingId).map(booking -> {
            booking.setDriverid(driverId);
            return repo.save(booking);
        }).orElseThrow(() -> new RuntimeException("No booking found with ID: " + bookingId));
    }

    public Booking modifyTravelDistance(String bookingId, int distance) {
        return repo.findById(bookingId).map(booking -> {
            booking.setTravelDistance(distance);
            return repo.save(booking);
        }).orElseThrow(() -> new RuntimeException("No booking found with ID: " + bookingId));
    }

    public String removeBooking(String bookingId) {
        if (repo.existsById(bookingId)) {
            repo.deleteById(bookingId);
            return "Booking successfully removed!";
        }
        return "Booking not found!";
    }
}

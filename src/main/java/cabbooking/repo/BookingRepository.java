package cabbooking.repo;

import cabbooking.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByBookstatus(int bookstatus);

    List<Booking> findByUserid(String userid);

    List<Booking> findByDriverid(String driverid);
}

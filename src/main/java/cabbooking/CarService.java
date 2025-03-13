package cabbooking;

import cabbooking.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository repo;

    public Car saveCar(Car carInfo) {
        return repo.save(carInfo);
    }

    public List<Car> fetchAllCars() {
        return repo.findAll();
    }

    public Optional<Car> findCarById(String carId) {
        return repo.findById(carId);
    }

    public List<Car> fetchCarsByStatus(String carStatus) {
        return repo.findByStatus(carStatus);
    }

    public Car modifyCarDetails(String carId, Car updatedCar) {
        return repo.findById(carId).map(car -> {
            car.setModel(updatedCar.getModel());
            car.setLicensePlate(updatedCar.getLicensePlate());
            car.setSeats(updatedCar.getSeats());
            car.setCapacity(updatedCar.getCapacity());
            car.setStatus(updatedCar.getStatus());
            car.setPricePerKm(updatedCar.getPricePerKm());
            car.setPhoto(updatedCar.getPhoto());
            return repo.save(car);
        }).orElse(null);
    }

    public String removeCarById(String carId) {
        if (repo.existsById(carId)) {
            repo.deleteById(carId);
            return "Car successfully deleted!";
        }
        return "Car not found!";
    }
}

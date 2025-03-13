package cabbooking.controller;

import cabbooking.Car;
import cabbooking.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody Car car) {
        return carService.modifyCarDetails(id, car);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable String id) {
        return carService.removeCarById(id);
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable String id) {
        return carService.findCarById(id);
    }

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.fetchAllCars();
    }

    @GetMapping("/status/{status}")
    public List<Car> getCarsByStatus(@PathVariable String status) {
        return carService.fetchCarsByStatus(status);
    }
}
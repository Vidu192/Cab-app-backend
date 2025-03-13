package cabbooking;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car_data")
@Data
public class Car {
    @Id
    private String id;
    private String model;
    private String licensePlate;
    private int seats;
    private int capacity;
    private int status;
    private double pricePerKm;
    private String photo;
}

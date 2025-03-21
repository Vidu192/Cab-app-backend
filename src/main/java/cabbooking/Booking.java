package cabbooking;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking_data")
@Data
public class Booking {
    @Id
    private String id;
    private String userid;
    private String carid;
    private String driverid = "-1";
    private String location;
    private String time;
    private int bookstatus = 0;
    private double totalfee;
    private int paymentstatus = 0;
    private int travelDistance = 0;
}


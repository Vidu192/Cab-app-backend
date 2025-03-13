package cabbooking;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_data")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private int phonenumber;
    private String password;
    private int userrole;
    private int Status = 0;
}


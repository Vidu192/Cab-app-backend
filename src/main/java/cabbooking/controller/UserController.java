package cabbooking.controller;

import cabbooking.User;
import cabbooking.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/userlogin")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Map<String, Object> authResponse = service.loginUser(email, password);
        return authResponse.containsKey("userrole")
                ? ResponseEntity.ok(authResponse)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
    }

    @PostMapping("/userregister")
    public String createUser(@RequestBody User userInfo) {
        return service.registerUser(userInfo, false);
    }

    @PostMapping("/staffregister")
    public String createStaffUser(@RequestBody User staffDetails) {
        return service.registerUser(staffDetails, true);
    }

    @GetMapping("/all")
    public Iterable<User> fetchAllUsers() {
        return service.fetchAllUsers();
    }

    @GetMapping("/staff")
    public List<User> listStaffUsers() {
        return service.retrieveStaffUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable String id, @RequestBody User updatedUserData) {
        Optional<User> existingUser = service.getUserById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        User user = existingUser.get();
        user.setUsername(updatedUserData.getUsername());
        user.setEmail(updatedUserData.getEmail());
        user.setPhonenumber(updatedUserData.getPhonenumber());
        user.setPassword(updatedUserData.getPassword());
        service.storeOrUpdateUser(user);
        return ResponseEntity.ok("User updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeUser(@PathVariable String id) {
        return service.deleteUserById(id)
                ? ResponseEntity.ok("User deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
}






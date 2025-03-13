package cabbooking;

import cabbooking.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Map<String, Object> loginUser(String email, String password) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent() && Objects.equals(user.get().getPassword(), password)) {
            return new HashMap<>() {{
                put("message", "Login successful!");
                put("userrole", user.get().getUserrole());
                put("userid", user.get().getId());
            }};
        }
        return new HashMap<>() {{
            put("message", "Invalid email or password!");
        }};
    }

    public String registerUser(User userInfo, boolean isAdmin) {
        if (repo.findByEmail(userInfo.getEmail()).isPresent()) {
            return "Email already exists!";
        }
        userInfo.setUserrole(isAdmin ? 1 : 2);
        repo.save(userInfo);
        return "User successfully registered!";
    }

    public Iterable<User> fetchAllUsers() {
        return repo.findAll();
    }

    public List<User> retrieveStaffUsers() {
        return repo.findByUserrole(1);
    }

    public Optional<User> getUserById(String userId) {
        return repo.findById(userId);
    }

    public void storeOrUpdateUser(User user) {
        repo.save(user);
    }

    public boolean deleteUserById(String userId) {
        return repo.findById(userId).map(user -> {
            repo.deleteById(userId);
            return true;
        }).orElse(false);
    }
}


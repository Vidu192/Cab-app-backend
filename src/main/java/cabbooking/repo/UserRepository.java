package cabbooking.repo;

import cabbooking.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByUserrole(int userrole);

    Optional<User> findByEmail(String email);
}

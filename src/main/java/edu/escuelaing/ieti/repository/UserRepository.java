package edu.escuelaing.ieti.repository;

import edu.escuelaing.ieti.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

package edu.escuelaing.ieti.repository;

import edu.escuelaing.ieti.repository.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    
}

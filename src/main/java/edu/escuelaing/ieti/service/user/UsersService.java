package edu.escuelaing.ieti.service.user;

import java.util.List;
import java.util.Optional;

import edu.escuelaing.ieti.repository.user.User;

public interface UsersService {

    User save(User user);

    Optional<User> findById(String id);

    List<User> all();

    void deleteById(String id);

    User update(User user, String userId);
}
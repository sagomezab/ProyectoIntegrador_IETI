package edu.escuelaing.ieti.service.user;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.repository.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> all() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public User update(User user, String userId) {
        return null;
    }
}

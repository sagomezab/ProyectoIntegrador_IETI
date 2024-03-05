package edu.escuelaing.ieti.service.user;

import java.util.List;

import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;

public interface UsersService {

    User save(UserDto userDto);

    User findById(String id);

    List<User> all();

    boolean deleteById(String id);

    User update(UserDto userDto, String userId);
}
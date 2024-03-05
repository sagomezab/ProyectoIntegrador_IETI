package edu.escuelaing.ieti.service.user;

import java.util.List;

import edu.escuelaing.ieti.data.User;
import edu.escuelaing.ieti.dto.UserDto;

public interface UsersService {

    User save(UserDto userDto);

    User findById(String id);

    List<User> all();

    boolean deleteById(String id);

    User update(UserDto userDto, String userId);

    User findByEmail(String email);
}
package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;
import edu.escuelaing.ieti.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import java.util.List;

@Service
public class UserServiceMongoDB implements UsersService{
    private final UserRepository userRepository;
    public UserServiceMongoDB( @Autowired UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        User createdUser = userRepository.save(new User(userDto));
        return createdUser;
    }

    @Override
    public User findById(String id) {
        Optional<User> optionalUser = userRepository.findById( id );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            return  null;

        }
    }

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        if ( userRepository.existsById( id ) )
        {
            userRepository.deleteById( id );
            return true;
        }
        return false;
    }

    @Override
    public User update(UserDto userDto, String userId) {
        if ( userRepository.findById(userId).isPresent() )
        {
            User user = userRepository.findById(userId).get();
            user.update(userDto);
            userRepository.save( user );
            return user;
        }
        return null;
    }

}

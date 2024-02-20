package edu.escuelaing.ieti.controller.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;
import edu.escuelaing.ieti.service.user.UsersService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        User userCreated = usersService.save(new User(user));
        URI createdUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
        return ResponseEntity.created(createdUserUri).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        User findUser = usersService.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return ResponseEntity.ok(findUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,@RequestBody UserDto userDto) {
        User userUpdate = usersService.findById(id).orElseThrow(()->new UserNotFoundException(id));
        User userUpdated = usersService.update(new User(userDto), id);
        usersService.save(userUpdate);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable ("id") String id) {
        usersService.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

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
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService userService;

    public UsersController(@Autowired UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> all()
    {
        return ResponseEntity.ok(userService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserDto userDto, @PathVariable String id)
    {
        return ResponseEntity.ok(userService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable String id)
    {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}

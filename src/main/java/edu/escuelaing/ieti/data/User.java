package edu.escuelaing.ieti.data;

import edu.escuelaing.ieti.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
public class User {

    @Id
    private String id;
    private Date createdAt;
    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<RoleEnum> roles;


    public User(UserDto userDto) {
        this.id = UUID.randomUUID().toString();
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.createdAt = new Date();
        this.password = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
    }

    public User() {
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPasswordHash() {
        return password;
    }

    public void setPasswordHash(String passwordHash) {
        this.password = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        if (!userDto.getPassword().isEmpty()) {
            this.password = userDto.getPassword();
        }
    }
}

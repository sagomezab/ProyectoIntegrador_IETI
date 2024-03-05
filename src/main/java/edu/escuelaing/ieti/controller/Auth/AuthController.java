package edu.escuelaing.ieti.controller.Auth;

import edu.escuelaing.ieti.data.User;
import edu.escuelaing.ieti.dto.LoginDto;
import edu.escuelaing.ieti.dto.TokenDto;
import edu.escuelaing.ieti.exception.InvalidCredentialsException;
import edu.escuelaing.ieti.service.user.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

import static edu.escuelaing.ieti.data.Constants.CLAIMS_ROLES_KEY;
import static edu.escuelaing.ieti.data.Constants.TOKEN_DURATION_MINUTES;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Value("${app.secret}")
    String secret;

    @Autowired
    private UsersService userService;

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto){
        User user = userService.findByEmail(loginDto.email);
        if (BCrypt.checkpw(loginDto.password, user.getPasswordHash())){
            return generateTokenDto(user);
        }else {
            throw new InvalidCredentialsException();
        }
    }

    private String generateToken(User user, Date expirationDate){
        return Jwts.builder()
                .setSubject(user.getId())
                .claim(CLAIMS_ROLES_KEY, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private TokenDto generateTokenDto(User user){
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES);
        String token = generateToken(user, expirationDate.getTime());
        return new TokenDto(token, expirationDate.getTime());
    }

}

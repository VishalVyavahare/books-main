package com.example.BooksMgmt.restservice.common.security.controller;

import com.example.BooksMgmt.restservice.common.security.model.User;
import com.example.BooksMgmt.restservice.common.security.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Vishal Vyavahare
 */
@RestController
@RequestMapping(value = {"/client", "/user", "/customer"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private UriComponentsBuilder self() {
        return MvcUriComponentsBuilder.fromController(getClass());
    }

    @ApiOperation(value = "Generated an authentication token for user")
    @PostMapping("token")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws Exception {
        if (StringUtils.isEmpty(userService.findByUserNameAndPassword(username, pwd))) {
            throw new Exception("Username and password are incorrect");
        }
        String token = getJWTToken(username);
        User user = new User();
        user.setUser(username);
        user.setToken(token);
        return user;
    }


    @ApiOperation(value = "Create a user which will be used for authentication")
    @PostMapping("register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User userCreated = userService.createUser(user);
        UriComponents uri =
            self().path("/{id}").buildAndExpand(userCreated.getUser());
        return ResponseEntity.created(uri.toUri()).body(userCreated);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(username)
            .claim("authorities",
                grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
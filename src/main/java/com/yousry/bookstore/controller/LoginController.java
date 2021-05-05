package com.yousry.bookstore.controller;

import com.yousry.bookstore.dal.domainobject.User;
import com.yousry.bookstore.security.JwtUtil;
import com.yousry.bookstore.security.UserUnauthorizedException;
import com.yousry.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.validation.Valid;

/**
 * JWT Login Class
 * @author : Yousry
 * @version : 1.0
 */
@RestController
@RequestMapping("/v1/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping
    @Operation(summary = "Used to do Login, without login you will not be able to access other services")
    public String login(@Valid @RequestParam(required = true) String name,
                        @RequestParam(required = true) String password) throws ServletException, EntityNotFoundException {
        User user = userService.getUser(name);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
                return jwtUtil.generateToken(name);
            } else {
                throw new UserUnauthorizedException("Wrong Name or Password");
            }
        } else {
            throw new UserUnauthorizedException("User does not exist.");
        }
    }
}

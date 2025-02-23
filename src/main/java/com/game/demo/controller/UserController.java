package com.game.demo.controller;

import com.game.demo.DTO.AuthRequestDTO;
import com.game.demo.DTO.UserRequestDTO;
import com.game.demo.entity.AppUser;
import com.game.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/user")
    public ResponseEntity<AppUser> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){

        AppUser user = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequestDTO){

        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                );

        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        return ResponseEntity.ok(authenticationResponse.getName());
    }


}

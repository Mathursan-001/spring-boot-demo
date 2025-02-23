package com.game.demo.service;

import com.game.demo.DTO.UserRequestDTO;
import com.game.demo.entity.AppUser;
import com.game.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AppUser createUser(UserRequestDTO userRequestDTO){

        return userRepository.save(
                AppUser
                        .builder()
                        .userName(userRequestDTO.getUserName())
                        .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                        .wins(0)
                        .loses(0)
                        .build()
        );

    }

}

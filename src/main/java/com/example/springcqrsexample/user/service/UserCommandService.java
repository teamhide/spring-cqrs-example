package com.example.springcqrsexample.user.service;

import com.example.springcqrsexample.user.command.RegisterUserCommand;
import com.example.springcqrsexample.user.domain.User;
import com.example.springcqrsexample.user.exception.DifferentPasswordException;
import com.example.springcqrsexample.user.exception.DuplicateEmailException;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;

    public void register(RegisterUserCommand command) {
        if (!command.getPassword1().equals(command.getPassword2())) {
            throw new DifferentPasswordException();
        }

        if (userRepository.existsByEmail(command.getEmail())) {
            throw new DuplicateEmailException();
        }

        User user = User.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .password(command.getPassword1())
                .build();
        userRepository.save(user);
    }
}

package com.e.commerce.shop.service;

import com.e.commerce.shop.domain.User;
import com.e.commerce.shop.repository.UserRepository;
import com.e.commerce.shop.security.PasswordProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final PasswordProcessor passwordProcessor;
    private final UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User getIfCorrect(User user) {
        encode(user);

        return userRepository.findByUsername(user.getUsername())
                .filter(user::credentialsEqual)
                .orElse(null);
    }

    public void encode(User user) {
        user.setPassword(passwordProcessor.encodePassword(user.getPassword()));
    }

    public void decode(User user) {
        user.setPassword(passwordProcessor.decodePassword(user.getPassword()));
    }

}

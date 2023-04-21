package com.e.commerce.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PasswordProcessor {

    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    public String encodePassword(String rawPassword) {

        return Optional.ofNullable(rawPassword)
                .map(String::getBytes)
                .map(encoder::encode)
                .map(String::new)
                .orElse(null);
    }

    public String decodePassword(String encodedPassword) {

        return Optional.ofNullable(encodedPassword)
                .map(String::getBytes)
                .map(decoder::decode)
                .map(String::new)
                .orElse(null);
    }
}

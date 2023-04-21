package com.e.commerce.shop.service;

import com.e.commerce.shop.domain.Session;
import com.e.commerce.shop.domain.User;
import com.e.commerce.shop.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session createSessionForUser(User user) {
        return sessionRepository.createForUser(user);
    }


}

package com.e.commerce.shop.service;

import com.e.commerce.shop.domain.Session;
import com.e.commerce.shop.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthService {

    private final SessionService sessionService;
    private final UserService userService;

    public void authenticate(User loginRequester, HttpServletResponse httpServletResponse) {
        User foundUser = userService.getIfCorrect(loginRequester);

        if (Objects.nonNull(foundUser)) {
            Session session = sessionService.createSessionForUser(foundUser);

            httpServletResponse.addHeader("sessionKey", session.getSessionKey());
        }
    }
}

package com.e.commerce.shop.controller;

import com.e.commerce.shop.domain.User;
import com.e.commerce.shop.dto.CredentialsDTO;
import com.e.commerce.shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login/")
    public void login(@RequestBody CredentialsDTO credentials, HttpServletResponse httpServletResponse) {
        User user = new User(credentials);

        authService.authenticate(user, httpServletResponse);
    }

    @GetMapping("/logout/")
    public void logout() {

    }
}

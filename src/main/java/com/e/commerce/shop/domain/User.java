package com.e.commerce.shop.domain;

import com.e.commerce.shop.dto.CredentialsDTO;
import com.e.commerce.shop.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private String username;
    private String password;

    public User(UserDTO user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }

    public User(CredentialsDTO credentials) {
        this.username = credentials.getUsername();
        this.password = credentials.getPassword();
    }

    public boolean credentialsEqual(User other) {
        return username.equals(other.getUsername()) && password.equals(other.getPassword());
    }
}

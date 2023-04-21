package com.e.commerce.shop.dto;

import com.e.commerce.shop.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String username;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }
}

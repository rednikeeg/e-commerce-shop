package com.e.commerce.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private Long sessionId;
    private Long userId;
    private String sessionKey;
}

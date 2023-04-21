package com.e.commerce.shop.repository;

import com.e.commerce.shop.domain.User;
import com.e.commerce.shop.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public Optional<User> findById(Long id) {

        try {
            User user = jdbcTemplate.queryForObject(
                    "SELECT userId, username, password FROM Users WHERE userId = ?",
                    userRowMapper,
                    id
            );

            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByUsername(String username) {

        try {
            User user = jdbcTemplate.queryForObject(
                    "SELECT userId, username, password FROM Users WHERE username = ?",
                    userRowMapper,
                    username
            );

            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

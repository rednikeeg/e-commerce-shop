package com.e.commerce.shop.repository;

import com.e.commerce.shop.domain.Session;
import com.e.commerce.shop.domain.User;
import com.e.commerce.shop.repository.rowmapper.SessionRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionRepository {

    private final SessionRowMapper sessionRowMapper;
    private final JdbcTemplate jdbcTemplate;


    private final SessionKeyGenerator sessionKeyGenerator;
//    private final SessionRowMapper sessionRowMapper;

    // TODO: Move generation logic to service!!!
    @Transactional
    public Session createForUser(User user) {
        String sessionKey = generateUniqueKey();

        jdbcTemplate.update(
                "INSERT INTO Sessions(userId, sessionKey) values(?, ?)",
                user.getUserId(),
                sessionKey
        );

        return getBySessionKey(sessionKey);
    }

    public Session getBySessionKey(String sessionKey) {

        return jdbcTemplate.queryForObject(
                "SELECT sessionId, userId, sessionKey FROM Sessions WHERE sessionKey = ?",
                sessionRowMapper,
                sessionKey
        );
    }

    private String generateUniqueKey() {
        String key;

        do {
            key = sessionKeyGenerator.generate();
        } while (isNotUnique(key));

        return key;
    }

    private boolean isNotUnique(String sessionKey) {

        return jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT * FROM sessions where sessionKey = ?) isNotUnique",
                Boolean.class,
                sessionKey
        );
    }

//    public Session getByUserId(String userId) {
//
//        return jdbcTemplate.queryForObject(
//                "SELECT sessionId, userId FROM sessions WHERE userId = ?",
//                sessionRowMapper,
//                userId
//        );
//    }
//
//    public void deleteById(Long sessionId) {
//
//        jdbcTemplate.update(
//                "DELETE FROM Sessions WHERE sessionId = ?",
//                sessionId
//        );
//    }
//
//    public Session getById(String sessionId) {
//
//        return jdbcTemplate.queryForObject(
//                "SELECT sessionId, userId FROM sessions WHERE sessionId = ?",
//                sessionRowMapper,
//                sessionId
//        );
//    }


    @Component
    private static class SessionKeyGenerator {

        public static final int ORIGIN = 'A';
        public static final int BOUND = 'Z' + 1;


        public String generate() {
            SecureRandom random = new SecureRandom();

            return random.ints(ORIGIN, BOUND)
                    .limit(20)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
    }
}

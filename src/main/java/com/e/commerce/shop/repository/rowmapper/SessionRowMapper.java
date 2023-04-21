package com.e.commerce.shop.repository.rowmapper;

import com.e.commerce.shop.domain.Session;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SessionRowMapper implements RowMapper<Session> {

    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        Session session = new Session();

        session.setSessionId(rs.getLong("sessionId"));
        session.setUserId(rs.getLong("userId"));
        session.setSessionKey(rs.getString("sessionKey"));

        return session;
    }
}

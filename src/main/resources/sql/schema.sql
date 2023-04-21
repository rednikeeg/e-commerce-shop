DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS users;

create table users
(
    userId   bigserial   not null
        primary key,
    username varchar(50) not null unique,
    password varchar(50) not null
);

create table sessions
(
    sessionId  bigserial   not null
        primary key,
    userId     bigint      not null
        references users,
    sessionKey varchar(50) not null unique
);

DO
$$
    BEGIN
        if not exists (select * from pg_user where usename = 'admin') then
            CREATE USER admin password 'admin';
        end if;
    END;
$$;

GRANT pg_read_all_data TO admin;
GRANT pg_write_all_data TO admin;
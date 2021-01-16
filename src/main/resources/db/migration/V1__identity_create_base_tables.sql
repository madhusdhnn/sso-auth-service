CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       TEXT UNIQUE              NOT NULL,
    org_id        BIGINT                   NOT NULL,
    role_id       BIGINT                   NOT NULL,
    password      TEXT                     NOT NULL,
    mobile_number TEXT                     NOT NULL,
    email_id      TEXT                     NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_usr_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

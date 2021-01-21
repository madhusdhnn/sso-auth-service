CREATE TABLE roles
(
    id      BIGSERIAL PRIMARY KEY,
    role_id TEXT UNIQUE,
    name    TEXT NOT NULL
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    company_id    TEXT                     NOT NULL,
    emp_id        TEXT UNIQUE              NOT NULL,
    org_id        TEXT                     NOT NULL,
    role_id       TEXT                     NOT NULL,
    user_name     TEXT                     NOT NULL,
    password      TEXT                     NOT NULL,
    email_id      TEXT                     NOT NULL,
    mobile_number TEXT                     NOT NULL,
    enabled       BOOLEAN                  NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_usr_role FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE TABLE user_role
(
    emp_id  TEXT NOT NULL,
    role_id TEXT NOT NULL,
    PRIMARY KEY (emp_id, role_id),
    CONSTRAINT fk_ur_user_id FOREIGN KEY (emp_id) REFERENCES users (emp_id),
    CONSTRAINT fk_ur_role_id FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE
INDEX IF NOT EXISTS idx_fk_ur_user_id ON user_role (emp_id);

CREATE
INDEX IF NOT EXISTS idx_fk_ur_role_id ON user_role (role_id);
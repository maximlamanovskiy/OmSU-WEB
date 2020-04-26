CREATE TABLE IF NOT EXISTS USERS
(
    id       VARCHAR(36) PRIMARY KEY,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(60) NOT NULL
);
CREATE TABLE IF NOT EXISTS ROLE
(
    id   VARCHAR(36) PRIMARY KEY,
    role VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS ROLE_FOR_USER
(
    id   VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL REFERENCES USERS(id) ON DELETE CASCADE,
    role_id VARCHAR(36) NOT NULL REFERENCES ROLE(id) ON DELETE CASCADE
)
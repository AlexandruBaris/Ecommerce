CREATE TABLE users
(
    user_id           INT GENERATED      ALWAYS AS IDENTITY,
    first_name        VARCHAR(22)        NOT NULL,
    last_name         VARCHAR(22)        NOT NULL,
    username          VARCHAR(50) UNIQUE NOT NULL,
    password          VARCHAR(255)       NOT NULL,
    enabled           BOOLEAN            NOT NULL,
    verification_code VARCHAR(64),
    CONSTRAINT users_PK PRIMARY KEY (user_id)
);
CREATE TABLE roles
(
    role_id        INT GENERATED      ALWAYS AS IDENTITY,
    role_name      VARCHAR(22)        NOT NULL,
    CONSTRAINT role_PK PRIMARY KEY (role_id)
);
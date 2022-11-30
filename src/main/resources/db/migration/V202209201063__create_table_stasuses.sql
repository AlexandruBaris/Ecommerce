CREATE TABLE statuses
(
    status_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(20) NOT NULL,
    CONSTRAINT status_PK PRIMARY KEY (status_id)
);
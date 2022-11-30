CREATE TABLE orders
(
    order_id INT GENERATED ALWAYS AS IDENTITY,
    order_amount NUMERIC(10, 2) NOT NULL,
    date           DATE           NOT NULL,
    user_id INT NOT NULL,
    status_id INT NOT NULL,
    CONSTRAINT order_PK PRIMARY KEY (order_id),
    CONSTRAINT user_FK FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT status_FK FOREIGN KEY (status_id) REFERENCES statuses (status_id)
);
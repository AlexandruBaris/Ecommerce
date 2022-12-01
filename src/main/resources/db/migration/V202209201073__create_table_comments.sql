CREATE TABLE comments
(
    comment_id  INT GENERATED      ALWAYS AS IDENTITY,
    date           DATE           NOT NULL,
    text VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    CONSTRAINT comment_PK PRIMARY KEY (comment_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON UPDATE CASCADE,
    FOREIGN KEY(product_id) REFERENCES products(product_id) ON UPDATE CASCADE
)
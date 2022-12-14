CREATE TABLE orders_products
(
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id) ON UPDATE CASCADE,
    FOREIGN KEY(order_id) REFERENCES orders(order_id) ON UPDATE CASCADE
)
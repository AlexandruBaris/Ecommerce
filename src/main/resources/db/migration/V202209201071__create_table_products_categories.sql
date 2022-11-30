CREATE TABLE products_categories(
    product_id     INT NOT NULL,
    category_id    INT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id) ON UPDATE CASCADE,
    FOREIGN KEY(category_id) REFERENCES categories(category_id) ON UPDATE CASCADE
);
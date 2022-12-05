CREATE TABLE products
(
    product_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    available_quantity SMALLINT ,
    description VARCHAR(255),
    price NUMERIC(10, 2) NOT NULL,
    img VARCHAR(255),
    CONSTRAINT product_PK PRIMARY KEY (product_id)
);
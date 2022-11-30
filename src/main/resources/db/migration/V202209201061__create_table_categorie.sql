CREATE TABLE categories
(
    category_id INT GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(50)   NOT NULL,
    CONSTRAINT categorie_PK PRIMARY KEY (category_id)
)
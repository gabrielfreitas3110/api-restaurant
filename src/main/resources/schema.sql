DROP TABLE IF EXISTS tb_category;

CREATE TABLE tb_category (
    id   BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS tb_product;

CREATE TABLE tb_product (
    id   BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS tb_product_category;

CREATE TABLE tb_product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);

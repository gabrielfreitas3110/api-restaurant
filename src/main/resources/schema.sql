DROP TABLE IF EXISTS tb_category;

CREATE TABLE tb_category (
    id   BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_product;

CREATE TABLE tb_product (
    id   BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_product_category;

CREATE TABLE tb_product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS tb_state;

CREATE TABLE tb_state (
    id   BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_city;

CREATE TABLE tb_city (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    state_id BIGINT,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS tb_client;

CREATE TABLE tb_client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    cpf_or_cnpj VARCHAR(255) UNIQUE,
    type INTEGER,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_address;

CREATE TABLE tb_address (
    id BIGINT NOT NULL AUTO_INCREMENT,
    street VARCHAR(255),
    number INTEGER,
    cep VARCHAR(255),
    client_id BIGINT,
    city_id BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_cellphone;

CREATE TABLE tb_cellphone (
    client_id BIGINT NOT NULL,
    cellphone VARCHAR(255)
);
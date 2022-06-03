DROP TABLE IF EXISTS tb_category;
DROP TABLE IF EXISTS tb_product;
DROP TABLE IF EXISTS tb_product_category;
DROP TABLE IF EXISTS tb_state;
DROP TABLE IF EXISTS tb_city;
DROP TABLE IF EXISTS tb_client;
DROP TABLE IF EXISTS tb_address;
DROP TABLE IF EXISTS tb_cellphone;
DROP TABLE IF EXISTS tb_credit_card_payment;
DROP TABLE IF EXISTS tb_slip_payment;
DROP TABLE IF EXISTS tb_payment;
DROP TABLE IF EXISTS tb_order;
DROP TABLE IF EXISTS tb_order_item;

CREATE TABLE tb_category (
    id BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tb_product (
    id BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE tb_product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE tb_state (
    id BiGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tb_city (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    state_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE tb_client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    cpf_cnpj VARCHAR(255) UNIQUE,
    type INTEGER,
    deleted BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE tb_address (
    id BIGINT NOT NULL AUTO_INCREMENT,
    street VARCHAR(255),
    number INTEGER,
    cep VARCHAR(255),
    client_id BIGINT,
    city_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE tb_cellphone (
    client_id BIGINT NOT NULL,
    cellphone VARCHAR(255)
);

CREATE TABLE tb_credit_card_payment (
    number_of_installemnts INTEGER ,
    order_id BIGINT NOT NULL,
    PRIMARY KEY (order_id)
);

CREATE TABLE tb_slip_payment (
    due_date TIMESTAMP,
    pay_date TIMESTAMP,
    order_id BIGINT NOT NULL,
    PRIMARY KEY (order_id)
);

CREATE TABLE tb_payment(
    order_id BIGINT NOT NULL,
    payment_status INTEGER,
    PRIMARY KEY (order_id)
);

CREATE TABLE tb_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    instant TIMESTAMP,
    client_id BIGINT,
    delivery_address_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE tb_order_item (
    discount DOUBLE PRECISION,
    quantity INTEGER,
    price DOUBLE,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL
);

ALTER TABLE tb_product_category ADD CONSTRAINT FK_product_category_product_id FOREIGN KEY("PRODUCT_ID") REFERENCES tb_product("ID");
ALTER TABLE tb_product_category ADD CONSTRAINT FK_product_category_category_id FOREIGN KEY("CATEGORY_ID") REFERENCES tb_category("ID");
ALTER TABLE tb_city ADD CONSTRAINT FK_city_state_id FOREIGN KEY("STATE_ID") REFERENCES TB_STATE("ID");
ALTER TABLE tb_address ADD CONSTRAINT FK_address_city_id FOREIGN KEY("CITY_ID") REFERENCES tb_city("ID");
ALTER TABLE tb_address ADD CONSTRAINT FK_address_client_id FOREIGN KEY("CLIENT_ID") REFERENCES tb_client("ID");
ALTER TABLE tb_cellphone ADD CONSTRAINT FK_cellphone_client_id FOREIGN KEY("CLIENT_ID") REFERENCES tb_client("ID");
ALTER TABLE tb_credit_card_payment ADD CONSTRAINT FK_credit_card_payment_order_id FOREIGN KEY("ORDER_ID") REFERENCES tb_payment("ORDER_ID");
ALTER TABLE tb_slip_payment ADD CONSTRAINT FK_slip_payment_order_id FOREIGN KEY("ORDER_ID") REFERENCES tb_payment("ORDER_ID");
ALTER TABLE tb_payment ADD CONSTRAINT FK_payment_order_id FOREIGN KEY("ORDER_ID") REFERENCES tb_order("ID");
ALTER TABLE tb_order ADD CONSTRAINT FK_order_delivery_address_id FOREIGN KEY("DELIVERY_ADDRESS_ID") REFERENCES tb_address("ID");
ALTER TABLE tb_order ADD CONSTRAINT FK_order_client_id FOREIGN KEY("CLIENT_ID") REFERENCES tb_client("ID");
ALTER TABLE tb_order_item ADD CONSTRAINT PK_order_item_id PRIMARY KEY("ORDER_ID","PRODUCT_ID");
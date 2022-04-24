INSERT INTO tb_category (name) VALUES ('Food');
INSERT INTO tb_category (name) VALUES ('Drink');

INSERT INTO tb_product (name, price) VALUES ('Soda', 4.99);
INSERT INTO tb_product (name, price) VALUES ('Coke', 5.19);
INSERT INTO tb_product (name, price) VALUES ('Pancake', 1.99);
INSERT INTO tb_product (name, price) VALUES ('Chicken Nuggets', 3.00);

INSERT INTO tb_product_category(product_id, category_id) VALUES (1, 2), (2,2), (3,1), (4,1);
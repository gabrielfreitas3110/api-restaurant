INSERT INTO tb_category (name) VALUES ('Food'), ('Drink');

INSERT INTO tb_product (name, price) VALUES
('Soda', 4.99),  ('Coke', 5.19),  ('Pancake', 1.99), ('Chicken Nuggets', 3.00);

INSERT INTO tb_product_category(product_id, category_id) VALUES (1, 2), (2,2), (3,1), (4,1);

INSERT INTO tb_state (name) VALUES ('Goias'), ('Minas Gerais');
INSERT INTO tb_city (name, state_id) VALUES ('Itumbiara', 1), ('Goiania', 1), ('Uberlandia', 2);

INSERT INTO tb_client(name, cpf_or_cnpj, type) VALUES ('Gabriel','012.345.678-99', 1);
INSERT INTO tb_client(name, cpf_or_cnpj, type) VALUES ('Augusto','60.421.100/0001-13', 2);

INSERT INTO tb_address(street, number, cep, client_id, city_id) VALUES
('Rua dos bobos', 0, '40028922',1,1), ('Rua 21', 555, '77555520',2,2);

INSERT INTO tb_cellphone (client_id, cellphone) VALUES
(1, '64996662498'), (1, '6434314231'), (2, '64996662498'), (2, '6434314231');
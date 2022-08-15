INSERT INTO tb_category (name) VALUES ('Food'), ('Drink');

INSERT INTO tb_product (name, price) VALUES
('Soda', 4.99),  ('Coke', 5.19),  ('Pancake', 1.99), ('Chicken Nuggets', 3.00);

INSERT INTO tb_product_category(product_id, category_id) VALUES (1, 2), (2,2), (3,1), (4,1);

INSERT INTO tb_state (name) VALUES ('Goias'), ('Minas Gerais');
INSERT INTO tb_city (name, state_id) VALUES ('Itumbiara', 1), ('Goiania', 1), ('Uberlandia', 2);

INSERT INTO tb_client(name, email, cpf_cnpj, type, deleted) VALUES
('Gabriel','gabriel.freitas3110@gmail.com', '01234567899', 1, 0), ('Augusto','xqgabriel1@gmail..com', '60421100000113', 2, 0);

INSERT INTO tb_address(street, number, cep, client_id, city_id) VALUES
('Rua dos bobos', 0, '40028922',1,1), ('Rua 0', 123, '75395199',1,2), ('Rua 21', 555, '77555520',2,2);

INSERT INTO tb_cellphone (cellphone, client_id) VALUES
('64996662498', 1), ('6434314231', 1), ('64996662498', 2), ('6434314231', 2);

INSERT INTO tb_order(instant, client_id, delivery_address_id) VALUES
('2022-05-28 16:30:00.0', 1, 1), ('2022-05-28 16:35:00.0', 1, 2);

INSERT INTO tb_payment(payment_status, order_id) VALUES
(1,1), (2, 2);
INSERT INTO tb_credit_card_payment(number_of_installemnts, order_id) VALUES (6, 1);
INSERT INTO tb_slip_payment(due_date, pay_date, order_id) VALUES ('2022-05-28 16:38:00.0', '2022-06-28 16:35:00.0', 2);

INSERT INTO tb_order_item(discount, quantity, price, order_id, product_id) VALUES
(0.0, 3, 4.99, 1, 1), (0.0, 2, 3.00, 1, 3);

INSERT INTO tb_category (name) VALUES
('Computing'), ('Office'), ('Household Linen'), ('Eletronics'), ('Gardening'), ('Decoration'), ('Perfumery') ;

INSERT INTO tb_product (name, price) VALUES
('Computer', 2000.00),  ('Printer', 800.00),  ('Mouse', 80.00), ('Office Desk', 300.00),
('Towel', 50.00),  ('Bedspread', 200.00),  ('TV True Color', 1200.00), ('Brushcutter', 800.00),
('Bedside Lamp', 100.00),  ('Pendant', 180.00),  ('Shampoo', 90.00);

INSERT INTO tb_product_category(product_id, category_id) VALUES
(5, 3), (6, 3), (7, 3),
(6, 4), (8, 4),
(9, 5), (10, 5),
(5, 6), (6, 6), (7, 6), (11, 6),
(12, 7),
(13, 8), (14, 8),
(15, 9);
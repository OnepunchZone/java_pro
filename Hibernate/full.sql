BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('Реактивный ранец', 300000),
('Клюшка', 1200),
('Плюшка', 90),
('Сабля', 10000),
('Молоко', 100),
('Колобок', 500000);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Саня'),
('Ваня'),
('Таня');

DROP TABLE IF EXISTS customer_products CASCADE;
CREATE TABLE customer_products (
    customer_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (customer_id, product_id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
INSERT INTO customer_products (customer_id, product_id) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 6),
(2, 2),
(2, 4),
(2, 5),
(3, 3),
(3, 5);

COMMIT;
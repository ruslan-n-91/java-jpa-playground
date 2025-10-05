CREATE DATABASE java_jpa_playground_db;

DROP TABLE IF EXISTS orders CASCADE;

CREATE SEQUENCE IF NOT EXISTS orders_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS orders
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('orders_id_seq'),
    order_number VARCHAR(255) NOT NULL,
    status       VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP    NOT NULL
    );

ALTER SEQUENCE orders_id_seq OWNED BY orders.id;

-- INSERT INTO Book (title, quantity)
-- VALUES ('Java The Complete Reference 9th Edition', 40),
--        ('Design Patterns: Elements of Reusable Object-Oriented Software', 30),
--        ('Grokking Algorithms', 25),
--        ('War and Peace', 30),
--        ('Adventures of Huckleberry Finn', 50),
--        ('Dune', 45);


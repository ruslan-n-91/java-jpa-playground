--liquibase formatted sql
--changeset Ruslan Nurnazarov:create_orders_items_clients_documents_tables

--rollback DROP TABLE orders_items; DROP TABLE orders; DROP TABLE items;
--rollback DROP TABLE clients; DROP TABLE documents;
--rollback DROP SEQUENCE orders_id_seq; DROP SEQUENCE items_id_seq;
--rollback DROP SEQUENCE clients_id_seq; DROP SEQUENCE documents_id_seq;

CREATE SEQUENCE IF NOT EXISTS orders_id_seq INCREMENT BY 50 START WITH 100;
CREATE SEQUENCE IF NOT EXISTS items_id_seq INCREMENT BY 50 START WITH 100;
CREATE SEQUENCE IF NOT EXISTS clients_id_seq INCREMENT BY 50 START WITH 100;
CREATE SEQUENCE IF NOT EXISTS documents_id_seq INCREMENT BY 50 START WITH 100;

CREATE TABLE IF NOT EXISTS clients
(
    id              BIGINT DEFAULT nextval('clients_id_seq') PRIMARY KEY,
    first_name      VARCHAR(255),
    second_name     VARCHAR(255),
    passport_number VARCHAR(255),
    birth_date      DATE
);

CREATE TABLE IF NOT EXISTS documents
(
    id              BIGINT DEFAULT nextval('documents_id_seq') PRIMARY KEY,
    document_number VARCHAR(255),
    document_type   VARCHAR(255),
    created_at      TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders
(
    id           BIGINT DEFAULT nextval('orders_id_seq') PRIMARY KEY,
    order_number VARCHAR(255),
    status       VARCHAR(255),
    created_at   TIMESTAMP,
    client_id    BIGINT,
    document_id  BIGINT,
    CONSTRAINT fk_clients FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE SET NULL,
    CONSTRAINT fk_documents FOREIGN KEY (document_id) REFERENCES documents (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS items
(
    id          BIGINT DEFAULT nextval('items_id_seq') PRIMARY KEY,
    item_number VARCHAR(255),
    name        VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders_items
(
    order_id BIGINT NOT NULL,
    item_id  BIGINT NOT NULL,
    PRIMARY KEY (order_id, item_id),
    CONSTRAINT fk_orders FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    CONSTRAINT fk_items FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);




--liquibase formatted sql
--changeset Ruslan Nurnazarov:insert_data_into_orders_items_clients_documents_tables

--rollback DELETE FROM orders_items; DELETE FROM orders_workers;
--rollback DELETE FROM orders; DELETE FROM items; DELETE FROM workers;
--rollback DELETE FROM clients; DELETE FROM documents; DELETE FROM special_documents;

INSERT INTO clients (id, first_name, second_name, passport_number, birth_date)
VALUES (1, 'John', 'Smith', '5556667777', '1991-05-07'),
       (2, 'Bob', 'Johnson', '1112223333', '1985-11-25'),
       (3, 'Tom', '123', '3332223333', '1981-10-11'),
       (4, 'Mike', '456', '4442223333', '1982-11-22'),
       (5, 'David', '789', '5552223333', '1983-12-25');

INSERT INTO documents (id, document_number, document_type, created_at)
VALUES (1, '2200001', 'DOCUMENT', '2025-10-04 17:57:00'),
       (2, '2200002', 'DOCUMENT', '2025-10-04 17:57:00'),
       (3, '2200003', 'DOCUMENT', '2025-10-04 17:57:00'),
       (4, '2200004', 'DOCUMENT', '2025-10-04 17:57:00'),
       (5, '2200005', 'DOCUMENT', '2025-10-04 17:57:00');

INSERT INTO orders (id, order_number, status, created_at, client_id, document_id)
VALUES (1, 'ORD-100001', 'PROCESSING', '2025-10-04 16:47:00', 1, 1),
       (2, 'ORD-100002', 'PROCESSING', '2025-10-04 16:47:00', 2, 2),
       (3, 'ORD-100003', 'PROCESSING', '2025-10-04 16:47:00', 3, 3),
       (4, 'ORD-100004', 'PROCESSING', '2025-10-04 16:47:00', 4, 4),
       (5, 'ORD-100005', 'PROCESSING', '2025-10-04 16:47:00', 5, 5);
--(nextval('orders_id_seq'), 'ORD-100005', 'PROCESSING', '2025-10-04 16:47:00');

INSERT INTO items (id, item_number, name, description)
VALUES (1, 'ITEM-100001', 'Laptop', 'High-performance business laptop'),
       (2, 'ITEM-100002', 'Wireless Mouse', 'Ergonomic wireless mouse'),
       (3, 'ITEM-100003', 'Mechanical Keyboard', 'RGB mechanical keyboard'),
       (4, 'ITEM-100004', 'Monitor', '27-inch 4K display'),
       (5, 'ITEM-100005', 'Webcam', '1080p HD webcam'),
       (6, 'ITEM-100006', 'Desk Lamp', 'LED adjustable desk lamp'),
       (7, 'ITEM-100007', 'Notebook', 'Set of 3 premium notebooks'),
       (8, 'ITEM-100008', 'Pen', 'Ballpoint pen, pack of 10'),
       (9, 'ITEM-100009', 'Stapler', 'Standard office stapler'),
       (10, 'ITEM-100010', 'Desk Organizer', 'Multipurpose desk organizer');

INSERT INTO workers (id, first_name, second_name, passport_number, birth_date)
VALUES (1, 'Worker1', '111', '5556661111', '1981-05-07'),
       (2, 'Worker2', '222', '1112222222', '1975-11-25'),
       (3, 'Worker3', '333', '1132223333', '1971-10-11'),
       (4, 'Worker4', '444', '4442224444', '1972-11-22'),
       (5, 'Worker5', '555', '5552225555', '1973-12-25');

INSERT INTO special_documents (id, document_number, document_type, created_at, order_id)
VALUES (1, '5500001', 'DOCUMENT', '2025-10-04 17:57:00', 1),
       (2, '5500002', 'DOCUMENT', '2025-10-04 17:57:00', 1),
       (3, '5500003', 'DOCUMENT', '2025-10-04 17:57:00', 1),
       (4, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 2),
       (5, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 2),
       (6, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 3),
       (7, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 3),
       (8, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 4),
       (9, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 4),
       (10, '5500004', 'DOCUMENT', '2025-10-04 17:57:00', 5),
       (11, '5500005', 'DOCUMENT', '2025-10-04 17:57:00', 5);

INSERT INTO orders_workers (order_id, worker_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 4),
       (4, 5),
       (4, 1),
       (5, 2),
       (5, 3);

INSERT INTO orders_items (order_id, item_id)
VALUES (1, 1),
       (1, 2),
       (1, 3), -- Order 1: Laptop, Mouse, Keyboard
       (2, 4),
       (2, 5), -- Order 2: Monitor, Webcam
       (3, 6),
       (3, 7),
       (3, 8), -- Order 3: Lamp, Notebook, Pen
       (4, 1),
       (4, 4),
       (4, 9), -- Order 4: Laptop, Monitor, Stapler
       (5, 10),
       (5, 8); -- Order 5: Organizer, Pen
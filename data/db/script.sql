CREATETABLE orders(
	id serial primary key,
	name varchar(255),
	amount integer,
	price numeric
);
INSERT INTO orders(name, amount, price)
VALUES('Printer_HP', 2, 28500.0);
SELECT* FROM orders;
UPDATE orders SET price = 29000.0;
SELECT* FROM orders;
DELETE FROM orders;
CREATETABLE shops(
	id serial primary key,
	name varchar(255)
);

CREATETABLE products(
	id serial primary key,
	name varchar(255)
);

CREATETABLE shops_products(
	id serial primary key,
	shop_id int REFERENCES shops(id),
	product_id int REFERENCES products(id)
);

INSERT INTO shops(name) VALUES ('5X');
INSERT INTO shops(name) VALUES ('Magnit');
INSERT INTO shops(name) VALUES ('Perekrestok');

INSERT INTO products(name) VALUES ('milk');
INSERT INTO products(name) VALUES ('bread');
INSERT INTO products(name) VALUES ('butter');

INSERT INTO shops_products(shop_id, product_id) VALUES (1, 1);
INSERT INTO shops_products(shop_id, product_id) VALUES (1, 2);
INSERT INTO shops_products(shop_id, product_id) VALUES (1, 3);
INSERT INTO shops_products(shop_id, product_id) VALUES (2, 1);
INSERT INTO shops_products(shop_id, product_id) VALUES (2, 2);
INSERT INTO shops_products(shop_id, product_id) VALUES (2, 3);

SELECT* FROM shops_products;
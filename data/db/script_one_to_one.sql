/*first way*/
CREATETABLE registration_numbers(
	id serial primary key,
	seria int,
	number int
);

CREATETABLE company(
	id serial primary key,
	name varchar(255),
	URN_id int REFERENCES registration_numbers(id) unique
);

INSERT INTO registration_numbers(seria, number) 
VALUES (5600, 230089);
INSERT INTO registration_numbers(seria, number) 
VALUES (4408, 250415);
SELECT* FROM registration_numbers;

INSERT INTO company(name, URN_id) 
VALUES ('OOO MegaShop', 1);
INSERT INTO company(name, URN_id) 
VALUES ('OOO Vega', 2);
SELECT* FROM company;

/*second way*/
CREATETABLE reg_numbers(
	id serial primary key,
	seria int,
	number int
);

CREATETABLE company_names(
	id serial primary key,
	name varchar(255)
);

CREATETABLE company_urn(
	id serial primary key,
	company_id int REFERENCES company_names(id) unique,
	reg_num_id int REFERENCES reg_numbers(id) unique
);

INSERT INTO company_names(name) 
VALUES ('OOO MegaShop');
INSERT INTO company_names(name) 
VALUES ('OOO Vega');
SELECT* FROM company_names;

INSERT INTO reg_numbers(seria, number) 
VALUES (5900, 230089);
INSERT INTO reg_numbers(seria, number) 
VALUES (4508, 250415);
SELECT* FROM reg_numbers;

INSERT INTO company_urn(company_id, reg_num_id) 
VALUES (1, 1);
INSERT INTO company_urn(company_id, reg_num_id) 
VALUES (2, 2);
SELECT* FROM company_urn;


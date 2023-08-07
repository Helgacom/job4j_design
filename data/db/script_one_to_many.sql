CREATETABLE autor(
	id serial primary key,
	name varchar(255)
);

CREATETABLE books(
	id serial primary key,
	name varchar(255),
	autor_id int REFERENCES autor(id)
);

INSERT INTO autor(name) VALUES ('S. King');
INSERT INTO books(name, autor_id) VALUES ('It', 1);
SELECT* FROM books;
SELECT* FROM autor 
WHERE id IN (SELECT autor_id FROM books);
CREATETABLE fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('tiger', 7080, '1000-01-01');
INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('stork', 2478, '1111-01-01');
INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('python', 4248, '1201-01-01');
INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('giraffe', 10620, '1302-01-01');
INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('elefant', 21240, '1010-01-01');
INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('kangaroos', 12980, '1770-01-01');
INSERT INTO fauna(name, avg_age)
VALUES ('cat', 5310);
SELECT* FROM fauna;
SELECT* FROM fauna WHERE name LIKE '%fish%';
SELECT* FROM fauna WHERE avg_age > 10000 AND avg_age < 21000;
SELECT* FROM fauna WHERE discovery_date IS NULL;
SELECT* FROM fauna WHERE discovery_date < '1950-01-01';
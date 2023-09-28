CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Alfa');
INSERT INTO company (id, name) VALUES (2, 'Mega');
INSERT INTO company (id, name) VALUES (3, 'Delta');
INSERT INTO company (id, name) VALUES (4, 'Sigma');
INSERT INTO company (id, name) VALUES (5, 'Vector');
INSERT INTO company (id, name) VALUES (6, 'Zero');

INSERT INTO person (id, name, company_id) VALUES (1, 'Ivanov_Ivan', 2);
INSERT INTO person (id, name, company_id) VALUES (2, 'Petrov_Petr', 3);
INSERT INTO person (id, name, company_id) VALUES (3, 'Semenov_Semen', 5);
INSERT INTO person (id, name, company_id) VALUES (4, 'Romanov_Roman', 1);
INSERT INTO person (id, name, company_id) VALUES (5, 'Nikitin_Nikita', 2);

SELECT person.name FROM person WHERE company_id != 5;

SELECT p.name AS Name, c.name AS Company
FROM person p JOIN company c ON p.company_id = c.id;

SELECT c.name company, count(p.name) amount
FROM company c JOIN person p ON p.company_id = c.id
GROUP BY c.name
HAVING count(p.name) = (SELECT MAX(count(p.name))
FROM (SELECT count(p.name) FROM company c JOIN  person p ON p.company_id = c.id
GROUP BY c.name);
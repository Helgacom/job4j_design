create table type(
	id serial primary key, 
	name varchar(255)
);

create table product(
	id serial primary key, 
	name varchar(255),
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type(name) 
values ('крупы'), ('мясо'), ('рыба'), 
('сыр'), ('молоко'), ('дессерты'), ('напитки'), ('фрукты и овощи');

insert into product(name, expired_date, price, type_id) values
('Геркулес', '2023-10-01', 78.9, 1), 
('Филе кур', '2023-08-13', 328.0, 2), 
('Маасдам', '2023-09-22', 180.0, 4), 
('Сок томатный', '2023-11-04', 138.5, 7), 
('Сок яблочный', '2023-12-10', 112.5, 7), 
('Сулугуни', '2023-08-07', 230.0, 4), 
('Эклеры слив.', '2023-08-12', 248.0, 6), 
('Яблоки', '2023-09-01', 89.9, 8), 
('Молоко 2,5%', '2023-11-16', 92.0, 5), 
('Молоко 1,5%', '2023-10-06', 88.0, 5), 
('Гауда', '2023-08-15', 210.0, 4), 
('Форель с/с', '2023-10-01', 478.0, 3), 
('Рис', '2024-02-25', 81.5, 1);
select * from product;

select * from product where type_id = 4;
select * from product where name like '%мороженое%';
select * from product where current_date > expired_date;

select name, price from product
where price = (select max(price) from product);

select t.name, count(p.type_id)
from type as t inner join product p
on t.id = p.type_id
group by t.name;

select * from product where type_id = 4 or type_id = 5;

select t.name, count(p.type_id)
from type as t inner join product p
on t.id = p.type_id
group by t.name
having count(p.type_id) < 10;

select p.name, t.name
from product as p inner join type t
on p.type_id = t.id;

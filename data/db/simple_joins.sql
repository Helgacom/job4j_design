create table owners(
	id serial primary key,
    name varchar(255)
);

create table cars(
	id serial primary key,
    car_name varchar(255),
	number varchar(255),
	owner_id int references owners(id) unique
);

insert into owners(name) values ('Ivanov Ivan');
insert into owners(name) values ('Romanov Igor');
insert into owners(name) values ('Vetrov Anton');
insert into cars(car_name, number, owner_id) 
values ('Ford', 'o632cc', 1);
insert into cars(car_name, number, owner_id) 
values ('Mercedes', 'x116ok', 2);
insert into cars(car_name, number, owner_id) 
values ('Reno', 'p408ca', 3);

select * from owners;
select * from cars;

select o.name, c.car_name, c.number
from owners as o join cars as c
on o.id = c.id;

select o.name as Name, c.car_name as Model, c.number as Number
from owners as o join cars as c
on o.id = c.id;

select o.name as "Owner name", c.car_name Model, c.number Number
from owners o join cars c
on o.id = c.id;

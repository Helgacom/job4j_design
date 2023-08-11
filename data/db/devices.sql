create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) 
values ('PocketBook', 3950.0), ('IPhone', 108400.0), ('Samsung', 62800.0), ('Nokia', 48000.0);
select * from devices;
insert into people(name) 
values ('Ivanov_Ivan'), ('Vetrov_Timur'), ('Romanov_Anton');
select * from people;
insert into devices_people(device_id, people_id) 
values (1, 1), (2, 2), (3, 3), (4, 3);
select * from devices_people;

select avg(price) from devices;
select p.name, avg(d.price)
from people as p join devices d 
on p.id = d.id
group by p.name
having avg(d.price) > 5000.0;

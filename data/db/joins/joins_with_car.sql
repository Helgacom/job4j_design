create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values
('sedan'), ('comp'), ('hatchback'), ('universal'), ('fastback'), ('crossover');

insert into car_engines(name) values
('gasoline'), ('diesel'), ('rotor'), ('hybrid');

insert into car_transmissions(name) values
('mech_tm'), ('auto_tm'), ('robo_tm'), ('var_tm');

insert into cars(name, body_id, engine_id, transmission_id) values
('reno', 6, 2, 1), ('volvo', 1, 1, 2), ('MB', 1, 4, 3),
('BMV', 3, 2, 4), ('peugeot', 2, 3, 1), ('toyota', 1, 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) values
('opel', null, 2, 1), ('KIA', 1, null, 2), ('subaru', 1, 4, null);

select c.name as car_name, b.name as body_name, 
e.name as engine_name, t.name as transmission_name from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on e.id = c.engine_id
left join car_transmissions t on t.id = c.transmission_id;

select b.name as body_name from cars c
right join car_bodies b on c.body_id = b.id
where c.body_id is null;

select e.name as engine_name from cars c
right join car_engines e on c.engine_id = e.id
where c.engine_id is null;

select t.name as transmission_name from cars c
right join car_transmissions t on c.transmission_id = t.id
where c.transmission_id is null;


create database clients;

create table products(
    id serial primary key,
    name varchar(50),
    price float,
    amount int
);

create table bonuses(
    id serial primary key,
    bonus_type varchar(50),
	bonus float
);

create table clients(
    id serial primary key,
    name varchar(255),
    bonus_id int references bonuses(id)
);

create table clients_products(
	id serial primary key,
	client_id int references clients(id),
	product_id int references  products(id)
);

create table delivery(
    id serial primary key,
    del_type varchar(255)
);

create table order_list(
    id serial primary key,
    name varchar(20),
    total_price float,
    client_id int references clients(id),
    del_id int references delivery(id),
	bonus_id int references bonuses(id)
);


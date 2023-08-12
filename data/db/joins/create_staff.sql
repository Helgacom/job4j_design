create database staff;

create table departaments(
    id serial primary key,
    dep_name varchar(255)
);

create table employees(
    id serial primary key,
    emp_name varchar(255),
    dep_id int references departaments(id)
);

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);
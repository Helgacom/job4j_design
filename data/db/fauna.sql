create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('tiger', 7080, '1000-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('stork', 2478, '1111-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('python', 4248, '1201-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('giraffe', 10620, '1302-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('elefant', 21240, '1010-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('kangaroos', 12980, '1770-01-01');
insert into fauna(name, avg_age)
values ('cat', 5310);
select * from fauna;
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
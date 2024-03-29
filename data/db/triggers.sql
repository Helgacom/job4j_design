create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();
	
insert into products (name, producer, count, price) 
values ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) 
values ('product_1', 'producer_1', 3, 50);
select * from products;

alter table products disable trigger discount_trigger;

insert into products (name, producer, count, price) 
values ('product_1', 'producer_1', 3, 50);
select * from products;

drop trigger discount_trigger on products;

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

drop trigger tax_trigger on products;

create or replace function add_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger price_add_tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure add_tax();
	
insert into products (name, producer, count, price) 
values ('product_2', 'producer_2', 8, 115);
insert into products (name, producer, count, price) 
values ('product_4', 'producer_4', 3, 50);

select * from products;
drop trigger price_add_tax_trigger on products;

create or replace function tax_count()
    returns trigger as
$$
    BEGIN
	    new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_count_trigger
    before insert
    on products
    for each row
    execute procedure tax_count();
	
insert into products (name, producer, count, price) 
values ('product_5', 'producer_2', 8, 115);

select * from products;
drop trigger tax_count_trigger on products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function add_history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date) values (new.name, new.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_history_trigger
    after insert
    on products
    for each row
    execute procedure add_history();
	
insert into products (name, producer, count, price) 
values ('product_6', 'producer_4', 3, 50);

select * from history_of_price;
drop trigger add_history_trigger on products;

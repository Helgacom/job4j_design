create table users(
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id) unique
);

create table roles(
	id serial primary key,
	role_name varchar(255)
);

create table rules_list(
	id serial primary key,
	rule_name varchar(255)
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules_list(id)
);

create table items(
	id serial primary key,
	item varchar(255),
	price numeric,
	user_id int references users(id) unique,
	category_id int references categories(id) unique,
	state_id int references states(id) unique
);

create table comments(
	id serial primary key,
	comments varchar(255),
	item_id int references items(id) unique
);

create table attachs(
	id serial primary key,
	file_name varchar(255),
	item_id int references items(id) unique
);

create table states(
	id serial primary key,
	state varchar(255)
);

create table categories(
	id serial primary key,
	category_name varchar(255)
);




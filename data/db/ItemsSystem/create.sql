CREATETABLE users(
	id serial primary key,
	name varchar(255),
	roles_id int REFERENCES roles(id) unique
);

CREATETABLE roles(
	id serial primary key,
	role_name varchar(255)
);

CREATETABLE rules_list(
	id serial primary key,
	rule_name varchar(255)
);

CREATETABLE roles_rules(
	id serial primary key,
	roles_id int REFERENCES roles(id),
	rules_id int REFERENCES rules_list(id)
);

CREATETABLE items(
	id serial primary key,
	item varchar(255),
	price numeric,
	user_id int REFERENCES users(id) unique,
	category_id int REFERENCES categories(id) unique,
	state_id int REFERENCES states(id) unique
);

CREATETABLE comments(
	id serial primary key,
	comments varchar(255),
	item_id int REFERENCES items(id) unique
);

CREATETABLE attachs(
	id serial primary key,
	file_name varchar(255),
	item_id int REFERENCES items(id) unique
);

CREATETABLE states(
	id serial primary key,
	state varchar(255)
);

CREATETABLE categories(
	id serial primary key,
	category_name varchar(255)
);




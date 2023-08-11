insert into users(name, roles_id) values ('Ivanov_Ivan', 1);
insert into users(name, roles_id) values ('Rybov_Timur', 2);
insert into roles(role_name) values ('Customer');
insert into roles(role_name) values ('Admin');
where id in (select roles_id from users);

insert into rules_list(rule_name) values ('get_state');
insert into rules_list(rule_name) values ('cancel');
insert into rules_list(rule_name) values ('answer');
insert into rules_list(rule_name) values ('question');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (1, 2);
insert into roles_rules(roles_id, rules_id) values (1, 4);
insert into roles_rules(roles_id, rules_id) values (2, 1);
insert into roles_rules(roles_id, rules_id) values (2, 3);

insert into items(item, price, user_id, category_id, state_id) values ('Printer_HP', 28000, 1, 1, 1);
insert into items(item, price, user_id, category_id, state_id) values ('Monitor_LG', 42000, 1, 2, 2);
where id in (select state_id from items);

insert into comments(comments, item_id) values ('delivery to customer', 1);
insert into comments(comments, item_id) values ('delivery to customer', 2);
where id in (select item_id from comments);

insert into attachs(file_name, item_id) values ('Order_HP_Ivanov.txt', 1);
insert into attachs(file_name, item_id) values ('Order_LG_Ivanov.txt', 2);
where id in (select item_id from attachs);

insert into categories(category_name) values ('Printers');
insert into categories(category_name) values ('Monitors');
where id in (select category_id from items);

insert into states(state, item_id) values ('in_work', 1);
insert into states(state, item_id) values ('done', 2);
where id in (select category_id from items);

insert into autor(name) values ('S. King');
insert into books(name, autor_id) VALUES ('It', 1);
where id in (select autor_id from books);
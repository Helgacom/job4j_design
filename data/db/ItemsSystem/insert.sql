INSERT INTO users(name, roles_id) VALUES ('Ivanov_Ivan', 1);
INSERT INTO users(name, roles_id) VALUES ('Rybov_Timur', 2);
INSERT INTO roles(role_name) VALUES ('Customer');
INSERT INTO roles(role_name) VALUES ('Admin');
WHERE id IN (SELECT roles_id FROM users);

INSERT INTO rules_list(rule_name) VALUES ('get_state');
INSERT INTO rules_list(rule_name) VALUES ('cancel');
INSERT INTO rules_list(rule_name) VALUES ('answer');
INSERT INTO rules_list(rule_name) VALUES ('question');

INSERT INTO roles_rules(roles_id, rules_id) VALUES (1, 1);
INSERT INTO roles_rules(roles_id, rules_id) VALUES (1, 2);
INSERT INTO roles_rules(roles_id, rules_id) VALUES (1, 4);
INSERT INTO roles_rules(roles_id, rules_id) VALUES (2, 1);
INSERT INTO roles_rules(roles_id, rules_id) VALUES (2, 3);

INSERT INTO items(item, price, user_id, category_id, state_id) VALUES ('Printer_HP', 28000, 1, 1, 1);
INSERT INTO items(item, price, user_id, category_id, state_id) VALUES ('Monitor_LG', 42000, 1, 2, 2);
WHERE id IN (SELECT state_id FROM items);

INSERT INTO comments(comments, item_id) VALUES ('delivery to customer', 1);
INSERT INTO comments(comments, item_id) VALUES ('delivery to customer', 2);
WHERE id IN (SELECT item_id FROM comments);

INSERT INTO attachs(file_name, item_id) VALUES ('Order_HP_Ivanov.txt', 1);
INSERT INTO attachs(file_name, item_id) VALUES ('Order_LG_Ivanov.txt', 2);
WHERE id IN (SELECT item_id FROM attachs);

INSERT INTO categories(category_name) VALUES ('Printers');
INSERT INTO categories(category_name) VALUES ('Monitors');
WHERE id IN (SELECT category_id FROM items);

INSERT INTO states(state, item_id) VALUES ('in_work', 1);
INSERT INTO states(state, item_id) VALUES ('done', 2);
WHERE id IN (SELECT category_id FROM items);

INSERT INTO autor(name) VALUES ('S. King');
INSERT INTO books(name, autor_id) VALUES ('It', 1);
WHERE id IN (SELECT autor_id FROM books);
insert into products(name, price, amount) values
('milk', 87.9, 23),('bread', 40.0, 12),
('butter', 196.0, 3),('cheese', 322.5, 6),('olive_oil', 480.0, 8);
select * from products;

insert into bonuses(bonus_type, bonus) values
('free_del', 1), ('cashback_10', 1), ('cashback_20', 1), ('gift', 1), ('discount_15', 0.85);
select * from bonuses;

insert into clients(name, bonus_id) values
('Ivanov_I.I.', 1), ('Semenova_S.Y.', 5), ('Romanova_E.V.', 2),
('Kovalev_D.V.', 4),('Volkov_Y.A.', 2), ('Vetrov_G.S.', 5),
('Mun_V.V.', 5), ('Ermilova_A.S.', 1), ('Nikitin_V.V.', 5);
select * from clients;

insert into clients_products(client_id, product_id) values
(1, 1), (1, 2), (1, 5),
(2,1), (2, 4), (2, 5),
(3, 3),
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5),
(5, 2), (5, 3),
(6, 3), (6, 4), (6, 5),
(7, 4),
(8, 3), (8, 5),
(9, 4), (9, 5);
select * from clients_products;

insert into delivery(del_type) values
('self-del'), ('free_address_del'), ('address_del');
select * from delivery;

insert into order_list(name, total_price, client_id, del_id, bonus_id) values
('1045789', 890.4, 1, 2, 1),
('1045790', 196.0, 2, 1, 5),
('1045735', 236.0, 3, 3, 2),
('1045741', 998.5, 4, 1, 4),
('1045716', 676.0, 5, 1, 2),
('1045205', 1126.4, 6, 1, 5),
('1045341', 802.5, 7, 1, 5),
('1045780', 322.5, 8, 2, 1),
('1045712', 607.9, 9, 3, 5);

create view orders_details
    as select c.name as client_name, o.name as code, o.total_price,
    sum(p.price) * (b.bonus) as price_whith_bonus, d.del_type as delivery
    from delivery d right join order_list o
    on o.del_id = d.id left join bonuses b
    on o.bonus_id = b.id right join clients c
    on c.bonus_id = b.id left join clients_products cp
    on cp.client_id = c.id left join products p on cp.product_id = p.id
    group by(c.name, o.name, o.total_price, b.bonus, d.del_type);

select * from orders_details;







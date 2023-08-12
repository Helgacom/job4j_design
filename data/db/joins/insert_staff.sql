insert into departaments(dep_name) values
('directorate'), ('finance_dep'), ('sales_dep'), ('logistics-dep'), ('legal_dep'), ('HR_dep');

insert into employees(emp_name, dep_id) values
('Ivanov_I.I.', 1), ('Semenova_S.Y.', 2), ('Romanova_E.V.', 6), ('Kovalev_D.V.', 4),
('Volkov_Y.A.', 3), ('Vetrov_G.S.', 5), ('Mun_V.V.', 5), ('Ermilova_A.S.', 6), ('Nikitin_V.V.', 3);

insert into teens(name, gender) values
('Ivanov_I.I.', 'male'), ('Semenova_S.Y.', 'female'), ('Romanova_E.V.', 'female'),
('Kovalev_D.V.', 'male'), ('Volkov_Y.A.', 'male'), ('Vetrov_G.S.', 'male'),
('Mun_V.V.', 'male'), ('Ermilova_A.S.', 'female'), ('Nikitin_V.V.', 'male');

select * from departaments d left join employees e on d.id = e.dep_id;

select * from departaments d right join employees e on d.id = e.dep_id;

select * from departaments d full join employees e on d.id = e.dep_id;

select * from departaments d cross join employees e;

select d.dep_name from departaments as d left join employees e
on d.id = e.dep_id where e.emp_name = null;

select * from departaments as d left join employees e
on d.id = e.dep_id order by d.id;
select * from employees as e right join departaments d
on d.id = e.dep_id order by d.id;

select * from employees as e left join departaments d
on d.id = e.dep_id order by d.id;
select * from departaments as d right join employees e
on d.id = e.dep_id order by d.id;

select t.name as t, t1.name as t1 from teens t
cross join teens t1
where t.gender != t1.gender;
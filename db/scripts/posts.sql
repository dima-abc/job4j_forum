/*Схема таблицы posts*/
create table if not exists posts
(
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);
/*Первичные данные в таблице posts*/
insert into posts(name, description) VALUES ('О чем этот форум?', 'Описание о чем форум и что в нем есть');
insert into posts(name, description) VALUES ('Правила форума.', 'Описание правил форума')
/*Схема таблицы пользователей и их ролей*/
/*Схема таблицы ролей пользователей*/
create table if not exists authorities
(
    id        serial primary key,
    authority varchar(50) not null unique
);
/*Схема таблицы users*/
create table if not exists users
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority)
values ('ROLE_ADMIN');
insert into authorities (authority)
values ('ROLE_USER');

/*Добавление пользователя с правами администратора
  login=root, password=secret*/
insert into users (username, password, enabled, authority_id)
values ('root', '$2a$10$PyZlyP39bhauGWAgSz.2Su9BoK53NYivaufjn2vMpAZH0m8R2mcum', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));
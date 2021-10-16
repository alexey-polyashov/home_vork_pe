
--USERS & ROLES
create table users
(
    id         bigserial primary key,
    username    varchar(30) not null unique,
    password    varchar(80) not null,
    email       varchar(50) unique,
    firstname   varchar(150),
    lastname    varchar(150),
    birthday    date,
    phone       varchar(50),
    address     varchar(250),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (username, password, email, phone, address, firstname, lastname, birthday)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com', '222-55-44', 'Moscow, Panfilova 4', 'Алексей', 'Пупкин', '1990-01-01'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com', '123-34-55', 'Rostov, Lenina 12', 'Василий', 'Васильевич', '1980-01-01' ),
       ('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'nick_davidson@gmail.com', '324-53-34', 'Voronezh, Lizyukova 8', 'Иван', 'Иванов', '1970-01-01');

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 1);

CREATE TABLE user_messages (
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    theme       varchar(250),
    message     text,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into user_messages (user_id, theme, message)
values ('1', 'Тема 1', 'Сообщение 1'),
       ('1', 'Тема 2', 'Сообщение 2' ),
       ('3', 'Тема 3', 'Сообщение 3');

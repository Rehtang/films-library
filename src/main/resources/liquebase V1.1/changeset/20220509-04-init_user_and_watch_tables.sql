create table users
(
    "id" uuid not null,
    "username" varchar(255) not null,
    "email" varchar(255),
    "password" varchar(255) not null,
    "first_name" varchar(255),
    "last_name" varchar(255),
    "status" varchar(20) default 'ACTIVE' not null,
    primary key (id),
    unique ("username")
);

insert into users ("id", username, "password", first_name, last_name, email,status)
values ('00000000-0000-0000-0000-000000000000', 'admin',
        '$2a$12$pLDh7PJ0erqwictwJShOk.bP5XH5G5RNJTZHkN6h2LEhM.j0JVeI.', 'admin',
        'admin', 'admin@email.com', default),
       ('11111111-1111-1111-1111-111111111111', 'user',
        '$2a$12$qJ49gHSfFAg0W.MzCNudPucSWshY1PEST3ux83I2PQrtIJlKZs8tC', 'user',
        'user', 'user@email.com', default);
-- 00011202
-- 1112314
create table watch_list(
    user_id uuid not null,
    film_id varchar(255) not null,
    primary key (user_id,film_id),
    foreign key (user_id) references users(id),
    foreign key (film_id) references film(imdb_id)
);

create table roles(
    "id" uuid,
    "name" varchar(50) not null ,
    unique ("name"),
    primary key ("id")
);

insert into roles ("id", "name")
values ('1871dbe4-ffda-36ba-92db-a845efaa6fa7', 'ROLE_USER'),
       ('73acd9a5-9721-30b7-9066-c82595a1fae3', 'ROLE_ADMIN');

create table "user_role"(
    "user_id" uuid,
    "role_id" uuid,
    primary key ("user_id","role_id"),
    foreign key ("user_id") references users("id"),
    foreign key ("role_id") references roles("id")
);

insert into "user_role" (user_id, role_id)
values ('00000000-0000-0000-0000-000000000000', '73acd9a5-9721-30b7-9066-c82595a1fae3'),
       ('11111111-1111-1111-1111-111111111111', '1871dbe4-ffda-36ba-92db-a845efaa6fa7');
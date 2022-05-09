create table users
(
    "id" uuid not null,
    "username" varchar(255) not null,
    "email" varchar(255),
    "password" varchar(255) not null,
    "first_name" varchar(255),
    "last_name" varchar(255),
    primary key (id),
    unique ("username")
);

create table watch_list(
    user_id uuid not null,
    film_id varchar(255) not null,
    primary key (user_id,film_id),
    foreign key (user_id) references users(id),
    foreign key (film_id) references film(imdb_id)
);
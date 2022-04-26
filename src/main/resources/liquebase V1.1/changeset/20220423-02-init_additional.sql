create table "film_recieve"(
    "film_id" varchar(255) ,
    "is_recieved" boolean default false,
    primary key("film_id")
);
copy film_recieve("film_id") from '20220423-03-movie_warmup.csv' delimiter ',' CSV;
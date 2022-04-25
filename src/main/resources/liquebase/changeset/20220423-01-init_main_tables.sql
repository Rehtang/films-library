create table "film"
(
    "imdb_id" varchar(255) not null,
    "title" varchar(255),
    "year_of_release" varchar(255),
    "rated"  varchar(255),
    "released"  varchar(255),
    "runtime"  varchar(255),
    "genre"  varchar(255),
    "director"  varchar(255),
    "writer" text,
    "actor" text,
    "plot" text,
    "language_of"  varchar(255),
    "country"  varchar(255),
    "award" text,
    "poster" text,
    "metascore"  varchar(255),
    "imdb_rating"  varchar(255),
    "imdb_vote"  varchar(255),
    "type"  varchar(255),
    "dvd"  varchar(255),
    "box_office"  varchar(255),
    "production" varchar(255),
    "website"  varchar(255),
    "response" boolean,
    unique ("imdb_id"),
    primary key("imdb_id")
);

create table "film_rating"(
    "id" bigserial,
    "film_id"  varchar(255) references film(imdb_id),
    "source"  varchar(255),
    "value"  varchar(255),
    primary key("id")
);
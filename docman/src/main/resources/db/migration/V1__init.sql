create sequence hibernate_sequence start 1 increment 1;

create DATABASE docman
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

create table post (
    id int8 not null,
    content text not null,
    created_on timestamp,
    title varchar(255) not null,
    updated_on timestamp,
    category text not null,
    username varchar(255) not null,
    primary key (id)
);

create table users (
    id int8 not null,
    email varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

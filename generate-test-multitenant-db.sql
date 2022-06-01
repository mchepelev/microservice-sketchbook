begin;

create schema "A_01";

create table "A_01".user
(
    id   bigserial    not null,
    name varchar(255) not null,
    constraint user_pkey
        primary key (id)
);

create schema "A_02";

create table "A_02".user
(
    id   bigserial    not null,
    name varchar(255) not null,
    constraint user_pkey
        primary key (id)
);

commit;
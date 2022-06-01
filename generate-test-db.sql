begin;

create schema team_management;

create table team_management.address
(
    id       bigserial      not null,
    address1 varchar(255) not null,
    constraint address_pkey
        primary key (id)
);

create table team_management.company
(
    id       bigserial      not null,
    name varchar(255) not null,
    constraint company_pkey
        primary key (id),
    constraint company_name_ukey
        unique (name)
);

create table team_management.office
(
    id       bigserial      not null,
    name varchar(255) not null,
    company_id integer not null,
    address_id integer not null,
    constraint office_pkey
        primary key (id),
    constraint company_id_fkey
        foreign key (company_id) references team_management.company,
    constraint address_id_fkey
        foreign key (address_id) references team_management.address,
    constraint office_name_ukey
        unique (name, company_id)
);

create table team_management.team
(
    id       bigserial      not null,
    name varchar(255) not null,
    company_id integer not null,
    constraint team_pkey
        primary key (id),
    constraint company_id_fkey
        foreign key (company_id) references team_management.company
);

create table team_management.employee
(
    id       bigserial      not null,
    name varchar(255) not null,
    home_address_id integer not null,
    company_id integer not null,
    office_id integer,
    constraint employee_pkey
        primary key (id),
    constraint address_id_fkey
        foreign key (home_address_id) references team_management.address,
    constraint company_id_fkey
        foreign key (company_id) references team_management.company,
    constraint office_id_fkey
        foreign key (office_id) references team_management.office
);

create table team_management.employee_team
(
    id       bigserial      not null,
    employee_id integer not null,
    team_id integer not null,
    constraint employee_team_pkey
        primary key (id),
    constraint employee_id_fkey
        foreign key (employee_id) references team_management.employee,
    constraint team_id_fkey
        foreign key (team_id) references team_management.team
);

commit;
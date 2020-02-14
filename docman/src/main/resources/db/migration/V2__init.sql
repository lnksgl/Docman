create sequence hibernate_sequence start 1 increment 1;

create table post (
    id int8 not null,
    content text not null,
    created_on timestamp,
    title varchar(255) not null,
    updated_on timestamp,
    category text not null,
    id_username int8 not null
        constraint post_user_id_fk
            references users,
    primary key (id)
);

create table privilege (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table role (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table users (
    id int8 not null,
    email varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table users_roles (
	user_id int8 not null
		constraint users_roles_users_id_fk
			references users,
	role_id8 int
	    constraint users_roles_role_id_fk
			references role
);

create table roles_privileges (
	role_id int8 not null
		constraint roles_privileges_role_id_fk
			references role,
	privilege_id int8 not null
	    constraint roles_privileges_privilege_id_fk
			references privilege
);

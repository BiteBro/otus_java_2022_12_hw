create table client
(
    id    bigserial not null primary key,
    name varchar(50)
);
create table address
(
    client_id bigint not null references client (id),
    street varchar(255)
);
create table phone
(
    id    bigserial not null,
    client_id bigint not null references client (id),
    number varchar(20),
    CONSTRAINT phone_pk PRIMARY KEY (id, client_id)
);

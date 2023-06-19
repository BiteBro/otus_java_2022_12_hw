create table client
(
    id    bigint not null primary key,
    name varchar(50)
);
create table address
(
    client_id bigint not null references client (id),
    street varchar(255)
);
create table phone
(
    id    bigint  not null constraint phone_pk  primary key,
    client_id bigint not null  references client (id),
    number varchar(20)
);




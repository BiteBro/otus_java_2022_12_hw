-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence phone_SEQ start with 1 increment by 1;
-- один клиент много номеров
create table phone
(
    id   bigint not null primary key,
    number varchar(50),
    foreign key (id_client) references client(id) on delete cascade
);
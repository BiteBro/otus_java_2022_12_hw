-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence address_SEQ start with 1 increment by 1;
-- один клиент один адрес
create table address
(
    id   bigint not null primary key,
    street varchar(50),
    foreign key (id_client) references client(id)
);
ALTER TABLE car_model
    ADD height INT NULL;

create table if not exists engine (
    id bigint auto_increment,
    name varchar(50) not null,
    capacity int not null,
    number_of_cylinders int null,
    height int null,
    car_model bigint not null,
    constraint engine_pk primary key (id),
    constraint engine_car_model_id_fk foreign key (car_model) references car_model (id)
);
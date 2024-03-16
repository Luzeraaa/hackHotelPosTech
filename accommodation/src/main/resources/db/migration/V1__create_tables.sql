create table accommodation (id bigserial not null, check_in date, check_out date, id_user bigint, total_people integer, room_id bigint, primary key (id));
create table amenitie (id bigserial not null, amount integer, description varchar(255), name varchar(255), accommodation_id bigint, primary key (id));
create table building (id bigserial not null, name varchar(255), accommodation_id bigint, primary key (id));
create table location (id bigserial not null, city varchar(255), neighborhood varchar(255), number integer, reference varchar(255), state varchar(255), street varchar(255), zip_code varchar(255), name varchar(255), primary key (id));
create table room (id bigserial not null, amount integer, bathroom integer, room_type varchar(255) check (room_type in ('SIMPLE_STANDARD','DOUBLE_STANDARD','SIMPLE_LUXARY','DOUBLE_LUXURY')), total_beds integer, total_people integer, value_daily numeric(38,2), building_id bigint, primary key (id));
alter table if exists accommodation add constraint FK2jjn0jw1vy6p1cptu8nchjerl foreign key (room_id) references room;
alter table if exists amenitie add constraint FKauc8dq12i8askj98y9p5p8hqm foreign key (accommodation_id) references location;
alter table if exists building add constraint FKbwdba2kdgwmuto7qbtcfdafqr foreign key (accommodation_id) references location;
alter table if exists room add constraint FK4kmfw73x2vpfymk0ml875rh2q foreign key (building_id) references building;
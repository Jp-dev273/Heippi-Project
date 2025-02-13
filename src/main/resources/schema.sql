create table hospital (
  id_hospital varchar(255) not null,
  address varchar(255),
  medical_services varchar(255),
  name varchar(255),
  primary key (id_hospital)
);

create table physician (
    address varchar(255),
    name varchar(255),
    user_id varchar(255) not null,
    id_hospital varchar(255),
    primary key (user_id)
);

create table observation (
    id bigint not null,
    description varchar(255),
    health_status varchar(255),
    specialty varchar(255),
    id_hospital varchar(255),
    id_patient varchar(255),
    id_physician varchar(255),
    primary key (id)
);

create table patient (
    address varchar(255),
    birth_date date,
    name varchar(255),
    user_id varchar(255) not null,
    primary key (user_id)
);

create table token_password (
    id_user varchar(255) not null,
    expiration date,
    token varchar(255),
    primary key (id_user)
);

create table users (
    id varchar(255) not null,
    activated boolean not null,
    activation_key varchar(255),
    authority enum ('HOSPITAL','PATIENT','PHYSICIAN'),
    email varchar(255),
    password varchar(255),
    telephone varchar(255),
    primary key (id)
);

alter table if exists users
    drop constraint if exists UK6dotkott2kjsp8vw4d0m25fb7;

alter table if exists users
    add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);

create sequence observation_seq start with 1 increment by 50;

alter table if exists hospital
    add constraint FKo19k1bwwtxbkmejnk6934y67e
        foreign key (id_hospital)
            references users;

alter table if exists physician
    add constraint FKgpw2b2wm20vvqglcj1p30hhpa
        foreign key (id_hospital)
            references hospital;

alter table if exists physician
    add constraint FK8whuhj0d5leknq3b8uubhhh9k
        foreign key (user_id)
            references users;

alter table if exists observation
    add constraint FKbwnjj6xginpmo3om2qf5nyxpw
        foreign key (id_hospital)
            references hospital;

alter table if exists observation
    add constraint FK90wlvoa7yjyqlfxegpvhm4hqj
        foreign key (id_patient)
            references patient;

alter table if exists observation
    add constraint FKkdn8l3m6tuduxlabcy4lmrkp6
        foreign key (id_physician)
            references physician;

alter table if exists patient
    add constraint FKie6vajiyur53rjcl5nc2pe83t
        foreign key (user_id)
            references users;

alter table if exists token_password
    add constraint FKn5i7l61lblrytxf66m80xix1l
        foreign key (id_user)
            references users;
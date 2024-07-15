-- schema.sql
drop table product if exists;
create table product (
   prd_id varchar(255) not null,
    prd_desc varchar(255),
    prd_img varchar(255),
    prd_nm varchar(255),
    prd_price integer not null,
    primary key (prd_id)
);

drop table customer if exists;
create table customer (
   customer_key varchar(255) not null,
    customer_email varchar(255),
    customer_name varchar(255),
    primary key (customer_key)
);
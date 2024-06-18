CREATE TABLE CAR_PART_CATEGORY(
    ID INT GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(15) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE CAR_PART(
    ID INT GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(50) NOT NULL,
    CATEGORY_ID INT NOT NULL,
    PRICE DECIMAL(6, 2) NOT NULL,
    DESCRIPTION VARCHAR(255),
    PRIMARY KEY (ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CAR_PART_CATEGORY(ID)
);

--
-- create table if not exists users (
--     id identity,
--     username varchar(100) not null unique,
--     password varchar(1000) not null
--     );
--
-- create table if not exists roles (
--     id   identity,
--     name varchar(100) not null unique
--     );
--
-- create table if not exists users_roles (
--     user_id      bigint not null,
--     role_id bigint not null,
--     constraint fk_users foreign key (user_id) references users(id),
--     constraint fk_roles foreign key (role_id) references roles(id)
--     );
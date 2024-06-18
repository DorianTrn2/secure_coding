INSERT INTO CAR_PART_CATEGORY(NAME) VALUES('CAR_PART');
INSERT INTO CAR_PART_CATEGORY(NAME) VALUES('OIL');
INSERT INTO CAR_PART_CATEGORY(NAME) VALUES('ACCESSORIES');
INSERT INTO CAR_PART_CATEGORY(NAME) VALUES('OTHER');

INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('WINTER TYRE', 1, 250, '235/45/R18');
INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('MOTOR OIL', 2, 20, '10W 40');
INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('Tire chains for winter conditions', 3, 40, '235/45/R18');
INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('Tire chains for winter conditions', 3, 40, '235/45/R18');
INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('Carglass clean fluid', 4, 16, 'Winter mix');
INSERT INTO CAR_PART(NAME, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES('Thule box', 3, 541, 'Roof box');

insert into users(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin

-- insert into roles (id, name)
-- values
--     (1, 'ROLE_ADMIN'),
--     (2, 'ROLE_USER');
--
-- insert into users_roles (user_id, role_id)
-- values
--     (1, 2),
--     (2, 1);
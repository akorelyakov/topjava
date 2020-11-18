DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2020-11-18 14:53:32.000000', 'user meal 1000', 1000),
       (100000, '2020-11-18 15:57:32.000000', 'user meal 1100', 1100),
       (100000, '2020-11-17 14:52:32.000000', 'user meal 800', 800),
       (100001, '2020-11-18 13:53:32.000000', 'admin meal 1000', 1000),
       (100001, '2020-11-18 15:53:32.000000', 'admin meal 1200', 1200),
       (100001, '2020-11-17 14:54:32.000000', 'admin meal 800', 800);

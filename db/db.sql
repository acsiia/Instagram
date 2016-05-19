create table country (
country_id int PRIMARY KEY,
country_en varchar(150) NOT NULL,
country_ru varchar(150) NOT NULL
);

INSERT INTO country(country_id, country_en, country_ru) VALUES
(1, 'Belarus', 'Беларусь'),
(2, 'Russia', 'Россия'),
(3, 'United Kingdom', 'Соединённое королевство Великобритании и Ирландии'),
(4, 'China', 'Китай'),
(5, 'Lithuania', 'Литва'),
(6, 'Germany', 'Германия'),
(7, 'Ukraine', 'Украина'),
(8, 'Brazil', 'Бразилия'),
(9, 'USA', 'США'),
(10, 'Kazakhstan', 'Казахстан'),
(11, 'Georgia', 'Грузия'),
(12, 'Latvia', 'Латвия'),
(13, 'Estonia', 'Эстония');


CREATE TABLE users (
id serial PRIMARY KEY,
login varchar(20) NOT NULL UNIQUE,
password varchar(144) NOT NULL,
email varchar(255) NOT NULL UNIQUE,
user_role varchar(255) NOT NULL,
user_enable boolean DEFAULT true 
);

CREATE TABLE  post (
post_id serial PRIMARY KEY,
user_id int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
post_content varchar,
post_send_date timestamp without time zone,
img bytea,
likes int DEFAULT 0,
dislikes int  DEFAULT 0
);


CREATE TABLE  comment (
comment_id serial PRIMARY KEY,
post_id int references post(post_id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
owner int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
comment_content varchar
);

CREATE TABLE  profile (
id serial PRIMARY KEY,
user_id int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
country_id int references country(country_id) ON UPDATE CASCADE ON DELETE CASCADE,
firstname varchar(125),
surname varchar(125),
secondname varchar(125),
city varchar(125),
avatar bytea,
sex varchar(15),
birthday  date
);

CREATE TABLE rating (
rating_id serial PRIMARY KEY,
post_id int references post(post_id) ON UPDATE CASCADE ON DELETE CASCADE,
sender int references users(id) ON UPDATE CASCADE ON DELETE CASCADE,
type varchar(255) NOT NULL
);


INSERT INTO users(login, password, email, user_role)
    VALUES ('admin', '23d4a6a2ea14ab8f999e9dfe47a9a8290e19519efe9f9eb68dc7b876ae631ee05cd3c0f522abb16fb60e51ea4d42662ba08f629e1b0ed2e6aa33b8851d660bc55e0415e4b66d50ee', 'admin@mail.ru', 'ADMIN');


INSERT INTO users(login, password, email, user_role)
    VALUES ('alex', '291ee101efe3abb93c598930105947ba6ecb23d1e372497e3d87a56a1798b3e95979947d699ff85d10cfa2e156db93086346831ef83affe9d1fe33d1c1ae7a5b21c3385133e77881', 'alex.moiseenkov@mail.ru', 'USER'),
	   ('alexandro', '39f87530c5c2decf9913a01a434724c599a958b21870dda2f81c3145f84da1cf71c8805c84aee45ab883c2ce29ee04f5dfe0774f30ddcd35d7092d992a8de6cc1fccada032fa95fd', 'alexandro@mail.ru', 'USER'),
	   ('archi', 'f748978c8a9ae839b5ef6ebf941ceb8a5ed8af5ef541435f4b6ecab4198097076f1eceec4c51974d2bdc629d35eb844249753f3938d1e570aee41559d5f5ed302f7eb08312191897', 'archibalt@bsuir.by', 'USER'),

INSERT INTO profile(
            user_id, country_id, firstname, surname, secondname, city, sex, birthday)
    VALUES (2, 1, 'Моисеенков', 'Алексей', 'Сергеевич', 'Минск', 'male', '1995-02-16'),
	   (3, 1, 'Велитченко', 'Александр', 'Александрович', 'Минск', 'male', '1995-02-11'),
	   (4, 1, 'Мяцкель', 'Артем', 'Сергеевич', 'Минск', 'male', '1994-12-31'),


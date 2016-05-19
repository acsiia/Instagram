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
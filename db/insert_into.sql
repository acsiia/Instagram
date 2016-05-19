INSERT INTO users(login, password, email, user_role)
    VALUES ('alex', '291ee101efe3abb93c598930105947ba6ecb23d1e372497e3d87a56a1798b3e95979947d699ff85d10cfa2e156db93086346831ef83affe9d1fe33d1c1ae7a5b21c3385133e77881', 'alex.moiseenkov@mail.ru', 'USER'),
	   ('alexandro', '39f87530c5c2decf9913a01a434724c599a958b21870dda2f81c3145f84da1cf71c8805c84aee45ab883c2ce29ee04f5dfe0774f30ddcd35d7092d992a8de6cc1fccada032fa95fd', 'alexandro@mail.ru', 'USER'),
	   ('archi', 'f748978c8a9ae839b5ef6ebf941ceb8a5ed8af5ef541435f4b6ecab4198097076f1eceec4c51974d2bdc629d35eb844249753f3938d1e570aee41559d5f5ed302f7eb08312191897', 'archibalt@bsuir.by', 'USER'),

INSERT INTO profile(
            user_id, country_id, firstname, surname, secondname, city, sex, birthday)
    VALUES (2, 1, 'Моисеенков', 'Алексей', 'Сергеевич', 'Минск', 'male', '1995-02-16'),
	   (3, 1, 'Велитченко', 'Александр', 'Александрович', 'Минск', 'male', '1995-02-11'),
	   (4, 1, 'Мяцкель', 'Артем', 'Сергеевич', 'Минск', 'male', '1994-12-31'),


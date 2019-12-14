-- Начальный набор данных

--Адреса
  -- Адрес 1
INSERT INTO address
  ( id, city, contry, created, flat, house, modified, region, street)
VALUES
  ( 1, 'Екатеринбург', 'Россия', current_timestamp, '9', '81', current_timestamp, 'Урал','Походная');
  -- Адрес 2
INSERT INTO address
  ( id, city, contry, created, flat, house, modified, region, street)
VALUES
  ( 2, 'Екатеринбург', 'Россия', current_timestamp, '17', '22', current_timestamp, 'Урал','Московская');
  -- Адрекс 3
INSERT INTO address
  ( id, city, contry, created, flat, house, modified, region, street)
VALUES
  ( 3, 'Нижний Тагил', 'Россия', current_timestamp, '3', '1', current_timestamp, 'Урал','Уральская');

-- Покупатели
  -- Покупатель 1
INSERT INTO customer
  ( id, actual_address_id, first_name, last_name, middle_name, registred_address_id, sex)
VALUES
  (nextval('hibernate_sequence'), 2, 'Владимир', 'Шахрин', 'Олегович', 3, 'MALE');
  -- Покупатель 2
INSERT INTO customer
  ( id, actual_address_id, first_name, last_name, middle_name, registred_address_id, sex)
VALUES
  ( nextval('hibernate_sequence'), 1, 'Зомбаков', 'Валерий', 'Генадьевич', 2, 'MALE');
  -- Покупатель 4
INSERT INTO customer
  ( id, actual_address_id, first_name, last_name, middle_name, registred_address_id, sex)
VALUES (nextval('hibernate_sequence'), 3, 'Илья', 'Китаев', 'Петрович', 1, 'MALE');
  -- Покупатель 3
INSERT INTO customer
  ( id, actual_address_id, first_name, last_name, middle_name, registred_address_id, sex)
VALUES
  (nextval('hibernate_sequence'), 2, 'Августа', 'Пухова', 'Андреевна', 1, 'FEMALE');

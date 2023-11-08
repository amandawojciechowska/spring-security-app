INSERT INTO producer (name)
VALUES ('Hagi'),
       ('Veoli'),
       ('Tolpa'),
       ('Ziaja'),
       ('Bioderma'),
       ('Kiehls'),
       ('Mokosh'),
       ('BasicLab'),
       ('Yope'),
       ('NaturalME');

INSERT INTO product (name, producer_id)
VALUES ('hydrolat rozmaryn', 10),
       ('hydrolat zielona herbata', 10),
       ('hydrolat melisa', 10),
       ('hydrolat aloes', 10),
       ('hydrolat czystek', 10),
       ('hydrolat roza i kaktus', 9),
       ('balsam melon z ogorkiem', 7),
       ('balsam zielona kawa z tabaka', 7),
       ('balsam zurawina', 7),
       ('balsam czekolada z wisnia', 7),
       ('zel figa wloska', 4),
       ('zel sloneczna pigwa', 7),
       ('zel czerwona porzeczka', 7),
       ('zel ksiezycowa pitaja', 7),
       ('zel mieta herbaciana', 7),
       ('zel kozie mleko', 7),
       ('zel', 4),
       ('zel oliwkowy', 7),
       ('zel kokos i sol morska', 9),
       ('zel roza i kadzidlowiec', 9),
       ('zel rumianek i pokrzywa', 9),
       ('taki sobie zel', 1);

INSERT INTO cart (username)
VALUES ('user');

INSERT INTO cart_item (cart_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (1, 3, 7);
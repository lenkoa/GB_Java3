CREATE TABLE IF NOT EXISTS goods(
    good_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    good_name TEXT NOT NULL,
    good_price INTEGER NOT NULL
    );

DELETE FROM goods WHERE id > 0;

INSERT INTO goods (good_id, good_name, good_price) VALUES (?, ?, ?);

SELECT good_price FROM goods WHERE good_name LIKE '?';

UPDATE goods SET good_price = ? WHERE good_name LIKE '?';

SELECT good_name, good_price FROM goods WHERE good_price BETWEEN ? AND ?;
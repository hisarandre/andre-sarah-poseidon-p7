
insert into Users(fullname, username, password, role)
values("Administrator", "admin", "$2a$12$elHC5dQFY88eVuOmgUlmmuQ2cVx6B7cj3e011WUYeC9phSPyYKBje", "ADMIN"),
      ("User", "user", "$2a$12$elHC5dQFY88eVuOmgUlmmuQ2cVx6B7cj3e011WUYeC9phSPyYKBje", "USER");

INSERT INTO curvepoint (curve_id, as_of_date, term, value, creation_date)
VALUES (1, '2023-05-19 12:00:00', 1.5, 0.025, '2023-05-19 12:00:00'),
       (2, '2023-05-19 12:00:00', 2.0, 0.035, '2023-05-19 12:00:00'),
       (3, '2023-05-19 12:00:00', 2.5, 0.045, '2023-05-19 12:00:00');


INSERT INTO bidlist (bidlist_id, account, type , bid_quantity)
VALUES (1, 'Account Name', 'Account Type', 10),
       (2, 'Account Name', 'Account Type', 10),
       (3, 'Account Name', 'Account Type', 10);


INSERT INTO rating (Id, moodys_rating, sand_p_rating , fitch_rating, order_number)
VALUES (1, 'Moody rating', 'Sand p', "fitch rating", 2),
       (2, 'Moody rating', 'Sand p', "fitch rating", 3),
       (3, 'Moody rating', 'Sand p', "fitch rating", 4);


INSERT INTO rulename (Id, name, description , json, template, sql_str, sql_part)
VALUES (1, 'name', 'description', 'json', 'template', 'sqlstr', 'sqlpart');

INSERT INTO trade (
  account,
  type,
  buy_quantity,
  buy_price,
  trade_date,
  security,
  status,
  trader,
  benchmark,
  book,
  creation_name,
  creation_date,
  deal_name,
  deal_type,
  source_list_id,
  side
) VALUES (
  'Account123',
  'Buy',
  100.0,
  50.0,
  '2023-05-22 10:30:00',
  'AAPL',
  'Pending',
  'John Doe',
  'NASDAQ',
  'Tech',
  'Trade1',
  '2023-05-22 10:30:00',
  'Deal1',
  'Stock',
  'List123',
  'Long'
);

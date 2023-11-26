-- currency_dictionaries test data
INSERT INTO currency_dictionaries (pk, gen_date, mod_date, version, code, currency, minor_unit, numeric_code, origin_country)
VALUES (1, '2023-11-26 01:47:12.000000', '2023-11-26 01:47:13.000000', 0, 'EUR', 'Euro', 2, '123', 'BELGIA');
INSERT INTO currency_dictionaries (pk, gen_date, mod_date, version, code, currency, minor_unit, numeric_code, origin_country)
VALUES (2, '2023-11-26 02:02:48.000000', '2023-11-26 02:02:50.000000', 0, 'USD', 'US Dollar', 2, '840', 'USA');
INSERT INTO currency_dictionaries (pk, gen_date, mod_date, version, code, currency, minor_unit, numeric_code, origin_country)
VALUES (3, '2023-11-26 02:04:05.000000', '2023-11-26 02:04:08.000000', 0, 'RON', 'Romanian Leu', 2, '946', 'ROMANIA');
INSERT INTO currency_dictionaries (pk, gen_date, mod_date, version, code, currency, minor_unit, numeric_code, origin_country)
VALUES (4, '2023-11-26 02:06:32.000000', '2023-11-26 02:06:34.000000', 0, 'GBP', 'Pound Sterling', 2, '826', 'UNITED KINGDOM OF GREAT BRITAIN AND NORTHERN IRELAND (THE)');
INSERT INTO currency_dictionaries (pk, gen_date, mod_date, version, code, currency, minor_unit, numeric_code, origin_country)
VALUES (5, '2023-11-26 16:14:49.000000', '2023-11-26 16:14:51.000000', 0, 'MDL', 'Moldavian Leu', 2, '777', 'MOLDOVA (REPUBLIC OF)');

-- foreign exchange office test data
INSERT INTO foreign_exchange_office (pk, gen_date, mod_date, version, office_name)
VALUES (1, '2023-11-26 13:18:29.000000', '2023-11-26 13:18:32.000000', 0, 'ExchangeCentru');
INSERT INTO foreign_exchange_office (pk, gen_date, mod_date, version, office_name)
VALUES (2, '2023-11-26 18:51:54.000000', '2023-11-26 18:51:56.000000', 0, 'ExchangeCiocana');

-- operators test data
INSERT INTO operators (pk, gen_date, mod_date, version, first_name, last_name, foreign_exchange_office_id)
VALUES (2, '2023-11-26 18:51:08.000000', '2023-11-26 18:51:09.000000', 0, 'Foo', 'Foo', 2);
INSERT INTO operators (pk, gen_date, mod_date, version, first_name, last_name, foreign_exchange_office_id)
VALUES (1, '2023-11-26 13:19:04.000000', '2023-11-26 13:19:06.000000', 0, 'Tom', 'Tomphson', 1);

-- exchange rates test data
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (1, '2023-11-26 02:02:19.812167', '2023-11-26 02:02:19.812167', 0, 20.000, 'EUR', 1, 1);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (2, '2023-11-26 02:13:11.601150', '2023-11-26 02:13:11.601150', 0, 18.000, 'USD', 1, 2);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (3, '2023-11-26 18:38:32.000000', '2023-11-26 18:38:34.000000', 0, 23.000, 'GBP', 2, 4);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (4, '2023-11-26 18:40:34.000000', '2023-11-26 18:40:37.000000', 0, 3.900, 'RON', 2, 3);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (251, '2023-11-26 19:25:18.314725', '2023-11-26 19:25:18.314725', 0, 21.700, 'RON', 1, 3);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (301, '2023-11-26 19:25:28.068205', '2023-11-26 19:25:28.068205', 0, 21.700, 'GBP', 1, 4);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (351, '2023-11-26 19:25:33.344742', '2023-11-26 19:25:33.344742', 0, 21.700, 'EUR', 1, 1);
INSERT INTO exchange_rates (pk, gen_date, mod_date, version, conversion_rate, currency_code, rate, currency_id)
VALUES (401, '2023-11-26 19:25:37.454812', '2023-11-26 19:25:37.454812', 0, 21.700, 'USD', 1, 2);

-- cash test data
INSERT INTO cash (pk, gen_date, mod_date, version, amount, currency_code, foreign_exchange_office_id, operator_id)
VALUES (501, '2023-11-26 14:02:37.737790', '2023-11-26 14:02:37.737790', 0, 101, 'EUR', 1, 1);
INSERT INTO cash (pk, gen_date, mod_date, version, amount, currency_code, foreign_exchange_office_id, operator_id)
VALUES (451, '2023-11-26 13:58:51.972428', '2023-11-26 13:58:51.972428', 0, 101, 'USD', 1, 1);

-- currency exchange test data
INSERT INTO currency_exchange (pk, gen_date, mod_date, version, received_amount, released_amount, source_currency_code, target_currency_code, exchange_rate_id, operator_id)
VALUES (1, '2023-11-26 18:49:13.357215', '2023-11-26 18:49:13.357215', 0, 1500.000, 17250.000, 'EUR', 'MDL', 1, 1);
INSERT INTO currency_exchange (pk, gen_date, mod_date, version, received_amount, released_amount, source_currency_code, target_currency_code, exchange_rate_id, operator_id)
VALUES (51, '2023-11-26 19:00:11.629885', '2023-11-26 19:00:11.629885', 0, 1500.000, 17250.000, 'EUR', 'MDL', 1, 1);

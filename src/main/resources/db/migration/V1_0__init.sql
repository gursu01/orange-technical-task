-- Create schema exchange if not exists
CREATE SCHEMA IF NOT EXISTS exchange;

-- Create sequence for generic ID generation
CREATE SEQUENCE cash_seq START 1 INCREMENT 1;
CREATE SEQUENCE currency_dictionaries_seq START 1 INCREMENT 1;
CREATE SEQUENCE currency_exchange_seq START 1 INCREMENT 1;
CREATE SEQUENCE exchange_rates_seq START 1 INCREMENT 1;
CREATE SEQUENCE foreign_exchange_office_seq START 1 INCREMENT 1;
CREATE SEQUENCE operator_report_seq START 1 INCREMENT 1;
CREATE SEQUENCE operators_seq START 1 INCREMENT 1;

-- Create table for cash
CREATE TABLE cash (
                      pk BIGINT NOT NULL DEFAULT nextval('cash_seq'),
                      gen_date TIMESTAMP NOT NULL,
                      mod_date TIMESTAMP NOT NULL,
                      version INT NOT NULL,
                      ammount NUMERIC(12, 3) NOT NULL,
                      currency_code VARCHAR(255) NOT NULL,
                      foreign_exchange_office_id BIGINT,
                      operator_id BIGINT,
                      PRIMARY KEY (pk)
);

-- Create table for currency dictionaries
CREATE TABLE currency_dictionaries (
                                       pk BIGINT NOT NULL DEFAULT nextval('currency_dictionaries_seq'),
                                       gen_date TIMESTAMP NOT NULL,
                                       mod_date TIMESTAMP NOT NULL,
                                       version INT NOT NULL,
                                       code VARCHAR(255) NOT NULL,
                                       currency VARCHAR(255) NOT NULL,
                                       minor_unit INT NOT NULL,
                                       numeric_code VARCHAR(255) NOT NULL,
                                       origin_country VARCHAR(255) NOT NULL,
                                       PRIMARY KEY (pk)
);

-- Create table for currency exchange
CREATE TABLE currency_exchange (
                                   pk BIGINT NOT NULL DEFAULT nextval('currency_exchange_seq'),
                                   gen_date TIMESTAMP NOT NULL,
                                   mod_date TIMESTAMP NOT NULL,
                                   version INT NOT NULL,
                                   received_ammount NUMERIC(12, 3) NOT NULL,
                                   released_ammount NUMERIC(12, 3) NOT NULL,
                                   source_currency_code VARCHAR(255),
                                   target_currency_code VARCHAR(255),
                                   rate_id BIGINT NOT NULL,
                                   operator_id BIGINT NOT NULL,
                                   PRIMARY KEY (pk)
);

-- Create table for exchange rates
CREATE TABLE exchange_rates (
                                pk BIGINT NOT NULL DEFAULT nextval('exchange_rates_seq'),
                                gen_date TIMESTAMP NOT NULL,
                                mod_date TIMESTAMP NOT NULL,
                                version INT NOT NULL,
                                conversion_rate NUMERIC(12, 3) NOT NULL,
                                currency_code VARCHAR(255) NOT NULL,
                                rate INT NOT NULL,
                                currency_id BIGINT NOT NULL,
                                PRIMARY KEY (pk)
);

-- Create table for foreign exchange office
CREATE TABLE foreign_exchange_office (
                                         pk BIGINT NOT NULL DEFAULT nextval('foreign_exchange_office_seq'),
                                         gen_date TIMESTAMP NOT NULL,
                                         mod_date TIMESTAMP NOT NULL,
                                         version INT NOT NULL,
                                         office_name VARCHAR(255),
                                         PRIMARY KEY (pk)
);

-- Create table for operator report
CREATE TABLE operator_report (
                                 pk BIGINT NOT NULL DEFAULT nextval('operator_report_seq'),
                                 gen_date TIMESTAMP NOT NULL,
                                 mod_date TIMESTAMP NOT NULL,
                                 version INT NOT NULL,
                                 currency_code VARCHAR(255),
                                 number_of_transactions INT,
                                 report_type VARCHAR(255),
                                 operator_id BIGINT NOT NULL,
                                 PRIMARY KEY (pk)
);

-- Create table for operators
CREATE TABLE operators (
                           pk BIGINT NOT NULL DEFAULT nextval('operators_seq'),
                           gen_date TIMESTAMP NOT NULL,
                           mod_date TIMESTAMP NOT NULL,
                           version INT NOT NULL,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           foreign_exchange_office_id BIGINT NOT NULL,
                           PRIMARY KEY (pk)
);

-- Add foreign key constraint for foreign_exchange_office_id in cash
ALTER TABLE cash
    ADD CONSTRAINT FK_cash_foreign_exchange_office_id
        FOREIGN KEY (foreign_exchange_office_id)
            REFERENCES foreign_exchange_office;

-- Add foreign key constraint for operator_id in cash
ALTER TABLE cash
    ADD CONSTRAINT FK_cash_operator_id
        FOREIGN KEY (operator_id)
            REFERENCES operators;

-- Add foreign key constraint for rate_id in currency_exchange
ALTER TABLE currency_exchange
    ADD CONSTRAINT FK_currency_exchange_rate_id
        FOREIGN KEY (rate_id)
            REFERENCES exchange_rates;

-- Add foreign key constraint for operator_id in currency_exchange
ALTER TABLE currency_exchange
    ADD CONSTRAINT FK_currency_exchange_operator_id
        FOREIGN KEY (operator_id)
            REFERENCES operators;

-- Add foreign key constraint for currency_id in exchange_rates
ALTER TABLE exchange_rates
    ADD CONSTRAINT FK_exchange_rates_currency_id
        FOREIGN KEY (currency_id)
            REFERENCES currency_dictionaries;

-- Add foreign key constraint for operator_id in operator_report
ALTER TABLE operator_report
    ADD CONSTRAINT FK_operator_report_operator_id
        FOREIGN KEY (operator_id)
            REFERENCES operators;

-- Add foreign key constraint for foreign_exchange_office_id in operators
ALTER TABLE operators
    ADD CONSTRAINT FK_operators_foreign_exchange_office_id
        FOREIGN KEY (foreign_exchange_office_id)
            REFERENCES foreign_exchange_office;

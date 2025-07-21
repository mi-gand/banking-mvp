--from initDB, executes 1 time before postgres container
CREATE SCHEMA IF NOT EXISTS deal;
DROP TABLE IF EXISTS deal.statement;
DROP TABLE IF EXISTS deal.credit;
DROP TABLE IF EXISTS deal.client;
DROP SEQUENCE IF EXISTS global_seq;

CREATE TABLE deal.client (
                        client_id         UUID PRIMARY KEY,
                        last_name         VARCHAR,
                        first_name        VARCHAR,
                        middle_name       VARCHAR,
                        birth_date        DATE,
                        email             VARCHAR,
                        gender            VARCHAR,
                        marital_status    VARCHAR,
                        dependent_amount  INTEGER DEFAULT 0,
                        passport_id       JSONB,
                        employment_id     JSONB,
                        account_number    VARCHAR
);

CREATE TABLE deal.credit (
                        credit_id            UUID PRIMARY KEY,
                        amount               DECIMAL,
                        term                 INTEGER,
                        monthly_payment      DECIMAL,
                        rate                 DECIMAL,
                        psk                  DECIMAL,
                        payment_schedule     JSONB,
                        insurance_enabled    BOOLEAN DEFAULT FALSE,
                        salary_client        BOOLEAN DEFAULT FALSE,
                        credit_status        VARCHAR
);

CREATE TABLE deal.statement (
                           statement_id     UUID PRIMARY KEY,
                           client_id        UUID,
                           credit_id        UUID,
                           status           VARCHAR,
                           creation_date    TIMESTAMP,
                           applied_offer    JSONB,
                           sign_date        TIMESTAMP,
                           status_history   JSONB CHECK (jsonb_typeof(status_history) = 'array')
    /*,
    FOREIGN KEY (client_id) REFERENCES client (client_id),
    FOREIGN KEY (credit_id) REFERENCES credit (credit_id)*/
);
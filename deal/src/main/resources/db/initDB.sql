DROP TABLE IF EXISTS statement;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS client;
DROP SEQUENCE IF EXISTS global_seq;

CREATE TABLE client (
                        client_id         UUID PRIMARY KEY,
                        last_name         VARCHAR NOT NULL,
                        first_name        VARCHAR NOT NULL,
                        middle_name       VARCHAR,
                        birth_date        DATE NOT NULL,
                        email             VARCHAR NOT NULL,
                        gender            VARCHAR NOT NULL,
                        marital_status    VARCHAR NOT NULL,
                        dependent_amount  INTEGER DEFAULT 0 NOT NULL,
                        passport          JSONB,
                        employment        JSONB,
                        account_number    VARCHAR NOT NULL
);

CREATE TABLE credit (
                        credit_id            UUID PRIMARY KEY,
                        amount               DECIMAL NOT NULL,
                        term                 INTEGER NOT NULL,
                        monthly_payment      DECIMAL NOT NULL,
                        rate                 DECIMAL NOT NULL,
                        psk                  DECIMAL NOT NULL,
                        payment_schedule     JSONB,
                        insurance_enabled    BOOLEAN DEFAULT FALSE NOT NULL,
                        salary_client        BOOLEAN DEFAULT FALSE NOT NULL,
                        credit_status        VARCHAR NOT NULL
);

CREATE TABLE statement (
                           statement_id     UUID PRIMARY KEY,
                           client_id        UUID NOT NULL,
                           credit_id        UUID NOT NULL,
                           status           VARCHAR NOT NULL,
                           creation_date    TIMESTAMP NOT NULL,
                           applied_offer    JSONB,
                           sign_date        TIMESTAMP,
                           ses_code         VARCHAR,
                           status_history   JSONB/*,
                           FOREIGN KEY (client_id) REFERENCES client (client_id),
                           FOREIGN KEY (credit_id) REFERENCES credit (credit_id)*/
);
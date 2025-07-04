DROP TABLE IF EXISTS statement;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS client;
DROP SEQUENCE IF EXISTS global_seq;

CREATE TABLE client (
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

CREATE TABLE credit (
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

CREATE TABLE statement (
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



/*CREATE TABLE client (
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
                           status_history   JSONB,
                           FOREIGN KEY (client_id) REFERENCES client (client_id),
                           FOREIGN KEY (credit_id) REFERENCES credit (credit_id)
);*/
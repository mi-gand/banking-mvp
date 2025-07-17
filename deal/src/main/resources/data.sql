--from populateDB, executes 1 time before postgres container

TRUNCATE TABLE deal.client CASCADE ;
TRUNCATE TABLE deal.credit CASCADE ;
TRUNCATE TABLE deal.statement CASCADE ;

--Statements
INSERT INTO deal.statement (statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer, sign_date)
VALUES ('aa111111-1111-1111-1111-111111111111', 'bb111111-1111-1111-1111-111111111111', '2025-06-24', 'cc111111-1111-1111-1111-111111111111',
        'PREAPPROVAL', '[{"time":"2025-06-24T00:00:00","status":"PREAPPROVAL","changeType":"AUTOMATIC"}]', '{
    "rate": 26.0,
    "term": 12,
    "statementId": "aa111111-1111-1111-1111-111111111111",
    "totalAmount": 673180.79,
    "salaryClient": true,
    "monthlyPayment": 56098.40,
    "requestedAmount": 500000,
    "insuranceEnabled": true
  }', null),
       ('aa222222-2222-2222-2222-222222222222', 'bb222222-2222-2222-2222-222222222222', '2025-06-24', 'cc222222-2222-2222-2222-222222222222',
        'PREAPPROVAL', '[{"time":"2025-06-24T00:00:00","status":"PREAPPROVAL","changeType":"AUTOMATIC"}]', '{
         "rate": 27.0,
         "term": 12,
         "statementId": "aa222222-2222-2222-2222-222222222222",
         "totalAmount": 676104.37,
         "salaryClient": false,
         "monthlyPayment": 56342.03,
         "requestedAmount": 500000,
         "insuranceEnabled": true
       }', null),
       ('aa333333-3333-3333-3333-333333333333', 'bb333333-3333-3333-3333-333333333333', '2025-06-24', 'cc333333-3333-3333-3333-333333333333',
        'PREAPPROVAL', '[{"time":"2025-06-24T00:00:00","status":"PREAPPROVAL","changeType":"AUTOMATIC"}]', '{
         "rate": 29.0,
         "term": 12,
         "statementId": "aa333333-3333-3333-3333-333333333333",
         "totalAmount": 581975.39,
         "salaryClient": true,
         "monthlyPayment": 48497.95,
         "requestedAmount": 500000,
         "insuranceEnabled": false
       }', null);


--Clients
INSERT INTO deal.client (client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
                         dependent_amount, passport_id, employment_id, account_number)
VALUES ('bb111111-1111-1111-1111-111111111111', 'Ivanov', 'Ivan', 'Ivanovich', '1985-07-15', 'ivan@ya.ru', 'MALE', 'MARRIED', 2, '{
  "number": "567890",
  "series": "1234",
  "issue_date": "2005-06-20",
  "issue_branch": "MVD"
}', '{
  "salary": 80000,
  "status": "EMPLOYED",
  "position": "WORKER",
  "employerInn": "1234567890",
  "workExperienceTotal": 15,
  "workExperienceCurrent": 5
}', '40817810099910004312'),
       ('bb222222-2222-2222-2222-222222222222', 'Petrov', 'Petr', 'Petrovich', '1990-08-10', 'petr@ya.ru', 'MALE', 'SINGLE', 0, '{
         "number": "123456",
         "series": "5678",
         "issue_date": "2010-09-15",
         "issue_branch": "MVD"
       }', '{
         "salary": 120000,
         "status": "EMPLOYED",
         "position": "MANAGER",
         "employerInn": "0987654321",
         "workExperienceTotal": 10,
         "workExperienceCurrent": 3
       }', '40817810099910004313'),
       ('bb333333-3333-3333-3333-333333333333', 'Sidorova', 'Elena', 'Sergeevna', '1995-03-22', 'elena@ya.ru', 'FEMALE', 'DIVORCED', 1, '{
         "number": "789012",
         "series": "9012",
         "issue_date": "2015-05-12",
         "issue_branch": "MVD"
       }', '{
         "salary": 90000,
         "status": "EMPLOYED",
         "position": "SPECIALIST",
         "employerInn": "1122334455",
         "workExperienceTotal": 7,
         "workExperienceCurrent": 2
       }', '40817810099910004314');

-- Credits
INSERT INTO deal.credit (credit_id, amount, term, monthly_payment, rate, psk, payment_schedule, salary_client, credit_status)
VALUES ('cc111111-1111-1111-1111-111111111111', 500000, 12, 56098.40, 26.0, 673180.79, '{
  "number": 1,
  "date": "2025-07-01",
  "totalPayment": 56098.40,
  "interestPayment": 10833.33,
  "debtPayment": 45265.07,
  "remainingDebt": 454734.93
}', true, 'CALCULATED'),
       ('cc222222-2222-2222-2222-222222222222', 500000, 12, 56342.03, 27.0, 676104.37, '{
         "number": 1,
         "date": "2025-07-01",
         "totalPayment": 56342.03,
         "interestPayment": 11250.00,
         "debtPayment": 45092.03,
         "remainingDebt": 454907.97
       }', false, 'CALCULATED'),
       ('cc333333-3333-3333-3333-333333333333', 500000, 12, 48497.95, 29.0, 581975.39, '{
         "number": 1,
         "date": "2025-07-01",
         "totalPayment": 48497.95,
         "interestPayment": 12083.33,
         "debtPayment": 36414.62,
         "remainingDebt": 463585.38
       }', true, 'CALCULATED');


DELETE
FROM deal.client;
DELETE
FROM deal.credit;
DELETE
FROM deal.statement;


INSERT INTO deal.client (client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
                         dependent_amount, passport_id, employment_id, account_number)
VALUES ('8c6da484-2774-4444-a59d-f2111b9c9673', 'Ivanov', 'Ivan', 'Ivanovich', null, 'ivan@ya.ru', null, null, null, '{
  "number": "567890",
  "series": "1234",
  "issue_date": null,
  "issue_branch": null
}', '{
  "salary": null,
  "status": null,
  "position": null,
  "employerInn": null,
  "workExperienceTotal": null,
  "workExperienceCurrent": null
}', null);
INSERT INTO deal.client (client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
                         dependent_amount, passport_id, employment_id, account_number)
VALUES ('da64ad78-4faf-4e54-a485-f49962580a48', 'Ivanov', 'Ivan', 'Ivanovich', null, 'ivan@ya.ru', null, null, null, '{
  "number": "567890",
  "series": "1234",
  "issue_date": null,
  "issue_branch": null
}', '{
  "salary": null,
  "status": null,
  "position": null,
  "employerInn": null,
  "workExperienceTotal": null,
  "workExperienceCurrent": null
}', null);
INSERT INTO deal.client (client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
                         dependent_amount, passport_id, employment_id, account_number)
VALUES ('f86039a5-1c17-4821-934b-827689314c57', 'Ivanov', 'Ivan', 'Ivanovich', null, 'ivan@ya.ru', null, null, null, '{
  "number": "567890",
  "series": "1234",
  "issue_date": null,
  "issue_branch": null
}', '{
  "salary": null,
  "status": null,
  "position": null,
  "employerInn": null,
  "workExperienceTotal": null,
  "workExperienceCurrent": null
}', null);
INSERT INTO deal.statement (statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer,
                            sign_date)
VALUES ('56d14d61-b584-4715-ad42-2cdb0fc71b45', '8c6da484-2774-4444-a59d-f2111b9c9673', '2025-06-24 00:00:00', null,
        'PREAPPROVAL', '{
    "time": [
      2025,
      6,
      24
    ],
    "status": "PREAPPROVAL",
    "changeType": "AUTOMATIC"
  }', '[
    {
      "rate": 26.0,
      "term": 12,
      "statementId": "56d14d61-b584-4715-ad42-2cdb0fc71b45",
      "totalAmount": 673180.79,
      "salaryClient": true,
      "monthlyPayment": 56098.40,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 27.0,
      "term": 12,
      "statementId": "56d14d61-b584-4715-ad42-2cdb0fc71b45",
      "totalAmount": 676104.37,
      "salaryClient": false,
      "monthlyPayment": 56342.03,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 29.0,
      "term": 12,
      "statementId": "56d14d61-b584-4715-ad42-2cdb0fc71b45",
      "totalAmount": 581975.39,
      "salaryClient": true,
      "monthlyPayment": 48497.95,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    },
    {
      "rate": 30.0,
      "term": 12,
      "statementId": "56d14d61-b584-4715-ad42-2cdb0fc71b45",
      "totalAmount": 584922.76,
      "salaryClient": false,
      "monthlyPayment": 48743.56,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    }
  ]', null);
INSERT INTO deal.statement (statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer,
                            sign_date)
VALUES ('7508a13e-e2be-4cc8-8e4f-92d5f15332fb', 'da64ad78-4faf-4e54-a485-f49962580a48', '2025-06-24 00:00:00', null,
        'PREAPPROVAL', '{
    "time": [
      2025,
      6,
      24
    ],
    "status": "PREAPPROVAL",
    "changeType": "AUTOMATIC"
  }', '[
    {
      "rate": 26.0,
      "term": 12,
      "statementId": "7508a13e-e2be-4cc8-8e4f-92d5f15332fb",
      "totalAmount": 673180.79,
      "salaryClient": true,
      "monthlyPayment": 56098.40,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 27.0,
      "term": 12,
      "statementId": "7508a13e-e2be-4cc8-8e4f-92d5f15332fb",
      "totalAmount": 676104.37,
      "salaryClient": false,
      "monthlyPayment": 56342.03,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 29.0,
      "term": 12,
      "statementId": "7508a13e-e2be-4cc8-8e4f-92d5f15332fb",
      "totalAmount": 581975.39,
      "salaryClient": true,
      "monthlyPayment": 48497.95,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    },
    {
      "rate": 30.0,
      "term": 12,
      "statementId": "7508a13e-e2be-4cc8-8e4f-92d5f15332fb",
      "totalAmount": 584922.76,
      "salaryClient": false,
      "monthlyPayment": 48743.56,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    }
  ]', null);
INSERT INTO deal.statement (statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer,
                            sign_date)
VALUES ('c6574de5-dacf-4d9f-bda7-3b13003976d2', 'f86039a5-1c17-4821-934b-827689314c57', '2025-06-24 00:00:00', null,
        'PREAPPROVAL', '{
    "time": [
      2025,
      6,
      24
    ],
    "status": "PREAPPROVAL",
    "changeType": "AUTOMATIC"
  }', '[
    {
      "rate": 26.0,
      "term": 12,
      "statementId": "c6574de5-dacf-4d9f-bda7-3b13003976d2",
      "totalAmount": 673180.79,
      "salaryClient": true,
      "monthlyPayment": 56098.40,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 27.0,
      "term": 12,
      "statementId": "c6574de5-dacf-4d9f-bda7-3b13003976d2",
      "totalAmount": 676104.37,
      "salaryClient": false,
      "monthlyPayment": 56342.03,
      "requestedAmount": 500000,
      "insuranceEnabled": true
    },
    {
      "rate": 29.0,
      "term": 12,
      "statementId": "c6574de5-dacf-4d9f-bda7-3b13003976d2",
      "totalAmount": 581975.39,
      "salaryClient": true,
      "monthlyPayment": 48497.95,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    },
    {
      "rate": 30.0,
      "term": 12,
      "statementId": "c6574de5-dacf-4d9f-bda7-3b13003976d2",
      "totalAmount": 584922.76,
      "salaryClient": false,
      "monthlyPayment": 48743.56,
      "requestedAmount": 500000,
      "insuranceEnabled": false
    }
  ]', null);
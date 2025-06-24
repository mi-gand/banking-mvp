DELETE FROM deal.client;
DELETE FROM deal.credit;
DELETE FROM deal.statement;


INSERT INTO deal.client (
    client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
    dependent_amount, passport_id, employment_id, account_number
) VALUES (
             '799d7d68-b0f5-44e5-a260-2649ab158868', 'Ivanov', 'Ivan', 'Ivanovich',
             null, 'ivan@ya.ru', null, null,
             null,
             '{"number": "567890", "series": "1234", "issue_date": null, "issue_branch": null}', '{"salary": null, "status": null, "position": null, "employerInn": null, "workExperienceTotal": null, "workExperienceCurrent": null}', null
         );
INSERT INTO deal.statement (
    statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer, sign_date
) VALUES (
             '15ede78f-13b2-4df6-b20a-d5f05eeee2ae', '799d7d68-b0f5-44e5-a260-2649ab158868', '2025-06-24 00:00:00',
             null, 'PREAPPROVAL',
             '{"time": [2025, 6, 24], "status": "PREAPPROVAL", "changeType": "AUTOMATIC"}', null , null
         );

INSERT INTO deal.client (
    client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
    dependent_amount, passport_id, employment_id, account_number
) VALUES (
             '7a327dda-5eda-41f7-9427-84d2d4dd671d', 'Ivanov', 'Ivan', 'Ivanovich',
             null, 'ivan@ya.ru', null, null,
             null,
             '{"number": "567890", "series": "1234", "issue_date": null, "issue_branch": null}', '{"salary": null, "status": null, "position": null, "employerInn": null, "workExperienceTotal": null, "workExperienceCurrent": null}', null
         );
INSERT INTO deal.statement (
    statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer, sign_date
) VALUES (
             'd97361d2-713a-4394-bb6c-025291a250b3', '7a327dda-5eda-41f7-9427-84d2d4dd671d', '2025-06-24 00:00:00',
             null, 'PREAPPROVAL',
             '{"time": [2025, 6, 24], "status": "PREAPPROVAL", "changeType": "AUTOMATIC"}', null, null
         );

INSERT INTO deal.client (
    client_id, last_name, first_name, middle_name, birth_date, email, gender, marital_status,
    dependent_amount, passport_id, employment_id, account_number
) VALUES (
             '3047320a-7725-436a-9cff-63f2928c0c38', 'Ivanov', 'Ivan', 'Ivanovich',
             null, 'ivan@ya.ru', null, null,
             null,
             '{"number": "567890", "series": "1234", "issue_date": null, "issue_branch": null}', '{"salary": null, "status": null, "position": null, "employerInn": null, "workExperienceTotal": null, "workExperienceCurrent": null}', null
         );
INSERT INTO deal.statement (
    statement_id, client_id, creation_date, credit_id, status, status_history, applied_offer, sign_date
) VALUES (
             'dbfa6325-0802-44d1-8cf6-89d7bb206734', '3047320a-7725-436a-9cff-63f2928c0c38', '2025-06-24 00:00:00',
             null, 'PREAPPROVAL',
             '{"time": [2025, 6, 24], "status": "PREAPPROVAL", "changeType": "AUTOMATIC"}', null, null
         );
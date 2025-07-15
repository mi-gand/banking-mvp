package ru.outofmemoryguru.statement.testdata;

public class JsonTestData {

    public static final String OFFER1_JSON = """
            {
                "amount": 500000,
                "term": 12,
                "firstName": "Ivan",
                "lastName": "Ivanov",
                "middleName": "Ivanovich",
                "email": "ivan@ya.ru",
                "birthdate": "2000-01-01",
                "passportSeries": "1234",
                "passportNumber": "567890"
            }
            """;


    public static final String EXPECTED4_OFFERS_JSON = """
            [
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 673180.79,
                "term": 12,
                "monthlyPayment": 56098.40,
                "rate": 26.0,
                "insuranceEnabled": true,
                "salaryClient": true
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 676104.37,
                "term": 12,
                "monthlyPayment": 56342.03,
                "rate": 27.0,
                "insuranceEnabled": true,
                "salaryClient": false
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 581975.39,
                "term": 12,
                "monthlyPayment": 48497.95,
                "rate": 29.0,
                "insuranceEnabled": false,
                "salaryClient": true
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 584922.76,
                "term": 12,
                "monthlyPayment": 48743.56,
                "rate": 30.0,
                "insuranceEnabled": false,
                "salaryClient": false
              }
            ]
            """;

    public static final String SCORING_DATA_JSON = """
            {
              "amount": 500000,
              "term": 12,
              "firstName": "Ivan",
              "lastName": "Ivanov",
              "middleName": "Ivanovich",
              "gender": "MALE",
              "birthdate": "1985-07-15",
              "passportSeries": "1234",
              "passportNumber": "567890",
              "passportIssueDate": "2005-06-20",
              "passportIssueBranch": "MVD",
              "maritalStatus": "MARRIED",
              "dependentAmount": 2,
              "employment": {
                "salary": 80000,
                "status": "EMPLOYED",
                "position": "WORKER",
                "employerInn": "1234567890",
                "workExperienceTotal": 15,
                "workExperienceCurrent": 5
              },
              "accountNumber": "40817810099910004312",
              "insuranceEnabled": true,
              "salaryClient": true
            }
            """;
}

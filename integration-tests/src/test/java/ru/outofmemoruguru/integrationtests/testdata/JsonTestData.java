package ru.outofmemoruguru.integrationtests.testdata;

public class JsonTestData {

    public static final String OFFER1_JSON = """
            {
                "statementId": "aa111111-1111-1111-1111-111111111111",
                "requestedAmount": 500000,
                "totalAmount": 673180.79,
                "term": 12,
                "monthlyPayment": 56098.40,
                "rate": 26.0,
                "insuranceEnabled": true,
                "salaryClient": true
              }
            """;

    public static final String LOAN_STATEMENT_JSON = """
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
                "statementId": "aa111111-1111-1111-1111-111111111111",
                "requestedAmount": 500000,
                "totalAmount": 673180.79,
                "term": 12,
                "monthlyPayment": 56098.40,
                "rate": 26.0,
                "insuranceEnabled": true,
                "salaryClient": true
              },
              {
                "statementId": "aa111111-1111-1111-1111-111111111111",
                "requestedAmount": 500000,
                "totalAmount": 676104.37,
                "term": 12,
                "monthlyPayment": 56342.03,
                "rate": 27.0,
                "insuranceEnabled": true,
                "salaryClient": false
              },
              {
                "statementId": "aa111111-1111-1111-1111-111111111111",
                "requestedAmount": 500000,
                "totalAmount": 581975.39,
                "term": 12,
                "monthlyPayment": 48497.95,
                "rate": 29.0,
                "insuranceEnabled": false,
                "salaryClient": true
              },
              {
                "statementId": "aa111111-1111-1111-1111-111111111111",
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
}

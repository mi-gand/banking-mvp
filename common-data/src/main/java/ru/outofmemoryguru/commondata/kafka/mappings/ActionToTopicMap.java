package ru.outofmemoryguru.commondata.kafka.mappings;

import java.util.Map;

public class ActionToTopicMap {
    public static final String SEND_DOCUMENTS = "send-documents";
    public static final String CREATE_DOCUMENTS = "create-documents";
    public static final String CREDIT_ISSUED = "credit-issued";
    public static final String FINISH_REGISTRATION = "finish-registration";
    public static final String SEND_SES = "send-ses";
    public static final String STATEMENT_DENIED = "statement-denied";

    private static final Map<String, String> ACTION_TO_TOPIC = Map.of(
            "send", SEND_DOCUMENTS,
            "sign", CREATE_DOCUMENTS,
            "code", CREDIT_ISSUED
            //тут еще 3 штуки надо
    );

    private ActionToTopicMap() {
    }

    public static Map<String, String> getActionsForTopics() {
        return ACTION_TO_TOPIC;
    }

    public static String getTopic(String action) {
        return ACTION_TO_TOPIC.get(action);
    }

}

package com.thetechmaddy.authservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetechmaddy.authservice.messaging.models.UserDetail;
import com.thetechmaddy.authservice.services.UserSyncService;
import com.thetechmaddy.authservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSubscriber {

    private final ObjectMapper objectMapper;
    private final UserSyncService userSyncService;

    @Autowired
    public KafkaSubscriber(ObjectMapper objectMapper, UserSyncService userSyncService) {
        this.objectMapper = objectMapper;
        this.userSyncService = userSyncService;
    }

    @KafkaListener(topics = "${messaging.kafka.topic.auth_user_sync}")
    public void listenUserRegistrationTopic(String messagePayload) {
        UserDetail userDetail = JsonUtils.parseJson(objectMapper, messagePayload, UserDetail.class);
        if (userDetail != null) {
            userSyncService.syncUser(userDetail);
        }
    }

}

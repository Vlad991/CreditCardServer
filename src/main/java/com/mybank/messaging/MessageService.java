package com.mybank.messaging;

import com.mybank.dto.EventType;
import com.mybank.dto.Payload;
import com.mybank.dto.SendSumDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@EnableBinding
public class MessageService {

    private BinderAwareChannelResolver resolver;

    public MessageService(BinderAwareChannelResolver resolver){
        this.resolver = resolver;
    }

    @EventListener
    public void sendSumEvent(SendSumDTO sendSumDTO){
        Payload<SendSumDTO> payload = new Payload<>();
        payload.setEvent(EventType.SEND.toString());
        payload.setObjectToSend(sendSumDTO);

        Map<String, String> headers = new HashMap<>();
        headers.put("EventVersion", "v1");
        headers.put("EntityVersion", "v1");

        Message<Payload<SendSumDTO>> message = MessageBuilder
                .withPayload(payload)
                .copyHeaders(headers)
                .build();

        MessageChannel channel = resolver.resolveDestination("send-event-output");
        if(!channel.send(message)){
            log.error("Can not send message.");
        }
    }

    @EventListener
    public void blockCardEvent(String number){
        Payload<String> payload = new Payload<>();
        payload.setEvent(EventType.BLOCK.toString());
        payload.setObjectToSend(number);

        Map<String, String> headers = new HashMap<>();
        headers.put("EventVersion", "v1");
        headers.put("EntityVersion", "v1");

        Message<Payload<String>> message = MessageBuilder
                .withPayload(payload)
                .copyHeaders(headers)
                .build();

        MessageChannel channel = resolver.resolveDestination("block-event-output");
        if(!channel.send(message)){
            log.error("Can not send message.");
        }
    }
}

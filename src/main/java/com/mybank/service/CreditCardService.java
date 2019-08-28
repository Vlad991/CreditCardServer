package com.mybank.service;

import com.mybank.dto.SendSumDTO;
import com.mybank.messaging.MessageService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private ApplicationEventPublisher applicationEventPublisher;

    public CreditCardService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendSum(String senderCardNumber, String receiverCardNumber, int sum) {
        SendSumDTO sendSumDTO = new SendSumDTO();
        sendSumDTO.setSenderCardNumber(senderCardNumber);
        sendSumDTO.setReceiverCardNumber(receiverCardNumber);
        sendSumDTO.setSum(sum);
        applicationEventPublisher.publishEvent(sendSumDTO);
    }

    public void blockCard(String number) {
        applicationEventPublisher.publishEvent(number);
    }
}

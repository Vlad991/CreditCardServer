package com.mybank.service;

import com.mybank.dto.SendSumDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private ApplicationEventPublisher applicationEventPublisher;

    public CreditCardService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendSum(SendSumDTO sendSumDTO) {
        applicationEventPublisher.publishEvent(sendSumDTO);
    }

    public void blockCard(String number) {
        applicationEventPublisher.publishEvent(number);
    }
}

package com.mybank.controller;

import com.mybank.dto.SendSumDTO;
import com.mybank.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/card")
@CrossOrigin
public class CreditCardController {
    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PutMapping(value = "/{number}/send")
    public ResponseEntity sendSumToCreditCard(@PathVariable("number") String senderCardNumber,
                                              @Valid @RequestBody SendSumDTO sendSumDTO) {
        creditCardService.sendSum(sendSumDTO);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/{number}/block")
    public ResponseEntity blockCreditCard(@PathVariable("number") String number) {
        creditCardService.blockCard(number);
        return ResponseEntity.accepted().build();
    }

//    @GetMapping(value = "/{number}/settings")
//    public List<CreditCardDTO> clientCards(@PathVariable("login") String login) {
//        //todo settings
//    }
}

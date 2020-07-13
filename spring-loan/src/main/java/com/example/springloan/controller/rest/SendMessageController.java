package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.SendMessage;
import com.example.springloan.service.SendMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/rest/send-message")
public class SendMessageController {

    private final SendMessageService sendMessageService;

    public SendMessageController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @PostMapping
    public ResponseEntity<SendMessage> create(@RequestBody SendMessage input) {
        SendMessage created = sendMessageService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

}

package com.example.springloan.service;

import com.example.springloan.dao.domain.SendMessage;
import com.example.springloan.dao.repository.SendMessageRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService extends BaseService<SendMessageRepository, SendMessage> {

    public SendMessageService(SendMessageRepository repository) {
        super(repository);
    }

    @Override
    public SendMessage update(SendMessage updating) throws NotFoundException {
        return updating;
    }



}

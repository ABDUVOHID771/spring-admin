package com.example.springloan.service;

import com.example.springloan.dao.domain.LoanRequestHistory;
import com.example.springloan.dao.repository.LoanRequestHistoryRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestHistoryService extends BaseService<LoanRequestHistoryRepository, LoanRequestHistory> {

    public LoanRequestHistoryService(LoanRequestHistoryRepository repository) {
        super(repository);
    }

    @Override
    public LoanRequestHistory update(LoanRequestHistory updating) throws NotFoundException {
        return updating;
    }
}

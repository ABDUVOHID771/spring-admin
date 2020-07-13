package com.example.springloan.service;

import com.example.springloan.dao.domain.LoanRequest;
import com.example.springloan.dao.domain.LoanType;
import com.example.springloan.dao.domain.User;
import com.example.springloan.dao.repository.LoanRequestsRepository;
import com.google.common.base.Strings;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestService extends BaseService<LoanRequestsRepository, LoanRequest> {


    public LoanRequestService(LoanRequestsRepository repository) {
        super(repository);
    }

    @Override
    public LoanRequest update(LoanRequest updating) throws NotFoundException {

        LoanRequest loanRequest = getRepository().findById(updating.getId()).orElseThrow(() -> new NotFoundException("Not Found !"));

        if (updating.getCountingStrategy() != null) {
            loanRequest.setCountingStrategy(updating.getCountingStrategy());
        }
        if (!Strings.isNullOrEmpty(updating.getSalaryCardBin())) {
            loanRequest.setSalaryCardBin(updating.getSalaryCardBin());
        }
        if (!Strings.isNullOrEmpty(updating.getTIN())) {
            loanRequest.setTIN(updating.getTIN());
        }

        if (updating.getRequestedAmount() != 0.0) {
            loanRequest.setRequestedAmount(updating.getRequestedAmount());
        }

        if (updating.getStatus().getValue() >= loanRequest.getStatus().getValue()) {
            loanRequest.setStatus(updating.getStatus());
        }

        LoanRequest updated = getRepository().save(loanRequest);

        return updated;

    }

    public List<LoanRequest> getAllLoanRequestByUser(User user) {
        return getRepository().getAllByUserRequest(user);
    }

    public List<LoanRequest> getByLoanType(LoanType loan) {
        return getRepository().findAllByLoanType(loan);
    }

    public List<LoanRequest> getLoanRequestsByBranch(Long id) {
        return getRepository().findAllByBranchId(id);
    }

}
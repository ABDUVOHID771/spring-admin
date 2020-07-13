package com.example.springloan.service;

import com.example.springloan.dao.domain.UserLoan;
import com.example.springloan.dao.repository.UserLoanRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoanService extends BaseService<UserLoanRepository, UserLoan> {

    public UserLoanService(UserLoanRepository repository) {
        super(repository);
    }

    @Override
    public UserLoan update(UserLoan updating) throws NotFoundException {

        UserLoan userLoan = getRepository().findById(updating.getId()).orElseThrow(() -> new NotFoundException("Not Found !"));

        userLoan.setStatus(updating.isStatus());

        return getRepository().save(userLoan);

    }

    public UserLoan getByUsername(String username) {
        return getRepository().findByUsername(username);
    }
}

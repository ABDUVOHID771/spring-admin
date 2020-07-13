package com.example.springloan.dao.repository;

import com.example.springloan.dao.domain.LoanRequest;
import com.example.springloan.dao.domain.LoanType;
import com.example.springloan.dao.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanRequestsRepository extends BaseRepository<LoanRequest> {

    List<LoanRequest> getAllByUserRequest(User user);

    List<LoanRequest> findAllByLoanType(LoanType loanType);

    List<LoanRequest> findAllByBranchId(Long id);

}

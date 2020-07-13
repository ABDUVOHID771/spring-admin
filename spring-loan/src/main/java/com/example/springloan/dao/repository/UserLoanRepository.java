package com.example.springloan.dao.repository;

import com.example.springloan.dao.domain.UserLoan;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoanRepository extends BaseRepository<UserLoan> {
    UserLoan findByUsername(String username);
}

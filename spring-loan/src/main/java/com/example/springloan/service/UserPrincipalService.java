package com.example.springloan.service;

import com.example.springloan.dao.domain.UserLoan;
import com.example.springloan.dao.domain.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {

    private final UserLoanService userLoanService;

    public UserPrincipalService(UserLoanService userLoanService) {
        this.userLoanService = userLoanService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserLoan user = userLoanService.getByUsername(s);

        return new UserPrincipal(user);

    }
}

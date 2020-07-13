package com.example.springloan;

import com.example.springloan.dao.domain.*;
import com.example.springloan.service.*;
import javassist.NotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final LoanRequestService loanRequestService;
    private final BranchService branchService;
    private final LoanTypeService loanTypeService;
    private final PasswordEncoder passwordEncoder;
    private final UserLoanService userLoanService;

    public DataLoader(UserService userService, LoanRequestService loanRequestService, BranchService branchService, LoanTypeService loanTypeService, PasswordEncoder passwordEncoder, UserLoanService userLoanService) {
        this.userService = userService;
        this.loanRequestService = loanRequestService;
        this.branchService = branchService;
        this.loanTypeService = loanTypeService;
        this.passwordEncoder = passwordEncoder;
        this.userLoanService = userLoanService;
    }

    @Override
    public void run(String... args) throws Exception {
        loader();
    }

    public void loader() throws NotFoundException {

        UserLoan userLoan = new UserLoan();
        userLoan.setUsername("user");
        userLoan.setStatus(true);
        userLoan.setPassword(passwordEncoder.encode("user"));
        userLoan.setUserRole("SUPERADMIN");
        userLoanService.create(userLoan);

    }
}
package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.LoanRequest;
import com.example.springloan.dao.domain.LoanType;
import com.example.springloan.dao.domain.User;
import com.example.springloan.service.LoanRequestService;
import com.example.springloan.service.LoanTypeService;
import com.example.springloan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    private final UserService userService;
    private final LoanRequestService loanRequestService;
    private final LoanTypeService loanService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, LoanRequestService loanRequestService, LoanTypeService loanService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.loanRequestService = loanRequestService;
        this.loanService = loanService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User input) {
        User created = userService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.get(id));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User input, @PathVariable Long id) {
        if ((input.getId() != null) && (input.getId().equals(id))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setId(id);
            return ResponseEntity.ok().body(userService.update(input));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            User user = userService.get(id);
            List<LoanRequest> loanRequests = loanRequestService.getAllLoanRequestByUser(user);

            for (LoanRequest loanRequest : loanRequests) {
                LoanType loan = loanService.get(loanRequest.getLoanType().getId());
                loanRequestService.delete(loanRequest.getId());
                loanService.delete(loan.getId());
            }

            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
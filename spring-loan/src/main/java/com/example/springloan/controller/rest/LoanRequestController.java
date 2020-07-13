package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.LoanRequest;
import com.example.springloan.dao.domain.UserLoan;
import com.example.springloan.service.LoanRequestService;
import com.example.springloan.service.LoanTypeService;
import com.example.springloan.service.UserLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/rest/loan-request")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;
    private final UserLoanService userLoanService;
    private final LoanTypeService loanService;

    public LoanRequestController(LoanRequestService loanRequestService, UserLoanService userLoanService, LoanTypeService loanService) {
        this.loanRequestService = loanRequestService;
        this.userLoanService = userLoanService;
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanRequest> create(@RequestBody LoanRequest input) {
        LoanRequest created = loanRequestService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<LoanRequest>> readAll(@PathVariable int id) {

        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();

        UserLoan userLoan = userLoanService.getByUsername(authenticated.getName());

        if (userLoan.getBranch().getId() == id) {
            return ResponseEntity.ok().body(loanRequestService.getLoanRequestsByBranch((long) id));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("")
    @PreAuthorize(value = "hasAuthority('SUPERADMIN')")
    public ResponseEntity<List<LoanRequest>> getAllLoanRequest() {
        return ResponseEntity.ok().body(loanRequestService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(loanRequestService.get(id));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanRequest> update(@RequestBody LoanRequest input, @PathVariable Long id) {
        if (input.getId() != null && !(input.getId().equals(id))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setId(id);
            return ResponseEntity.ok().body(loanRequestService.update(input));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            loanRequestService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.LoanRequestHistory;
import com.example.springloan.service.LoanRequestHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/loan-request-history")
public class LoanRequestHistoryController {

    private final LoanRequestHistoryService loanRequestHistoryService;

    public LoanRequestHistoryController(LoanRequestHistoryService loanRequestHistoryService) {
        this.loanRequestHistoryService = loanRequestHistoryService;
    }

    @PostMapping
    public ResponseEntity<LoanRequestHistory> create(@RequestBody LoanRequestHistory input) {
        LoanRequestHistory created = loanRequestHistoryService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('SUPERADMIN')")
    public ResponseEntity<List<LoanRequestHistory>> getAllLoanRequest() {
        return ResponseEntity.ok().body(loanRequestHistoryService.getAll());
    }

}

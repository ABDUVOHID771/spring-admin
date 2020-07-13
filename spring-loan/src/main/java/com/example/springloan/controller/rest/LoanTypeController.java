package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.LoanRequest;
import com.example.springloan.dao.domain.LoanType;
import com.example.springloan.service.LoanRequestService;
import com.example.springloan.service.LoanTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/loan-type")
@PreAuthorize(value = "hasAuthority('SUPERADMIN')")
public class LoanTypeController {

    private final LoanTypeService loanTypeService;
    private final LoanRequestService loanRequestService;

    public LoanTypeController(LoanTypeService loanTypeService, LoanRequestService loanRequestService) {
        this.loanTypeService = loanTypeService;
        this.loanRequestService = loanRequestService;
    }

    @PostMapping
    public ResponseEntity<LoanType> create(@RequestBody LoanType input) {
        LoanType created = loanTypeService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LoanType>> readAll() {
        return ResponseEntity.ok().body(loanTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanType> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(loanTypeService.get(id));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanType> update(@RequestBody LoanType input, @PathVariable Long id) {
        if ((input.getId() != 0) && !(input.getId()==id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setId(id);
            return ResponseEntity.ok().body(loanTypeService.update(input));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            LoanType loanType = loanTypeService.get(id);
            List<LoanRequest> loanRequests = loanRequestService.getByLoanType(loanType);
            for (LoanRequest loanRequest : loanRequests) {
                loanRequestService.delete(loanRequest.getId());
            }
            loanTypeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

}

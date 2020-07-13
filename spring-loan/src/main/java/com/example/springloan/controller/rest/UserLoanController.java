package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.UserLoan;
import com.example.springloan.service.UserLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/user-loan")
@PreAuthorize(value = "hasAuthority('SUPERADMIN')")
public class UserLoanController {

    private final UserLoanService userLoanService;

    public UserLoanController(UserLoanService userLoanService) {
        this.userLoanService = userLoanService;
    }

    @GetMapping
    public ResponseEntity<List<UserLoan>> readAll() {
        return ResponseEntity.ok().body(userLoanService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLoan> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userLoanService.get(id));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLoan> update(@RequestBody UserLoan input, @PathVariable Long id) {
        if ((input.getId() != null) && !(input.getId().equals(id))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setId(id);
            return ResponseEntity.ok().body(userLoanService.update(input));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Long  >getUserLoanByUsername(@PathVariable String username){
        try {
            UserLoan userLoan = userLoanService.getByUsername(username);
            return ResponseEntity.ok().body(userLoan.getBranch().getId());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}

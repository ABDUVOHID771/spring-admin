package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.Branch;
import com.example.springloan.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Branch> create(@RequestBody Branch input) {
        Branch created = branchService.create(input);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> readAll() {
        return ResponseEntity.ok().body(branchService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(branchService.get(id));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> update(@RequestBody Branch input, @PathVariable Long id) {
        if ((input.getId() != null) && (input.getId().equals(id))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setId(id);
            return ResponseEntity.ok().body(branchService.update(input));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            branchService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

}

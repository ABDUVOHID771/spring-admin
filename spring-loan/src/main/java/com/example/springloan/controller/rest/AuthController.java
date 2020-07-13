package com.example.springloan.controller.rest;

import com.example.springloan.dao.domain.Branch;
import com.example.springloan.dao.domain.UserLoan;
import com.example.springloan.dao.domain.UserPrincipal;
import com.example.springloan.jwt.AuthenticationRequest;
import com.example.springloan.jwt.AuthenticationResponse;
import com.example.springloan.jwt.JwtUtil;
import com.example.springloan.service.UserLoanService;
import com.example.springloan.service.UserPrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserPrincipalService userPrincipalService;
    private final JwtUtil jwtUtil;
    private final UserLoanService userLoanService;

    public AuthController(AuthenticationManager authenticationManager, UserPrincipalService userPrincipalService, JwtUtil jwtUtil, UserLoanService userLoanService) {
        this.authenticationManager = authenticationManager;
        this.userPrincipalService = userPrincipalService;
        this.jwtUtil = jwtUtil;
        this.userLoanService = userLoanService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException exception) {
            throw new Exception("Incorrect username or password !");
        }
        final UserDetails userDetails = userPrincipalService.loadUserByUsername(authenticationRequest.getUsername());
        UserLoan userLoan = userLoanService.getByUsername(authenticationRequest.getUsername());
        List<String> authorities = Collections.singletonList(userDetails.getAuthorities().toString());
        int branchId = 0;
        if (authorities.contains("[SUPERADMIN]") || authorities.contains("[ADMIN]")) {
            if (authorities.contains("[SUPERADMIN]")) {
                branchId = -1;
            }
            if (authorities.contains("[ADMIN]")) {
                Branch branch = userLoan.getBranch();
                System.out.println("BRANCH " + branch);
                branchId = Math.toIntExact(branch.getId());
            }
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, branchId, userLoan.getUserRole(), Math.toIntExact(userLoan.getId())));
        } else {
            throw new Exception("Incorrect username or password !");

        }

    }
//
//    @GetMapping("/refresh")
//    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization");
//        final String token = authToken.substring(7);
//        String username = jwtUtil.extractUsername(token);
//
//        UserPrincipal userPrincipal = (UserPrincipal) userPrincipalService.loadUserByUsername(username);
//
//        if (jwtUtil.canTokenRefreshed(token) && userPrincipal != null) {
//            String newToken = jwtUtil.refreshToken(token);
//            return ResponseEntity.ok(new AuthenticationResponse(newToken, 0,userPrincipal.getAuthorities().toString()));
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

}

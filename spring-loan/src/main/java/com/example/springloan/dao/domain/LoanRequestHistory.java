package com.example.springloan.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_request_history")
public class LoanRequestHistory extends BaseEntity {

    @OneToOne
    private LoanRequest loanRequest;

    @Column(name="loan_user_id")
    private Long loanUser;

    @Column(name = "previous_status")
    private Status previousStatus;

    @Column(name = "changed_status")
    private Status changedStatus;

}

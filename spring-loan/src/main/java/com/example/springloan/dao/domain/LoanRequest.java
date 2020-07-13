package com.example.springloan.dao.domain;

import com.example.springloan.dao.domain.BaseEntity;
import com.example.springloan.dao.domain.LoanCountingStrategy;
import com.example.springloan.dao.domain.LoanType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "loan_request")
public class LoanRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userRequest;

    @OneToOne
    private LoanType loanType;

    @NotNull
    @Enumerated
    @Column(name = "status")
    private Status status;

    @Column(name = "additional_phone")
    private String additionalPhone;

    @Column(name = "salary_card_bin")
    private String salaryCardBin;

    @Column(name = "TIN")
    private String TIN;

    @Column(name = "requested_amount")
    private double requestedAmount;

    @Column(name = "counting_strategy")
    private LoanCountingStrategy countingStrategy;

    @Column(name="passport_photo1")
    private String passportPhoto1;

    @Transient
    private MultipartFile passportPhoto2;

    @Transient
    private MultipartFile selfiePhoto;

    @Column(name = "passport_serie")
    private String passportSerie;

    @Column(name = "passport_number")
    private String passportNumber;

    @OneToOne
    private Branch branch;

}

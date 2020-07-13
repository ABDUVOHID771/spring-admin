package com.example.springloan.dao.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "loan_type")
public class LoanType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "min_amount")
    private Double minAmount;

    @Column(name = "max_amount")
    private Double maxAmount;

    @Column(name="min_month")
    private int minMonth;

    @Column(name="max_month")
    private int maxMonth;

    @Column(name="annuitet")
    private boolean annuitet;

    @Column(name="differential")
    private boolean differential;

    @Column(name="insurance")
    private String insurance;

    @Column(name="insurance_price")
    private Double insurancePrice;

    @Column(name="card_payment")
    private int cardPayment;

}

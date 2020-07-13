package com.example.springloan.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class Branch extends BaseEntity {

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "branch_long")
    private Double lonG;

    @Column(name = "branch_lat")
    private Double lat;

    @Column(name = "address")
    private String address;
}

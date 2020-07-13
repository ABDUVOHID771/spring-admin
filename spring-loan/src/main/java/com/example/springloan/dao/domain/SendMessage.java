package com.example.springloan.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sendMessages")
public class SendMessage extends BaseEntity {

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "text")
    private String text;

    @Column(name = "result")
    private String result;

}

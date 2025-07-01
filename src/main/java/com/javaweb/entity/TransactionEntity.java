package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {
    @Column(name="code")
    String transactionName;
    @Column(name = "note")
    String note;
    @Column(name = "customerid")
    Long customerId;
}

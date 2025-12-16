package com.example.quicken.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Long id;
    private Long accountId;
    private LocalDate date;
    private BigDecimal amount;
    private String description;

    // Constructor
    public Transaction(Long id, Long accountId, LocalDate date, BigDecimal amount, String description) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

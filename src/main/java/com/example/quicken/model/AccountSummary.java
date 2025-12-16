package com.example.quicken.model;

import java.math.BigDecimal;

public class AccountSummary {

    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private BigDecimal net;

    public AccountSummary(BigDecimal totalIncome, BigDecimal totalExpenses, BigDecimal net) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.net = net;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public BigDecimal getNet() {
        return net;
    }
}
